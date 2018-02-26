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
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAudit;
 
import com.ambition.agile.modules.org.service.OrgAuditService;
import com.ambition.agile.modules.org.service.OrgService;


/**
 * 代理机构专职人员Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/org/orgAudit")
public class OrgAuditController extends BaseController {

	@Autowired
	private OrgAuditService orgAuditService;
	
	@Autowired
	private OrgService orgService;
	
	@ModelAttribute
	public OrgAudit get(@RequestParam(required=false) String id) {
		OrgAudit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgAuditService.get(id);
		}
		if (entity == null){
			entity = new OrgAudit();
		}
		return entity;
	}
	
	@RequiresPermissions("org:orgAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgAudit orgAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgAudit> page = orgAuditService.findPage(new Page<OrgAudit>(request, response), orgAudit); 
		model.addAttribute("page", page);
		return "modules/org/orgAuditList";
	}

	//@RequiresPermissions("org:orgAudit:view")
	@RequestMapping(value = "form")
	public String form(OrgAudit orgAudit, Model model) {
		model.addAttribute("orgAudit", orgAudit);
		return "modules/org/orgAuditForm";
	}

	//@RequiresPermissions("org:orgAudit:edit")
	@RequestMapping(value = "save")
	public String save(OrgAudit orgAudit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgAudit)){
			return form(orgAudit, model);
		}
		
		orgAuditService.save(orgAudit);
		addMessage(redirectAttributes, "保存成功");
		Org org=orgService.get(String.valueOf(orgAudit.getOrgId()));
		org.setStatus(orgAudit.getOrgStatus());
		orgService.save(org);
		return "redirect:"+Global.getAdminPath()+"/org/org/listApprove";
	}
	
	@RequiresPermissions("org:orgAudit:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgAudit orgAudit, RedirectAttributes redirectAttributes) {
		orgAuditService.delete(orgAudit);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgAudit/?repage";
	}

}