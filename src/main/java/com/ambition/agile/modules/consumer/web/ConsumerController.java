/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.consumer.web;

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
import com.ambition.agile.modules.consumer.entity.Consumer;
import com.ambition.agile.modules.consumer.service.ConsumerService;

/**
 * 用户信息Controller
 * @author harry
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/consumer/consumer")
public class ConsumerController extends BaseController {

	@Autowired
	private ConsumerService consumerService;
	
	@ModelAttribute
	public Consumer get(@RequestParam(required=false) String id) {
		Consumer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = consumerService.get(id);
		}
		if (entity == null){
			entity = new Consumer();
		}
		return entity;
	}
	
	@RequiresPermissions("consumer:consumer:view")
	@RequestMapping(value = {"list", ""})
	public String list(Consumer consumer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Consumer> page = consumerService.findPage(new Page<Consumer>(request, response), consumer); 
		model.addAttribute("page", page);
		return "modules/consumer/consumerList";
	}

	@RequiresPermissions("consumer:consumer:view")
	@RequestMapping(value = "form")
	public String form(Consumer consumer, Model model) {
		model.addAttribute("consumer", consumer);
		return "modules/consumer/consumerForm";
	}

	@RequiresPermissions("consumer:consumer:edit")
	@RequestMapping(value = "save")
	public String save(Consumer consumer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consumer)){
			return form(consumer, model);
		}
		consumerService.save(consumer);
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/consumer/consumer/?repage";
	}
	
	@RequiresPermissions("consumer:consumer:edit")
	@RequestMapping(value = "delete")
	public String delete(Consumer consumer, RedirectAttributes redirectAttributes) {
		consumerService.delete(consumer);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/consumer/consumer/?repage";
	}

}