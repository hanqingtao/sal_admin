/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.org;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgAchieveService;
import com.ambition.agile.modules.org.service.OrgBakService;
import com.ambition.agile.modules.org.service.OrgQualificationService;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgStaffService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.entity.CreditRecord;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.pro.service.CreditRecordService;
import com.ambition.agile.modules.pro.service.ProjectService;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.service.AreaService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 代理机构Controller
 * 
 * @author harry
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${frontPath}/org/org")
public class OrgWebController extends BaseController {
	@Autowired
	private OrgService orgService;
	@Autowired
	private OrgBakService orgBakService;

	@Autowired
	private OrgUserService orgUserService;

	@Autowired
	private OrgStaffService orgStaffService;
	
	@Autowired
	private OrgAchieveService orgAchieveService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private OrgQualificationService orgQualificationService;
	
	@Autowired
	private CreditRecordService creditRecordService;
	
	@Autowired
	private ProjectService projectService;
	
	@ModelAttribute
	public Org get(@RequestParam(required = false) String id) {
		Org entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orgService.get(id);
		}
		if (entity == null) {
			entity = new Org();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Org org, HttpServletRequest request, OrgQualification orgQualification,
			HttpServletResponse response, Model model) {
		Page<Org> page = orgService.findPage(new Page<Org>(request, response), org);
		model.addAttribute("page", page);
		model.addAttribute("orgQualification", orgQualification);
		model.addAttribute("org", org);
		return "center/agent/agentList";
	}

	@RequestMapping(value = "form")
	public String form(Org org, Model model, HttpSession session) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message", "请您登录");
			return "index";
		}
		model.addAttribute("org", org);
		return "center/org/orgInfo";
	}

	/**
	 * @param file1
	 * @param file2
	 * @param org
	 * @param orgQualification
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param session
	 * @param areaParentids
	 * @see 用于企业信息的保存,保存完进入下一步 
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save( Org org,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session,String areaParentids,String changeFlag) {
		    if(org.getArea()!=null){
		        Area area=areaService.get(org.getArea().getId());
		        org.setAreaParentids(area.getCode()); 
		    }else{
		    	model.addAttribute("message", "请选择机构区域");
				return "center/org/orgInfo";
		    }
		if (orgService.selectOrgcode(org.getScCode()) > 0) {
			model.addAttribute("message", "已有社会信用代码");
			return "center/org/orgInfo";
		}
		org.setStatus(OrgConstant.ORG_STATUS_INIT); //设置Org状态 0，未提交
		orgService.save(org);
		
		
		OrgUser user = (OrgUser) session.getAttribute("user");
		
		user.setOrg(org);
		 
		orgUserService.save(user);
		
		model.addAttribute("message", "保存代理机构成功");
		
		if(StringUtils.isNotEmpty(changeFlag)&&"0".equals(changeFlag)){
			
			return "center/change/changeOrgQualification";
			
		}
		
		return "center/org/orgQualification";
	}
	
	
	
	
	@RequestMapping(value = "edit")
	@ResponseBody
	public ApiResponse<?> edit(Org org,
			HttpServletRequest request,String saveFlag, HttpSession session) {
		System.out.println(org);
		System.out.println(org.getArea());
		String message = "保存成功";
		if(org.getId() != null&&!"1".equals(saveFlag)){
			
			if (orgService.getOrgByScCode(org) > 0) {
				message = "社会信用代码已存在!";
				return ApiResponse.fail(401, message);
			}
		}
		OrgUser user = (OrgUser) session.getAttribute("user");
		if(user!=null){
			user=orgUserService.get(user);
		}else{
			return ApiResponse.fail(300, "请登录!");
		}
		
		
		
		if(StringUtils.isEmpty(org.getStatus())){
			org.setStatus(OrgConstant.ORG_STATUS_INIT);//设置Org状态-1，未提交	
		}
		orgService.save(org);
		
		
		user.setOrg(org);
		 
		orgUserService.save(user);
		Map<String, Object> map = new HashMap<>();
		map.put("orgId", org.getId());
		return ApiResponse.success(message, map);
	}
	
	

	/**
	 * 进入填写机构信息的入口(从首页进)
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "entry")
	public String entrance(Model model, Org org, OrgQualification orgQualification,
			RedirectAttributes redirectAttributes, HttpSession session) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message","请你登录");
			return "center/org/register";
		}
		OrgUser orgUser=orgUserService.get(user);
		
		model.addAttribute("org", orgUser.getOrg());
		if (orgUser.getOrg() != null){
			if(orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId())).size()>0){
				if(orgStaffService.findStaffByOrgId(orgUser.getOrg().getId()).size()>0){
					if(orgAchieveService.findAchieveByOrgId(orgUser.getOrg().getId()).size()>0){
						return "center/org/result";	
					}else{
						return "center/org/orgAchieve";	
					}
					
				}else{
					return "center/org/orgStaff";
				}
//				return "center/org/orgStaff";
				  
			}else{
				return "center/org/orgQualification";	
			}
			  
		}else{	
		       return "center/org/orgInfo";
		}
		
		 
	}
	/**
	 * 跳转页面
	 * @param pageName
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{pageName}",method = RequestMethod.GET)
	public String toTargetPage(@PathVariable("pageName") String pageName,HttpSession session,
			HttpServletRequest request,Model model){
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message","请你登录");
			return "center/org/register";
		}
		OrgUser orgUser=orgUserService.get(user);
		
		model.addAttribute("org", orgUser.getOrg());
		return "center/org/"+pageName;
	}

	
	@RequestMapping(value = "goback")
	public String goBack(Model model, Org org, OrgQualification orgQualification,
			RedirectAttributes redirectAttributes, HttpSession session) {
		/*OrgUser user = (OrgUser) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message","请你登录");
			return "index";
		}
		OrgUser orgUser=orgUserService.get(user);
		
		if (orgUser.getOrg() != null){
			if(orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId())).size()>0){
				if(orgStaffService.findStaffByOrgId(orgUser.getOrg().getId()).size()>0){
					if(orgAchieveService.findAchieveByOrgId(orgUser.getOrg().getId()).size()>0){
						return "center/org/result";	
					}else{
						return "center/org/orgAchieve";	
					}
					
				}else{
					return "center/org/orgStaff";
				}
				  
			}else{
				return "center/org/orgQualification";	
			}
			  
		}else{	
		       return "center/org/orgInfo";
		}*/
		return "center/org/orgInfo";
		 
	}
	
	
	/**
	 * 进入机构下的资质管理
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "enter")
	public String inter(Model model, RedirectAttributes redirectAttributes, HttpSession session,String qualificationManagement) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message","请你登录");
			model.addAttribute("qualificationManagement",qualificationManagement);
			return "center/login";
		}
		
		OrgUser orgUser=orgUserService.get(user);
	    Org orgObj = orgService.get(orgUser.getOrg());
	   model.addAttribute("org", orgObj);	
		return "center/mechanism/mechanism";
	}

	/**
	 * 验证信用代码是否重复
	 * 
	 * @param model
	 * @param sc_code
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "repeat")
	@ResponseBody
	public ApiResponse<?> findorgCode(Model model, String sc_code, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		   
		if (StringUtils.isNotEmpty(sc_code)) {
		 
				if (orgService.selectOrgcode(sc_code) > 0) {
					 
					return  ApiResponse.fail(401, "&nbsp;该信用代码已被占用！");

				} else{
					return  ApiResponse.fail(200, "&nbsp;该信用代码可以使用！");			
				}
				 
			 
		}
		return  ApiResponse.fail(402, "&nbsp;信用代码不能为空！");

	}

	/**
	 * 添加社保信息
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ApiResponse<?> add(@RequestParam(required=true)String org_socialinsu_photo,
			Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user != null){
			 OrgUser orgUser=orgUserService.get(user);
			 Org org=orgService.get(orgUser.getOrg());
			 org.setSocialinsuPhoto(org_socialinsu_photo);
			 orgService.save(org);
		}else{
 
			return ApiResponse.fail(300, "请登录");
		}
 
		
 
		return ApiResponse.success("保存成功", null);
	}

	/**
	 * 代理名录栏目下
	 * 
	 * @param model
	 * @param org
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "agent")
	public String orgNote(String areaCode,String nature,String keyWord,Model model, Org org, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		org.setNature(nature);
		org.setName(keyWord);
		org.setAreaParentids(areaCode);
         Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
		 
       model.addAttribute("page",page);
         
		return "center/agent/aginetList";
	}
	
	/**
	 * 注册代理机构成功
	 * @param org
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value = "succ")
	public String succ(Org org, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		//设置Org状态为提交，1，设置提交时间
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user != null){
			 OrgUser orgUser=orgUserService.get(user);
			 org=orgService.get(orgUser.getOrg());
			 if("0".equals(org.getStatus())){ //如果是未提交状态，这里设置提交，并设置提交时间
				 org.setStatus(OrgConstant.ORG_STATUS_SUBMIT);
				 org.setSubmitTime(new Date());
				 orgService.save(org);
				 
				 //首次备份
				 orgBakService.bakOrgData(org,"0");
			 }
			  
		}
		
		return "center/org/result";
	}
	@RequestMapping(value = "ajaxSucess")
	public void ajaxSucess(Org org, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		//设置Org状态为提交，1 ,设置提交时间
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user != null){
			 OrgUser orgUser=orgUserService.get(user);
			 org=orgService.get(orgUser.getOrg());
			 if("0".equals(org.getStatus())){ //如果是未提交状态，这里设置提交，并设置提交时间
				 org.setStatus(OrgConstant.ORG_STATUS_SUBMIT);
				 org.setSubmitTime(new Date());
				 orgService.save(org);
				 
				 //首次备份
				 orgBakService.bakOrgData(org,"0");
			 }
		}
	}
	/**
	 * 页面点击保存备份，变更数据备份
	 * @param org
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "ajaxBak")
	public String ajaxBak(Org org, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		//设置Org状态为提交，1 ,设置提交时间
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user != null){
			OrgUser orgUser=orgUserService.get(user);
			org=orgService.get(orgUser.getOrg());
			//后次变更
			orgBakService.bakOrgData(org,"1");
		}
		model.addAttribute("org", org);
		return "center/change/changeSucc";
	}
	
	
	/**
	 * 机构 信息填报  或  更改 
	 * @param org
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "changeEntry")
	public String changeEntry(Org org,String tagNum,String flag, 
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		
		OrgUser user = (OrgUser) session.getAttribute("user");
		
		if(user!=null){
			OrgUser orgUser=orgUserService.get(user);
			if(null != orgUser && null != orgUser.getOrg() && 
					StringUtils.isNotEmpty(orgUser.getOrg().getId())){
				org=orgService.get(orgUser.getOrg().getId());
			
				//更新是否已经添加过 三种信息的状态字段 
				//查询 该代理机构下的证书信息是否已经上传
				if(orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(orgUser.getOrg().getId())).size()>0){
					if(org.getQualificationOn() != 1){
						org.setQualificationOn(1);
					}
				}else{
					org.setQualificationOn(0);
				}
				//查询 该代理机构下的专职人员信息是否已经上传
				if(orgStaffService.findStaffByOrgId(orgUser.getOrg().getId()).size()>0){
					if(org.getStaffOn() != 1){
						org.setStaffOn(1);
					}
				}else{
					org.setStaffOn(0);
				}
				//查询 该代理机构下的业绩信息是否已经上传 1是  0否
				if(orgAchieveService.findAchieveByOrgIdNoYears(orgUser.getOrg().getId()).size()>0){
					if(org.getAchieveOn() != 1){
						org.setAchieveOn(1);
					}
				}else{
					org.setAchieveOn(0);
				}
				orgService.updateStateOn(org);
			}
		}else{
			return "center/login";
		}
		model.addAttribute("org", org);
		model.addAttribute("flag",flag);
		return "center/change/changeOrgInfoInner";
	}
	
	
	
	@RequestMapping(value = "cancellation")
	public String cancellatio(String orgId,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		Org org=orgService.get(orgId);
	    org.setId(orgId);	
	    org.setStatus("7");
		orgService.save(org);
		model.addAttribute("org", org);
		return "center/mechanism/mechanism";
	}
	/**
	 * 查询机构名录
	 * @param org
	 * @param areaParentids
	 * @param nature
	 * @param name
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	//@RequestMapping(value = "")
	@RequestMapping(value = {"search", ""})
	public String search(Org org,String areaParentids,String nature,String name,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
			org.setStatus(OrgConstant.ORG_STATUS_PASS);//招标中心通过
			Page<Org> page = orgService.findPage(new Page<Org>(request, response), org); 
			model.addAttribute("page", page);
			model.addAttribute("name", name);
			model.addAttribute("nature", nature);
			model.addAttribute("areaParentids", areaParentids);
		    return "center/agent/agentList";
	}
	
	

	@RequestMapping(value = "details")
	public String findOrgDetails(Org org,String areaParentids,String nature,String name,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		 if(StringUtils.isNotEmpty(org.getId().toString())){
			 List<CreditRecord> creditRecordList=creditRecordService.findCreditRecordByOrgId(Integer.valueOf(org.getId()));
			 List<OrgStaff> orgStaffList=orgStaffService.findStaffByOrgId(org.getId().toString());
			 List<Project> projectList=projectService.findInfoByOrgId(Integer.valueOf(org.getId()));
			 List<OrgQualification> orgQualificationList=orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(org.getId()));
				model.addAttribute("orgStaffList", orgStaffList);
				model.addAttribute("projectList", projectList);
				model.addAttribute("creditRecordList", creditRecordList);
				model.addAttribute("orgQualificationList", orgQualificationList);
				model.addAttribute("org", org);
		 }
		 
		
		  return "center/agent/agentDetails";
	}
	
	@RequestMapping(value = "h5")
	public String getH5(Org org,String areaParentids,String nature,String name,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		if(StringUtils.isNotEmpty(org.getId().toString())){
			 List<CreditRecord> creditRecordList=creditRecordService.findCreditRecordByOrgId(Integer.valueOf(org.getId()));
			 List<OrgStaff> orgStaffList=orgStaffService.findStaffByOrgId(org.getId().toString());
			 List<Project> projectList=projectService.findInfoByOrgId(Integer.valueOf(org.getId()));
			 List<OrgQualification> orgQualificationList=orgQualificationService.findOrgQualificationByOrgId(Integer.valueOf(org.getId()));
				model.addAttribute("orgStaffList", orgStaffList);
				model.addAttribute("projectList", projectList);
				model.addAttribute("creditRecordList", creditRecordList);
				model.addAttribute("orgQualificationList", orgQualificationList);
				model.addAttribute("org", org);
		 }
		  return "center/h5/h5";
	}
	
	@RequestMapping(value = "fgwEntry")
	public String fgwEntry(String areaParentids,String nature,String name,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		if (user != null) {
			OrgUser orgUser=orgUserService.get(user);
			Org orgObj = orgUser.getOrg();
			if (orgObj != null) {
				String orgId = orgObj.getId();
				Org org = orgService.get(orgId);
				model.addAttribute("org", org);
			}else{
				model.addAttribute("org", orgObj);	
			}
			
		} else {
			model.addAttribute("message", "请您登录");
			return "center/login";
		}
		  return "center/change/changeFgw";
	}
	
	
	/**
	 * 根据机构性质查询前10条数据
	 * @param nature
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "nature")
	public void findOrgByNature(String  nature,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		 List<Org> orgList=orgService.getOrgByNature(nature);
		          ObjectMapper mapper = new ObjectMapper();
				try {
					String str = mapper.writeValueAsString(orgList);
					PrintWriter writer = response.getWriter();
					writer.println(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		  
	}
	
	/**
	 * 查询全国10条机构
	 * @param nature
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "all")
	public void findOrgByArea(String  nature,RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,Model model) {
		        List<Org> orgList=orgService.findOrgInfoByNum();
		          ObjectMapper mapper = new ObjectMapper();
				try {
					String str = mapper.writeValueAsString(orgList);
					PrintWriter writer = response.getWriter();
					writer.println(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		  
	}
	
}