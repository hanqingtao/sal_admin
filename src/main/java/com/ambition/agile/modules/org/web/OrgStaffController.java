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
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.service.OrgStaffService;

/**
 * 代理机构专职人员Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/org/orgStaff")
public class OrgStaffController extends BaseController {

	@Autowired
	private OrgStaffService orgStaffService;
	
	@ModelAttribute
	public OrgStaff get(@RequestParam(required=false) String id) {
		OrgStaff entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgStaffService.get(id);
		}
		if (entity == null){
			entity = new OrgStaff();
		}
		return entity;
	}
	
	@RequiresPermissions("org:orgStaff:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgStaff orgStaff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgStaff> page = orgStaffService.findPage(new Page<OrgStaff>(request, response), orgStaff); 
		model.addAttribute("page", page);
		return "modules/org/orgStaffList";
	}

	@RequiresPermissions("org:orgStaff:view")
	@RequestMapping(value = "form")
	public String form(OrgStaff orgStaff, Model model) {
		model.addAttribute("orgStaff", orgStaff);
		return "modules/org/orgStaffForm";
	}

	@RequiresPermissions("org:orgStaff:edit")
	@RequestMapping(value = "save")
	public String save(OrgStaff orgStaff, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgStaff)){
			return form(orgStaff, model);
		}
		orgStaffService.save(orgStaff);
		addMessage(redirectAttributes, "保存代理机构专职人员成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgStaff/?repage";
	}
	
	@RequiresPermissions("org:orgStaff:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgStaff orgStaff, RedirectAttributes redirectAttributes) {
		orgStaffService.delete(orgStaff);
		addMessage(redirectAttributes, "删除代理机构专职人员成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgStaff/?repage";
	}

}