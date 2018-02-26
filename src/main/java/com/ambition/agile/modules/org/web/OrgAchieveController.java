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
import com.ambition.agile.modules.org.entity.OrgAchieve;
import com.ambition.agile.modules.org.service.OrgAchieveService;

/**
 * 代理机构招标业绩Controller
 * @author harry
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/org/orgAchieve")
public class OrgAchieveController extends BaseController {

	@Autowired
	private OrgAchieveService orgAchieveService;
	
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
	
	@RequiresPermissions("org:orgAchieve:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgAchieve orgAchieve, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrgAchieve> page = orgAchieveService.findPage(new Page<OrgAchieve>(request, response), orgAchieve); 
		model.addAttribute("page", page);
		return "modules/org/orgAchieveList";
	}

	@RequiresPermissions("org:orgAchieve:view")
	@RequestMapping(value = "form")
	public String form(OrgAchieve orgAchieve, Model model) {
		model.addAttribute("orgAchieve", orgAchieve);
		return "modules/org/orgAchieveForm";
	}

	@RequiresPermissions("org:orgAchieve:edit")
	@RequestMapping(value = "save")
	public String save(OrgAchieve orgAchieve, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgAchieve)){
			return form(orgAchieve, model);
		}
		orgAchieveService.save(orgAchieve);
		addMessage(redirectAttributes, "保存代理机构招标业绩成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgAchieve/?repage";
	}
	
	@RequiresPermissions("org:orgAchieve:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgAchieve orgAchieve, RedirectAttributes redirectAttributes) {
		orgAchieveService.delete(orgAchieve);
		addMessage(redirectAttributes, "删除代理机构招标业绩成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgAchieve/?repage";
	}

}