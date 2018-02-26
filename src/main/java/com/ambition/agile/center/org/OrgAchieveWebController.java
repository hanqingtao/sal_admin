/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.org;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.DateTimeUtil;
import com.ambition.agile.common.util.ExcelUtil;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAchieve;
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgAchieveService;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.sys.utils.DictUtils;

/**
 * 代理机构招标业绩Controller
 * @author harry
 * @version 2017-08-03
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/org/orgAchieve")
public class OrgAchieveWebController extends BaseController {

	
	@Autowired
	private OrgAchieveService orgAchieveService;
	
	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private OrgService orgService;
	
	@ModelAttribute
	public OrgAchieve get(@RequestParam(required=false) String id) {
		OrgAchieve entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgAchieveService.get(id);
		}
		if (entity == null){
			entity = new OrgAchieve();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(OrgAchieve orgAchieve, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgAchieve> page = orgAchieveService.findPage(new Page<OrgAchieve>(request, response), orgAchieve); 
		model.addAttribute("page", page);
		return "center/org/orgAchieve";
	}

	@RequestMapping(value = "form")
	public String form(OrgAchieve orgAchieve, Model model) {
		model.addAttribute("orgAchieve", orgAchieve);
		return "center/org/orgAchieve";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public ApiResponse<?> save(OrgAchieve orgAchieve, Model model, 
			RedirectAttributes redirectAttributes,HttpSession session) {
		 
		OrgUser user = (OrgUser) session.getAttribute("user");
		if(user!=null){
			OrgUser orgUser=orgUserService.get(user);
			user = orgUser;
			orgAchieve.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));	
			
			//更新Org的三年内业绩额度
			 Org org2 = orgService.get(orgUser.getOrg());
			 orgService.updateRecentMoney(org2);
		}else{
			return ApiResponse.fail(300, "请登录");	
		}
		
		if(!org.apache.commons.lang.StringUtils.isNotEmpty(orgAchieve.getBidMoney())){
			orgAchieve.setBidMoney(null);
		}
		if(!org.apache.commons.lang.StringUtils.isNotEmpty(orgAchieve.getEntrustMoney())){
			orgAchieve.setEntrustMoney(null);
		}
		/*
		if(!org.apache.commons.lang.StringUtils.isNotEmpty(orgAchieve.getEndEntrustMoney())){
			orgAchieve.setEndEntrustMoney(null);
		}
		if(!org.apache.commons.lang.StringUtils.isNotEmpty(orgAchieve.getEndBidMoney())){
			orgAchieve.setEndBidMoney(null);
		}
		*/
		orgAchieve.setIsHistory(1);
		orgAchieveService.save(orgAchieve);
		model.addAttribute("message","保存代理机构招标业绩成功");
		model.addAttribute("orgAchieve", orgAchieve);
//		model.addAttribute("org", org);
		model.addAttribute("org", user.getOrg());
		return ApiResponse.success(null);
	}
	
	@RequestMapping(value = "change")
	public String change(OrgAchieve orgAchieve,Org org, Model model, RedirectAttributes redirectAttributes,HttpSession session) {
		 
		
		orgAchieveService.save(orgAchieve);
		OrgUser user = (OrgUser) session.getAttribute("user");
		if(user!=null){
			OrgUser orgUser=orgUserService.get(user);
			 if(orgUser.getOrg()!=null){
					List<OrgAchieve> achieveList=orgAchieveService.findAchieveByOrgId(orgUser.getOrg().getId());
					
					
					model.addAttribute("achieveList", achieveList);
				 }
			
			 //更新Org的三年内业绩额度
			 Org org2 = orgService.get(orgUser.getOrg());
			 orgService.updateRecentMoney(org2);
		}else{
			return "center/login";	
		}
		
		model.addAttribute("message","保存代理机构招标业绩成功");
		model.addAttribute("orgAchieve", orgAchieve);
		model.addAttribute("org", org);
		return "center/change/changeOrgAchieve";
	}
    /**
     * 上传注册招标业绩入口
     * @param orgAchieve
     * @param model
     * @return
     */
	@RequestMapping(value = "entry")
	public String entry(String id,Org org,OrgAchieve orgAchieve,Model model) {
		model.addAttribute("orgAchieve", orgAchieve);
		model.addAttribute("org", org);
	    model.addAttribute("orgId",id);
		return "center/org/orgAchieve";
	}
	
	/**
	 * 上传完业绩结果入口
	 * @param id
	 * @param org
	 * @param orgAchieve
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "changeEntry")
	public String rchangeEntry(OrgAchieve orgAchieve,Model model,HttpSession session) {
		OrgUser user = (OrgUser) session.getAttribute("user");
		   
		 if(user!=null){
			 OrgUser orgUser=orgUserService.get(user);
		 
			 if(orgUser.getOrg()!=null){
				List<OrgAchieve> achieveList=orgAchieveService.findAchieveByOrgId(orgUser.getOrg().getId());
				System.out.println(achieveList+"获取下专职List");
				
				model.addAttribute("achieveList", achieveList);
			 }
		  
		}else{
			return "center/login";	
		}
		 
		return "center/change/changeOrgAchieve";
	}
	
	
	/**
	 * ajax加载人员分页
	 * @param orgStaff
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "achievePage")
	public String achievePage( 
			Page<OrgAchieve> page,Integer pageNo,Integer pageSize,
			Model model,HttpSession session,HttpServletRequest request) {
		OrgUser user=(OrgUser)session.getAttribute("user");
		if(user==null){
			return "center/login";
		}
		OrgUser orgUser=orgUserService.get(user);
		if(page == null){
	 		page = new Page<>();
	 	}
		page.setPageSize(99999999);
	 	if(pageNo == null){
	 		pageNo = 1;
	 	}
	 	page.setPageNo(pageNo);
	 	
	 	OrgAchieve orgStaff = new OrgAchieve();
	 	String orgIdstr = orgUser.getOrg().getId();
	 	if(StringUtils.isNotEmpty(orgIdstr)){
	 		orgStaff.setOrgId(Integer.parseInt(orgIdstr));
	 	}
	 	page = orgAchieveService.findPage(page,orgStaff);
	 	model.addAttribute("page", page);
	 	model.addAttribute("orgId", orgIdstr);
		return "center/org/achievePage";
	}
	//读取业绩存入 project表 原来的业绩表不用了,org_achieve
	@RequestMapping(value = "importAchieve")
	@ResponseBody
	public Map<String, Object> imporgAchieve(@RequestParam(value = "imporgAchieve", required = false) MultipartFile imporgAchieve,
			OrgAchieve orgAchieve, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		OrgUser user=(OrgUser)session.getAttribute("user");
		Map<String, Object> resMap = new HashMap<>();
		int messageCode = 200;
		if(user==null){
			messageCode = 401; //未登录
			resMap.put("messageCode", messageCode);
			return  resMap;
		}
		OrgUser orgUser=orgUserService.get(user);
		
	 
		
		if (imporgAchieve != null && !StringUtils.isEmpty(imporgAchieve.getOriginalFilename())) {
			try {
				InputStream inputStream = imporgAchieve.getInputStream();
				ApiResponse<List<Map<String, Object>>> orgAchieveList =ExcelUtil.readExcelToAchieve(inputStream);
				if(orgAchieveList.isSuccess()){
					System.out.println("成功");
					List<Map<String, Object>> qmaps = orgAchieveList.getBody();//获取单元格数据
					if(qmaps!=null&&qmaps.size()>0){
						Integer countNum = 3;
						orgAchieve.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
					    for (Map<String, Object> map : qmaps) {
					    	
					    	orgAchieve.setId(null);
					    	orgAchieve.setNum((String)map.get("num"));
					    	orgAchieve.setName((String)map.get("name"));
					    	orgAchieve.setType((String)map.get("type"));
					    	orgAchieve.setIsCenter((Integer)map.get("isCenter"));
					    	orgAchieve.setEntrustUnit((String)map.get("entrustUnit"));
//					    	orgStaff.setEndBidMoney((String)map.get("entrustMoney"));
					    	orgAchieve.setEntrustMoney((String)map.get("entrustMoney"));
					    	orgAchieve.setBidMoney((String)map.get("bidMoney"));
					    	orgAchieve.setTenderOpenTime(DateTimeUtil.stringToDate((String)map.get("tenderOpenTime"), "yyyy-MM-dd HH:mm:ss"));
					    	orgAchieve.setBidTime(DateTimeUtil.stringToDate((String)map.get("bidTime"), "yyyy-MM-dd HH:mm:ss"));
					    	orgAchieve.setNoticeMedia((String)map.get("noticeMedia"));
					    	orgAchieve.setNoticeDate(DateTimeUtil.stringToDate((String)map.get("noticeDate"), "yyyy-MM-dd HH:mm:ss"));
					    	orgAchieve.setIsHistory(1);
					    	System.out.println(orgAchieve+"读取到的数据");
							orgAchieveService.save(orgAchieve);
					    }
					    
					    messageCode = 200;					
					}else{
						messageCode = 500;					
					}
					
				}else{
					messageCode = 500;					
					
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			 

		}
		messageCode = 200;
		//更新Org的三年内业绩额度
		Org org = orgService.get(orgUser.getOrg());
		orgService.updateRecentMoney(org);
		resMap.put("messageCode", messageCode);
		return resMap;
	}
	/**
	 * ajax删除
	 * @param orgStaff
	 * @param redirectAttributes
	 */
	@RequestMapping(value = "delete")
	public void delete(OrgAchieve orgStaff, RedirectAttributes redirectAttributes,HttpSession session) {
		
		orgAchieveService.delete(orgStaff);
		//更新Org的三年内业绩额度
		OrgUser user=(OrgUser)session.getAttribute("user");
		if(user != null){
			OrgUser orgUser=orgUserService.get(user);
			Org org = orgService.get(orgUser.getOrg());
			orgService.updateRecentMoney(org);
		}
	}
}