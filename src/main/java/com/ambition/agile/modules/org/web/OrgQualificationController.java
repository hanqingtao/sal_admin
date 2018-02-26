/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.web;

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
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.service.OrgQualificationService;

/**
 * 代理机构资格Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/org/orgQualification")
public class OrgQualificationController extends BaseController {

	@Autowired
	private OrgQualificationService orgQualificationService;
	
	@ModelAttribute
	public OrgQualification get(@RequestParam(required=false) String id) {
		OrgQualification entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgQualificationService.get(id);
		}
		if (entity == null){
			entity = new OrgQualification();
		}
		return entity;
	}
	
	@RequiresPermissions("org:orgQualification:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgQualification orgQualification, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgQualification> page = orgQualificationService.findPage(new Page<OrgQualification>(request, response), orgQualification); 
		model.addAttribute("page", page);
		return "modules/org/orgQualificationList";
	}

	@RequiresPermissions("org:orgQualification:view")
	@RequestMapping(value = "form")
	public String form(OrgQualification orgQualification, Model model) {
		model.addAttribute("orgQualification", orgQualification);
		return "modules/org/orgQualificationForm";
	}

	@RequiresPermissions("org:orgQualification:edit")
	@RequestMapping(value = "save")
	public String save(OrgQualification orgQualification, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgQualification)){
			return form(orgQualification, model);
		}
		orgQualificationService.save(orgQualification);
		addMessage(redirectAttributes, "保存代理机构资格成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgQualification/?repage";
	}
	
	@RequiresPermissions("org:orgQualification:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgQualification orgQualification, RedirectAttributes redirectAttributes) {
		orgQualificationService.delete(orgQualification);
		addMessage(redirectAttributes, "删除代理机构资格成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgQualification/?repage";
	}

}