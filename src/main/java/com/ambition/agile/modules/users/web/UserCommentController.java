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
import com.ambition.agile.modules.users.entity.UserComment;
import com.ambition.agile.modules.users.service.UserCommentService;

/**
 * 评价信息Controller
 * @author harry
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/users/userComment")
public class UserCommentController extends BaseController {

	@Autowired
	private UserCommentService userCommentService;
	
	@ModelAttribute
	public UserComment get(@RequestParam(required=false) String id) {
		UserComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userCommentService.get(id);
		}
		if (entity == null){
			entity = new UserComment();
		}
		return entity;
	}
	
	@RequiresPermissions("users:userComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserComment userComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserComment> page = userCommentService.findPage(new Page<UserComment>(request, response), userComment); 
		model.addAttribute("page", page);
		return "modules/users/userCommentList";
	}

	@RequiresPermissions("users:userComment:view")
	@RequestMapping(value = "form")
	public String form(UserComment userComment, Model model) {
		model.addAttribute("userComment", userComment);
		return "modules/users/userCommentForm";
	}

	@RequiresPermissions("users:userComment:edit")
	@RequestMapping(value = "save")
	public String save(UserComment userComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userComment)){
			return form(userComment, model);
		}
		userCommentService.save(userComment);
		addMessage(redirectAttributes, "保存评价信息成功");
		return "redirect:"+Global.getAdminPath()+"/users/userComment/?repage";
	}
	
	@RequiresPermissions("users:userComment:edit")
	@RequestMapping(value = "delete")
	public String delete(UserComment userComment, RedirectAttributes redirectAttributes) {
		userCommentService.delete(userComment);
		addMessage(redirectAttributes, "删除评价信息成功");
		return "redirect:"+Global.getAdminPath()+"/users/userComment/?repage";
	}

}