/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.MailBean;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAudit;
import com.ambition.agile.modules.org.service.OrgAchieveService;
import com.ambition.agile.modules.org.service.OrgAuditService;
import com.ambition.agile.modules.org.service.OrgQualificationService;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgStaffService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.service.CreditRecordService;
import com.ambition.agile.modules.pro.service.ProjectService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 代理机构Controller
 * @author harry
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/org/org")
public class OrgController extends BaseController {
	@Value("${mail.from}")  
	private String mailFrom; 
	
	@Autowired
	private OrgService orgService;

	@Autowired
	private  SystemService systemService;
	
	@Autowired
	private  OfficeService officeService;
	
	@Autowired
	private  OrgAuditService orgAuditService;
	
	@Autowired
	private  OrgQualificationService orgQualificationService;
	
	@Autowired
	private  OrgStaffService  orgStaffService; 
	
	@Autowired
	private  ProjectService  projectService;
	
	@Autowired
	private  CreditRecordService  creditRecordService;
	
	@Autowired
	private  OrgAchieveService orgAchieveService;  
	
	@Autowired
	private  OrgUserService orgUserService;
	
	@ModelAttribute
	public Org get(@RequestParam(required=false) String id) {
		Org entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgService.get(id);
		}
		if (entity == null){
			entity = new Org();
		}
		return entity;
	}
	
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"list", ""})
	public String list(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
		model.addAttribute("page", page);
		return "modules/org/orgList";
	}
	
	
	/**
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @see 查看 待审批的代理机构的数据 中心管理员  各省发改委管理员  
	 * 		根据不同省份 显示不同的数据
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"listApprove", ""})
	public String listApprove(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//User sysUser=systemService.getUserByLoginName(UserUtils.getUser().getLoginName());
		//获取管理员的权限及级别进行数据查询 h  
		User user = UserUtils.getUser();
		if(null != user ){
			//System.out.println("11111----------office id: " + user.getOffice().getId());
			if(org == null){
				org = new Org();
			}
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					// 招标中心管理员
					if(roleId.equals("5")){  
						s_sys = false; break;
					}
				}
				if(s_sys){					
//					org.setArea(user.getOffice().getArea());
					org.setArea(user.getCompany().getArea());
					org.setStatus(OrgConstant.ORG_STATUS_SUBMIT);
				}else{
					org.setStatus("25");
				}
				Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
				model.addAttribute("page", page);
			}
		}
		return "modules/org/orgApprovalList";
	}
	
	/**
	 * 进入代理机构审批页面
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"toApproveForm", ""})
	public String toApproveForm(OrgAudit orgAudit, Model model) {
		Org org = orgService.get(orgAudit.getOrgId()+"");
		model.addAttribute("orgName", org.getName());
		model.addAttribute("ostatus", org.getStatus());
		
		model.addAttribute("orgAudit", orgAudit);
		return "modules/org/orgApproveForm";
	}
	
	/**
	 * 保存审批意见信息
	 * @param orgAudit
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"saveApproveForm", ""})
	public String saveApproveForm(OrgAudit orgAudit, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		if (!beanValidator(model, orgAudit)){
			return toApproveForm(orgAudit, model);
		}
		
		User user = UserUtils.getUser();
		orgAudit.setCreateId(Integer.parseInt(user.getId()));
		orgAudit.setCreateDate(new Date());
		orgAuditService.save(orgAudit);
		
		
		// 更新代理机构状态
		Org org = orgService.get(orgAudit.getOrgId()+"");
		org.setStatus(orgAudit.getOrgStatus());
		orgService.save(org);
		orgService.updateById(orgAudit.getOrgStatus(),org.getId());
		addMessage(redirectAttributes, "审批代理机构操作成功");
		return "redirect:"+Global.getAdminPath()+"/org/org/listApprove";
	}
	
	/**
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @see 查看 待审批的代理机构的数据 中心管理员  各省发改委管理员  
	 * 		根据不同省份 显示不同的数据
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"listApproved", ""})
	public String listApproved(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User sysUser=systemService.getUserByLoginName(UserUtils.getUser().getLoginName());
		
		//获取管理员的权限及级别进行数据查询 h  
		User user = UserUtils.getUser();
		if(null != user ){
			//System.out.println("11111----------office id: " + user.getOffice().getId());
			if(org == null){
				org = new Org();
			}
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					// 招标中心管理员
					if(roleId.equals("5")){  
						s_sys = false; break;
					}
				}
				if(s_sys){					
//					org.setArea(user.getOffice().getArea());
					org.setArea(user.getCompany().getArea());
					org.setStatus("25");
				}else{
					org.setStatus("36");
				}
				Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
				model.addAttribute("page", page);
			}
		}
		return "modules/org/orgApprovaledList";
	}
	
	
	/**
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @see 查看 黑名单
	 * 的代理机构的数据 中心管理员  各省发改委管理员  
	 * 		根据不同省份 显示不同的数据
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"listBlank", ""})
	public String listBlank(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//获取管理员的权限及级别进行数据查询 h
		
		User user = UserUtils.getUser();
		if(null != user ){
			if(org == null){
				org = new Org();
			}
			org.setStatus(OrgConstant.ORG_STATUS_BLANK);
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					// 招标中心管理员
					if(roleId.equals("5") ||roleId.equals("4") || roleId.equals("1") ){  
						s_sys = false; break;
					}
				}
				if(s_sys){					
//					org.setArea(user.getOffice().getArea());
					org.setArea(user.getCompany().getArea());
				}
			}
			Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
			model.addAttribute("page", page);
		}
		return "modules/org/blankList";
	}

	/**
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @see 查看 通过的 正式名录 的代理机构的数据 中心管理员  各省发改委管理员  
	 * 		根据不同省份 显示不同的数据
	 */
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = {"listPass", ""})
	public String listPass(Org org, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(null == org){
			org = new Org();
		}
		org.setStatus("368");
		//获取管理员的权限及级别进行数据查询 h
		User user = UserUtils.getUser();
		if(null != user ){
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					 // 招标中心管理员
					if(roleId.equals("5") ||roleId.equals("4") || roleId.equals("1") ){ 
						s_sys = false; break;
					}
				}
				if(s_sys){					
//					org.setArea(user.getOffice().getArea());
					org.setArea(user.getCompany().getArea());
				}
			}
		}
		Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
		model.addAttribute("page", page);
		model.addAttribute("pass", "pass");
		return "modules/org/orgList";
	}

	@RequiresPermissions("org:org:edit")
	@RequestMapping(value = "delete")
	public String delete(Org org, RedirectAttributes redirectAttributes) {
		orgService.delete(org);
		addMessage(redirectAttributes, "删除代理机构成功");
		return "redirect:"+Global.getAdminPath()+"/org/org/?repage";
	}
	
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "form")
	public String form(Org org, Model model) {
		model.addAttribute("org", org);
		return "modules/org/orgForm";
	}
	
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "details")
	public String details(Org org, Model model,OrgAudit orgAudit ) {
		User user = UserUtils.getUser();
		if(null != user ){
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					// 招标中心管理员
					if(roleId.equals("5") ||roleId.equals("4") || roleId.equals("1") ){  
						s_sys = false; break;
					}
				}
				model.addAttribute("s_sys",s_sys);
			}
		}
		 
		model.addAttribute("orgName", org.getName());
		model.addAttribute("ostatus", org.getStatus());
		
		model.addAttribute("orgAudit", orgAudit);
		model.addAttribute("org", orgService.get(org));
		model.addAttribute("orgStaffList", orgStaffService.findStaffByOrgId(org.getId().toString()));
		model.addAttribute("projectList", projectService.findInfoByOrgId(Integer.valueOf(org.getId())));
		model.addAttribute("creditRecordList", creditRecordService.findCreditRecordByOrgId(Integer.valueOf(org.getId())));
		model.addAttribute("orgQualificationList", orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(org.getId())));
		model.addAttribute("orgAuditList", orgAuditService.findOrgAuditByOrgId(Integer.valueOf(org.getId())));
		return "modules/org/orgDetails";
	}

	@RequiresPermissions("org:org:edit")
	@RequestMapping(value = "save")
	public String save(Org org, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, org)){
			return form(org, model);
		}
		orgService.save(org);
		addMessage(redirectAttributes, "保存代理机构成功");
		return "redirect:"+Global.getAdminPath()+"/org/org/?repage";
	}
	
	@RequiresPermissions("org:org:edit")
	@RequestMapping(value = "editBlank")
	public String editBlank(Org org, RedirectAttributes redirectAttributes) {
		//orgService.delete(org);
		org = orgService.get(org);
		org.setStatus(OrgConstant.ORG_STATUS_BLANK);
		orgService.save(org);
		addMessage(redirectAttributes, "已成功加入黑名单");
		return "redirect:"+Global.getAdminPath()+"/org/org/listPass";
	}
	
	@RequiresPermissions("org:org:edit")
	@RequestMapping(value = "removeBlank")
	public String removeBlank(Org org, RedirectAttributes redirectAttributes) {
		org = orgService.get(org);
		org.setStatus(OrgConstant.ORG_STATUS_PASS);
		orgService.save(org);
		addMessage(redirectAttributes, "已成功移除黑名单");
		return "redirect:"+Global.getAdminPath()+"/org/org/listBlank";
	}
	
	@RequiresPermissions("org:org:edit")
	@RequestMapping(value = "stop")
	public String stop(Org org, RedirectAttributes redirectAttributes) {
		 
		return "redirect:"+Global.getAdminPath()+"/org/org/listPass";
	}
	
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "orgApprovedDatails")
	public String orgApprovedDatails(Org org, Model model) {
		User user = UserUtils.getUser();
		if(null != user ){
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					// 招标中心管理员
					if(roleId.equals("5") ||roleId.equals("4") || roleId.equals("1") ){  
						s_sys = false; break;
					}
				}
				model.addAttribute("s_sys",s_sys);
			}
		}
		 
		model.addAttribute("orgName", org.getName());
		model.addAttribute("ostatus", org.getStatus());
		model.addAttribute("org", orgService.get(org));
		model.addAttribute("orgStaffList", orgStaffService.findStaffByOrgId(org.getId().toString()));
		model.addAttribute("creditRecordList", creditRecordService.findCreditRecordByOrgId(Integer.valueOf(org.getId())));
		model.addAttribute("orgQualificationList", orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(org.getId())));
		//以中标时间算三年业绩
		model.addAttribute("orgAchieveList", orgAchieveService.findAchieveByOrgId(org.getId()));
		model.addAttribute("orgAuditList", orgAuditService.findOrgAuditByOrgId(Integer.valueOf(org.getId())));
		model.addAttribute("org", org);
		return "modules/org/orgApproveDetails";
	}
	
	
	@RequiresPermissions("org:org:view")
	@RequestMapping(value = "operation")
	@ResponseBody
	public ApiResponse<?> operation(Org org,String OperationFlag,String ids, Model model,HttpServletRequest request,HttpServletResponse response) {
		try{			
			orgService.updateById(OperationFlag,ids);
		}catch(Exception e){
			return ApiResponse.fail(301, "org operation error: message: " + e.getMessage());
		}
		return ApiResponse.success(null);
	}

}