/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.pro;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ftp.FtpConfigHolder;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.constant.ProConstant;
import com.ambition.agile.modules.pro.entity.Engineer;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.pro.entity.ProjectFlow;
import com.ambition.agile.modules.pro.service.EngineerService;
import com.ambition.agile.modules.pro.service.ProjectFlowService;
import com.ambition.agile.modules.pro.service.ProjectService;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.service.AreaService;
import com.fasterxml.jackson.databind.ObjectMapper;
 

/**
 * 项目信息表Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller                
@RequestMapping(value = "${frontPath}/pro/project")
public class ProjectWebController extends BaseController {

	  
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private OrgUserService orgUserService;  
	
	
	@Autowired
	private EngineerService engineerService; 
	
	@Autowired
	private ProjectFlowService projetFlowService; 
	
	@Autowired
	private AreaService areaService;
	
     @ModelAttribute
	public Project get(@RequestParam(required=false) String id) {
		Project entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectService.get(id);
		}
		if (entity == null){
			entity = new Project();
		}
		return entity;
	} 
	
	@RequestMapping(value = {"list", ""})
	public String list(Project project, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		   OrgUser orgUser=(OrgUser)session.getAttribute("user");
			  if(orgUser!=null){
				  String orgId=orgUser.getOrg().getId();
			    	 project.setOrgId(Integer.valueOf(orgId));
			    }else{
		    		return "center/login";
		    	} 
		  Page<Project> page = projectService.findPage(new Page<Project>(request, response), project);
		  model.addAttribute("page", page);
		return "center/pro/projectList";
	}

	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "center/pro/createPjoject";
	}

	@RequestMapping(value = "save")
	public String save(Project project,Engineer engineer, Model model, RedirectAttributes redirectAttributes,HttpSession session) {
		/*OrgUser user=(OrgUser)session.getAttribute("user");
		   if(user!=null){
			   OrgUser orgUser=orgUserService.get(user);
			   project.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
		   }
		Area area=areaService.get(String.valueOf(project.getAreaId()));
		System.out.println(area+"获取省份");
		project.setAreaName(area.getName());
		projectService.save(project);
		engineer.setId(String.valueOf(project.getEngineerId()));
		 engineer=engineerService.get(engineer);
		model.addAttribute("Info", "保存项目信息成功");
		model.addAttribute("project", project);
		model.addAttribute("engineer", engineer);*/
		Engineer eg = project.getEngineer();
		OrgUser user = (OrgUser)session.getAttribute("user");
		if(user != null){
			OrgUser orgUser=orgUserService.get(user);
		   	eg.setOrgId(orgUser.getOrg().getId());
		   	project.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
		}
		// 保存项目工程信息
		engineerService.save(eg);
		// 保存项目信息
		if(eg.getId() != null){
			if(project.getEngineerId() == null){
				project.setEngineerId(Integer.parseInt(eg.getId()));
				project.setStatus(0);
				project.setIsHistory(0);
			}
			Area area = areaService.get(String.valueOf(project.getAreaId()));
			project.setAreaName(area.getName());
			projectService.save(project);
			
			if(user != null){
				OrgUser orgUser=orgUserService.get(user);
				//更新Org的三年内业绩额度
				Org org2 = orgService.get(orgUser.getOrg());
				orgService.updateRecentMoney(org2);
			}
		}
		return "center/pro/createProject";
	}
	
	@RequestMapping(value = "delete")
	public void delete(Project project,RedirectAttributes redirectAttributes,HttpServletResponse response,HttpSession session) {
		System.out.println(project.getId()+"获取项目id");
		projectService.delete(project);
		
		//更新Org的三年内业绩额度
		OrgUser user=(OrgUser)session.getAttribute("user");
		if(user != null){
			OrgUser orgUser=orgUserService.get(user);
			Org org = orgService.get(orgUser.getOrg());
			orgService.updateRecentMoney(org);
		}
	         try{
	        	 String info="删除项目信息成功";
				 ObjectMapper mapper = new ObjectMapper();  
				String str = mapper.writeValueAsString(info); 
				PrintWriter writer = response.getWriter();
				writer.println(str);
				}catch(Exception e){
					e.printStackTrace();
				}
 
	}
	
	/**
	 * 进入中央投资项目管理
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = {"entry",""})
	public String proList(Project project, Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,HttpSession session) {
		   model.addAttribute("project", project);
		   OrgUser user=(OrgUser)session.getAttribute("user");
		   if(user!=null){
			 
			   OrgUser orgUser=orgUserService.get(user);
			   
			   List<Project> projectList=projectService.findInfoByOrgId(Integer.valueOf(orgUser.getOrg().getId()));
			 
			   model.addAttribute("projectList",projectList);
			   project.setOrgId(Integer.valueOf(orgUser.getOrg().getId()));
		   }
		  
		   Page<Project> page = projectService.findPage(new Page<Project>(request, response), project);
		   
			  model.addAttribute("page", page);
		   return "center/pro/projectList";
	}
	
	 
	
	/**
	 * 创建新新中央投资项目
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "add")
	public String createProject(Org org,Engineer engineer,Project project,Model model, RedirectAttributes redirectAttributes,HttpSession session) {
		  OrgUser user=(OrgUser)session.getAttribute("user");
		   if(user!=null){
			   OrgUser orgUser=orgUserService.get(user);
			   org=orgUser.getOrg();
			    
		   }
		   engineer=engineerService.get(String.valueOf(project.getEngineerId()));
		 
		model.addAttribute("org", org);
		model.addAttribute("engineer", engineer);
		model.addAttribute("Project", project);
		addMessage(redirectAttributes, "创建项目管理");
		return "center/pro/createProject";
	}
  
	
	/**
	 * 中央投资项目栏目
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value = "proList")
	public String prolist(Project project, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		  Page<Project> page = projectService.findPage(new Page<Project>(request, response), project);
		  model.addAttribute("page", page);
		  model.addAttribute("project", project);
		return "center/investmentPro/proList";
	}
	
	
	@RequestMapping(value = "uploadAnnouncement")
	public String uploadAnnouncement(ProjectFlow projectFlow, Integer plist, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
	    /*String projectId=(String)session.getAttribute("projectId");
	    String proId=project.getId();
        if(projectId==null){
      	  session.setAttribute("projectId",proId);	  
        }
	    session.getAttribute("projectId");*/
		String jspUrl = "center/pro/notice";
		if(null != projectFlow && projectFlow.getProjectId() > 0){
			Integer projectId = projectFlow.getProjectId();
			projectFlow = 
					projetFlowService.findInfoByProId(projectFlow.getProjectId());
			if(null == projectFlow || 
					StringUtils.isEmpty(projectFlow.getId())){
				projectFlow = new ProjectFlow();
				projectFlow.setProjectId(projectId);
				projectFlow.setNoticeStatus(0);
				projectFlow.setTenderStatus(0);
				projectFlow.setReportStatus(0);
				projectFlow.setBidStatus(0);
				projetFlowService.save(projectFlow);
			}
		}
		if(plist != null && plist == 1){  
			if(projectFlow.getBidStatus() != null && projectFlow.getBidStatus() == 1){
				jspUrl = "center/pro/result";
			}else if(projectFlow.getReportStatus() != null && projectFlow.getReportStatus() == 1){
				jspUrl = "center/pro/report";
			}else if(projectFlow.getTenderStatus() != null && projectFlow.getTenderStatus() == 1){
				jspUrl = "center/pro/tender";
			}
		}
		
		model.addAttribute("projectFlow", projectFlow);
//		model.addAttribute("imgServer", FtpConfigHolder.getFtpFileAddress());
		return jspUrl;
	}
	
	
	

	/**
	 * 获取中央投资项目
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "details")
	public String getDetails(Project project, HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
	 
		Project projectN=null;
		Engineer engineer=null;
		ProjectFlow projectFlow=null;
		if(StringUtils.isNotEmpty(project.getId())){
			 projectN=projectService.get(project.getId());
			 
		    projectFlow=projetFlowService.findInfoByProId(Integer.valueOf(project.getId()));
			 engineer=engineerService.get(String.valueOf(project.getEngineerId()));
			 
			  
		}
		 
		 
		 
		model.addAttribute("projectFlow", projectFlow);
		  model.addAttribute("project", projectN);
		  model.addAttribute("engineer", engineer);
		return "center/investmentPro/proDetails";
	}
	
 
	/**
	 * 获取中央投资项目
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "search")
	public String search(Project project, HttpServletRequest request,String keyWord, String keyValue,HttpServletResponse response, Model model,HttpSession session) {
		project.setStatus(ProConstant.PROJECT_STATUS_PASS);
		if(keyWord!=null){
			if(keyWord.equals("1")){
				project.setName(keyValue);
			}else if(keyWord.equals("3")){
				 project.setBiddingAgency(keyValue);
			}	
		}
		
		 
		Page<Project> page = projectService.findPage(new Page<Project>(request, response), project); 
		model.addAttribute("page", page);
		model.addAttribute("project", project);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("keyValue", keyValue);
		return "center/investmentPro/proList";
	}
	
	 
	
}