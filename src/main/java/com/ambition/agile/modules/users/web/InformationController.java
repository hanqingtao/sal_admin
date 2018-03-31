/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.web;

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
import com.ambition.agile.modules.users.entity.Information;
import com.ambition.agile.modules.users.service.InformationService;

/**
 * 实时信息Controller
 * @author harry
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/users/information")
public class InformationController extends BaseController {

	@Autowired
	private InformationService informationService;
	
	@ModelAttribute
	public Information get(@RequestParam(required=false) String id) {
		Information entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = informationService.get(id);
		}
		if (entity == null){
			entity = new Information();
		}
		return entity;
	}
	
	@RequiresPermissions("users:information:view")
	@RequestMapping(value = {"list", ""})
	public String list(Information information, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Information> page = informationService.findPage(new Page<Information>(request, response), information); 
		model.addAttribute("page", page);
		return "modules/users/informationList";
	}

	@RequiresPermissions("users:information:view")
	@RequestMapping(value = "form")
	public String form(Information information, Model model) {
		model.addAttribute("information", information);
		return "modules/users/informationForm";
	}

	@RequiresPermissions("users:information:edit")
	@RequestMapping(value = "save")
	public String save(Information information, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, information)){
			return form(information, model);
		}
		informationService.save(information);
		addMessage(redirectAttributes, "保存实时信息成功");
		return "redirect:"+Global.getAdminPath()+"/users/information/?repage";
	}
	
	@RequiresPermissions("users:information:edit")
	@RequestMapping(value = "delete")
	public String delete(Information information, RedirectAttributes redirectAttributes) {
		informationService.delete(information);
		addMessage(redirectAttributes, "删除实时信息成功");
		return "redirect:"+Global.getAdminPath()+"/users/information/?repage";
	}

}