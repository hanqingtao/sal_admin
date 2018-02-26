/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.web;

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
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.pro.entity.ProjectFlow;
import com.ambition.agile.modules.pro.service.ProjectFlowService;

/**
 * 项目流程Controller
 * @author harry
 * @version 2017-08-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/projectFlow")
public class ProjectFlowController extends BaseController {

	@Autowired
	private ProjectFlowService projectFlowService;
	
	@ModelAttribute
	public ProjectFlow get(@RequestParam(required=false) String id) {
		ProjectFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectFlowService.get(id);
		}
		if (entity == null){
			entity = new ProjectFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:projectFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectFlow projectFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectFlow> page = projectFlowService.findPage(new Page<ProjectFlow>(request, response), projectFlow); 
		model.addAttribute("page", page);
		return "modules/pro/projectFlowList";
	}

	@RequiresPermissions("pro:projectFlow:view")
	@RequestMapping(value = "form")
	public String form(ProjectFlow projectFlow, Model model) {
		model.addAttribute("projectFlow", projectFlow);
		return "modules/pro/projectFlowForm";
	}
	/**
	 * 点击项目，进入项目流程编辑页面
	 * @param projectFlow
	 * @param model
	 * @return
	 */
	@RequiresPermissions("pro:project:view")
	@RequestMapping(value = "formByProId")
	public String formByProId(ProjectFlow projectFlow, Model model) {
		projectFlow = projectFlowService.findInfoByProId(projectFlow.getProjectId());
		model.addAttribute("projectFlow", projectFlow);
		return "modules/pro/projectFlowForm";
	}

	@RequiresPermissions("pro:projectFlow:edit")
	@RequestMapping(value = "save")
	public String save(ProjectFlow projectFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectFlow)){
			return form(projectFlow, model);
		}
		projectFlowService.save(projectFlow);
		addMessage(redirectAttributes, "保存项目流程成功");
		return "redirect:"+Global.getAdminPath()+"/pro/projectFlow/?repage";
	}
	
	@RequiresPermissions("pro:projectFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectFlow projectFlow, RedirectAttributes redirectAttributes) {
		projectFlowService.delete(projectFlow);
		addMessage(redirectAttributes, "删除项目流程成功");
		return "redirect:"+Global.getAdminPath()+"/pro/projectFlow/?repage";
	}

}