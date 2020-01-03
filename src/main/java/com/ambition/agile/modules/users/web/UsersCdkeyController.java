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
import com.ambition.agile.modules.users.entity.UsersCdkey;
import com.ambition.agile.modules.users.service.UsersCdkeyService;

/**
 * 用户激活码关系Controller
 * @author harry
 * @version 2020-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/users/usersCdkey")
public class UsersCdkeyController extends BaseController {

	@Autowired
	private UsersCdkeyService cdkeyUsersService;
	
	@ModelAttribute
	public UsersCdkey get(@RequestParam(required=false) String id) {
		UsersCdkey entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cdkeyUsersService.get(id);
		}
		if (entity == null){
			entity = new UsersCdkey();
		}
		return entity;
	}
	
	@RequiresPermissions("users:cdkeyUsers:view")
	@RequestMapping(value = {"list", ""})
	public String list(UsersCdkey cdkeyUsers, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UsersCdkey> page = cdkeyUsersService.findPage(new Page<UsersCdkey>(request, response), cdkeyUsers); 
		model.addAttribute("page", page);
		return "modules/users/cdkeyUsersList";
	}

	@RequiresPermissions("users:cdkeyUsers:view")
	@RequestMapping(value = "form")
	public String form(UsersCdkey usersCdkey, Model model) {
		model.addAttribute("cdkeyUsers", usersCdkey);
		return "modules/users/cdkeyUsersForm";
	}

	@RequiresPermissions("users:cdkeyUsers:edit")
	@RequestMapping(value = "save")
	public String save(UsersCdkey usersCdkey, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, usersCdkey)){
			return form(usersCdkey, model);
		}
		cdkeyUsersService.save(usersCdkey);
		addMessage(redirectAttributes, "保存用户激活码关系成功");
		return "redirect:"+Global.getAdminPath()+"/users/cdkeyUsers/?repage";
	}
	
	@RequiresPermissions("users:cdkeyUsers:edit")
	@RequestMapping(value = "delete")
	public String delete(UsersCdkey usersCdkey, RedirectAttributes redirectAttributes) {
		cdkeyUsersService.delete(usersCdkey);
		addMessage(redirectAttributes, "删除用户激活码关系成功");
		return "redirect:"+Global.getAdminPath()+"/users/cdkeyUsers/?repage";
	}

}