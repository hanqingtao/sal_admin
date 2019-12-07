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
import com.ambition.agile.modules.users.entity.Batch;
import com.ambition.agile.modules.users.service.BatchService;

/**
 * 批次Controller
 * @author harry
 * @version 2019-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/users/batch")
public class BatchController extends BaseController {

	@Autowired
	private BatchService batchService;
	
	@ModelAttribute
	public Batch get(@RequestParam(required=false) String id) {
		Batch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = batchService.get(id);
		}
		if (entity == null){
			entity = new Batch();
		}
		return entity;
	}
	
	@RequiresPermissions("users:batch:view")
	@RequestMapping(value = {"list", ""})
	public String list(Batch batch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Batch> page = batchService.findPage(new Page<Batch>(request, response), batch); 
		model.addAttribute("page", page);
		return "modules/users/batchList";
	}

	@RequiresPermissions("users:batch:view")
	@RequestMapping(value = "form")
	public String form(Batch batch, Model model) {
		model.addAttribute("batch", batch);
		return "modules/users/batchForm";
	}

	@RequiresPermissions("users:batch:edit")
	@RequestMapping(value = "save")
	public String save(Batch batch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, batch)){
			return form(batch, model);
		}
		batchService.save(batch);
		addMessage(redirectAttributes, "保存批次成功");
		return "redirect:"+Global.getAdminPath()+"/users/batch/?repage";
	}
	
	@RequiresPermissions("users:batch:edit")
	@RequestMapping(value = "delete")
	public String delete(Batch batch, RedirectAttributes redirectAttributes) {
		batchService.delete(batch);
		addMessage(redirectAttributes, "删除批次成功");
		return "redirect:"+Global.getAdminPath()+"/users/batch/?repage";
	}

}