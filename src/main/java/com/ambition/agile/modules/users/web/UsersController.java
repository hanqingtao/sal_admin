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
import com.ambition.agile.modules.users.entity.Users;
import com.ambition.agile.modules.users.service.UsersService;

/**
 * 用户信息Controller
 * @author harry
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/users/users")
public class UsersController extends BaseController {

	@Autowired
	private UsersService usersService;
	
	@ModelAttribute
	public Users get(@RequestParam(required=false) String id) {
		Users entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = usersService.get(id);
		}
		if (entity == null){
			entity = new Users();
		}
		return entity;
	}
	
	@RequiresPermissions("users:users:view")
	@RequestMapping(value = {"list", ""})
	public String list(Users users, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Users> page = usersService.findPage(new Page<Users>(request, response), users); 
		model.addAttribute("page", page);
		return "modules/users/usersList";
	}

	@RequiresPermissions("users:users:view")
	@RequestMapping(value = "form")
	public String form(Users users, Model model) {
		model.addAttribute("users", users);
		return "modules/users/usersForm";
	}

	@RequiresPermissions("users:users:edit")
	@RequestMapping(value = "save")
	public String save(Users users, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, users)){
			return form(users, model);
		}
		usersService.save(users);
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/users/users/?repage";
	}
	
	@RequiresPermissions("users:users:edit")
	@RequestMapping(value = "delete")
	public String delete(Users users, RedirectAttributes redirectAttributes) {
		usersService.delete(users);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/users/users/?repage";
	}

}