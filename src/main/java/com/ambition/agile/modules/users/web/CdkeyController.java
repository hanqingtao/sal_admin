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
import com.ambition.agile.modules.users.entity.Cdkey;
import com.ambition.agile.modules.users.service.CdkeyService;

/**
 * 激活码Controller
 * @author harry
 * @version 2019-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/users/cdkey")
public class CdkeyController extends BaseController {

	@Autowired
	private CdkeyService cdkeyService;
	
	@ModelAttribute
	public Cdkey get(@RequestParam(required=false) String id) {
		Cdkey entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cdkeyService.get(id);
		}
		if (entity == null){
			entity = new Cdkey();
		}
		return entity;
	}
	
	@RequiresPermissions("users:cdkey:view")
	@RequestMapping(value = {"list", ""})
	public String list(Cdkey cdkey, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Cdkey> page = cdkeyService.findPage(new Page<Cdkey>(request, response), cdkey); 
		model.addAttribute("page", page);
		return "modules/users/cdkeyList";
	}

	@RequiresPermissions("users:cdkey:view")
	@RequestMapping(value = "form")
	public String form(Cdkey cdkey, Model model) {
		model.addAttribute("cdkey", cdkey);
		return "modules/users/cdkeyForm";
	}

	@RequiresPermissions("users:cdkey:edit")
	@RequestMapping(value = "save")
	public String save(Cdkey cdkey, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cdkey)){
			return form(cdkey, model);
		}
		cdkeyService.save(cdkey);
		addMessage(redirectAttributes, "保存激活码成功");
		return "redirect:"+Global.getAdminPath()+"/users/cdkey/?repage";
	}
	
	@RequiresPermissions("users:cdkey:edit")
	@RequestMapping(value = "delete")
	public String delete(Cdkey cdkey, RedirectAttributes redirectAttributes) {
		cdkeyService.delete(cdkey);
		addMessage(redirectAttributes, "删除激活码成功");
		return "redirect:"+Global.getAdminPath()+"/users/cdkey/?repage";
	}

}