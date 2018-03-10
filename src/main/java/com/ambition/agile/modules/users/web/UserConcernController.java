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
import com.ambition.agile.modules.users.entity.UserConcern;
import com.ambition.agile.modules.users.service.UserConcernService;

/**
 * 我的关注Controller
 * @author harry
 * @version 2018-03-10
 */
@Controller
@RequestMapping(value = "${adminPath}/users/userConcern")
public class UserConcernController extends BaseController {

	@Autowired
	private UserConcernService userConcernService;
	
	@ModelAttribute
	public UserConcern get(@RequestParam(required=false) String id) {
		UserConcern entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userConcernService.get(id);
		}
		if (entity == null){
			entity = new UserConcern();
		}
		return entity;
	}
	
	@RequiresPermissions("users:userConcern:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserConcern userConcern, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserConcern> page = userConcernService.findPage(new Page<UserConcern>(request, response), userConcern); 
		model.addAttribute("page", page);
		return "modules/users/userConcernList";
	}

	@RequiresPermissions("users:userConcern:view")
	@RequestMapping(value = "form")
	public String form(UserConcern userConcern, Model model) {
		model.addAttribute("userConcern", userConcern);
		return "modules/users/userConcernForm";
	}

	@RequiresPermissions("users:userConcern:edit")
	@RequestMapping(value = "save")
	public String save(UserConcern userConcern, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userConcern)){
			return form(userConcern, model);
		}
		userConcernService.save(userConcern);
		addMessage(redirectAttributes, "保存我的关注成功");
		return "redirect:"+Global.getAdminPath()+"/users/userConcern/?repage";
	}
	
	@RequiresPermissions("users:userConcern:edit")
	@RequestMapping(value = "delete")
	public String delete(UserConcern userConcern, RedirectAttributes redirectAttributes) {
		userConcernService.delete(userConcern);
		addMessage(redirectAttributes, "删除我的关注成功");
		return "redirect:"+Global.getAdminPath()+"/users/userConcern/?repage";
	}

}