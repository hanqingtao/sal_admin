/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.constant.OrgConstant;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.pro.constant.ProConstant;
import com.ambition.agile.modules.pro.entity.Engineer;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.pro.entity.ProjectFlow;
import com.ambition.agile.modules.pro.service.EngineerService;
import com.ambition.agile.modules.pro.service.ProjectFlowService;
import com.ambition.agile.modules.pro.service.ProjectService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 项目信息表Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectFlowService projectFlowService;
	
	@Autowired
	private EngineerService engineerService;
	
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
	
	@RequiresPermissions("pro:project:view")
	@RequestMapping(value = {"list", ""})
	public String list(Project project, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(null != user){
			
			if(project == null){
				project = new Project();
			}
//			Integer isSysM = Constants.YES;
//			if(!user.getId().equals(Constants.SYS_USER_ID)){
//				isSysM = Constants.NO;
//				project.setAreaId(Integer.parseInt(user.getOffice().getArea().getId()));
////				org.setArea(user.getOffice().getArea());
////				org.setStatus(OrgConstant.ORG_STATUS_SUBMIT);
//			}
			List<String> roleIdList = user.getRoleIdList();
			if(roleIdList != null && roleIdList.size() > 0){				
				// 默认为省级管理员
				boolean s_sys = true;
				for(String roleId : roleIdList){
					if(roleId.equals("5") ||roleId.equals("4") || roleId.equals("1") ){  // 招标中心管理员
						s_sys = false; break;
					}
				}
				if(s_sys){					
					project.setAreaId(Integer.parseInt(user.getOffice().getArea().getId()));
				}
			}
			
			
			
			project.setStatus(ProConstant.PROJECT_STATUS_PASS);
			Page<Project> page = projectService.findPage(new Page<Project>(request, response), project); 
			model.addAttribute("page", page);
			
		}
		
		return "modules/pro/projectList";
	}

	@RequiresPermissions("pro:project:view")
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/pro/projectForm";
	}

	@RequiresPermissions("pro:project:view")
	@RequestMapping(value = "details")
	public String details(Project project,ProjectFlow projectFlow,Engineer engineer,Model model) {
		model.addAttribute("project", projectService.get(project));
		 model.addAttribute("projectFlow", projectFlowService.findInfoByProId(Integer.valueOf(project.getId()))); 
		 model.addAttribute("engineer", engineerService.get(String.valueOf(project.getEngineerId())));
		return "modules/pro/projectDetails";
	}
	
	@RequiresPermissions("pro:project:edit")
	@RequestMapping(value = "save")
	public String save(Project project, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, project)){
			return form(project, model);
		}
		projectService.save(project);
		addMessage(redirectAttributes, "保存项目信息表成功");
		return "redirect:"+Global.getAdminPath()+"/pro/project/?repage";
	}
	
	@RequiresPermissions("pro:project:edit")
	@RequestMapping(value = "delete")
	public String delete(Project project, RedirectAttributes redirectAttributes) {
		projectService.delete(project);
		addMessage(redirectAttributes, "删除项目信息表成功");
		return "redirect:"+Global.getAdminPath()+"/pro/project/?repage";
	}

}