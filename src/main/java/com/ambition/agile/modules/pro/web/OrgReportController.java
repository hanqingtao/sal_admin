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
import com.ambition.agile.modules.pro.entity.OrgReport;
import com.ambition.agile.modules.pro.service.OrgReportService;

/**
 * 代理机构举报信息Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/pro/orgReport")
public class OrgReportController extends BaseController {

	@Autowired
	private OrgReportService orgReportService;
	
	@ModelAttribute
	public OrgReport get(@RequestParam(required=false) String id) {
		OrgReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgReportService.get(id);
		}
		if (entity == null){
			entity = new OrgReport();
		}
		return entity;
	}
	
	@RequiresPermissions("pro:orgReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgReport orgReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgReport> page = orgReportService.findPage(new Page<OrgReport>(request, response), orgReport); 
		model.addAttribute("page", page);
		return "modules/pro/orgReportList";
	}

	@RequiresPermissions("pro:orgReport:view")
	@RequestMapping(value = "form")
	public String form(OrgReport orgReport, Model model) {
		model.addAttribute("orgReport", orgReport);
		return "modules/pro/orgReportForm";
	}

	@RequiresPermissions("pro:orgReport:edit")
	@RequestMapping(value = "save")
	public String save(OrgReport orgReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgReport)){
			return form(orgReport, model);
		}
		orgReportService.save(orgReport);
		addMessage(redirectAttributes, "保存代理机构举报信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/orgReport/?repage";
	}
	
	@RequiresPermissions("pro:orgReport:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgReport orgReport, RedirectAttributes redirectAttributes) {
		orgReportService.delete(orgReport);
		addMessage(redirectAttributes, "删除代理机构举报信息成功");
		return "redirect:"+Global.getAdminPath()+"/pro/orgReport/?repage";
	}

}