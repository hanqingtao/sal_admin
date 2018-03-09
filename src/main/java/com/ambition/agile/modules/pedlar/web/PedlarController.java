/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.web;

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
import com.ambition.agile.modules.pedlar.entity.Pedlar;
import com.ambition.agile.modules.pedlar.service.PedlarService;

/**
 * 商贩信息Controller
 * @author harry
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/pedlar/pedlar")
public class PedlarController extends BaseController {

	@Autowired
	private PedlarService pedlarService;
	
	@ModelAttribute
	public Pedlar get(@RequestParam(required=false) String id) {
		Pedlar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pedlarService.get(id);
		}
		if (entity == null){
			entity = new Pedlar();
		}
		return entity;
	}
	
	@RequiresPermissions("pedlar:pedlar:view")
	@RequestMapping(value = {"list", ""})
	public String list(Pedlar pedlar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Pedlar> page = pedlarService.findPage(new Page<Pedlar>(request, response), pedlar); 
		model.addAttribute("page", page);
		return "modules/pedlar/pedlarList";
	}

	@RequiresPermissions("pedlar:pedlar:view")
	@RequestMapping(value = "form")
	public String form(Pedlar pedlar, Model model) {
		model.addAttribute("pedlar", pedlar);
		return "modules/pedlar/pedlarForm";
	}

	@RequiresPermissions("pedlar:pedlar:edit")
	@RequestMapping(value = "save")
	public String save(Pedlar pedlar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pedlar)){
			return form(pedlar, model);
		}
		pedlarService.save(pedlar);
		addMessage(redirectAttributes, "保存商贩信息成功");
		return "redirect:"+Global.getAdminPath()+"/pedlar/pedlar/?repage";
	}
	
	@RequiresPermissions("pedlar:pedlar:edit")
	@RequestMapping(value = "delete")
	public String delete(Pedlar pedlar, RedirectAttributes redirectAttributes) {
		pedlarService.delete(pedlar);
		addMessage(redirectAttributes, "删除商贩信息成功");
		return "redirect:"+Global.getAdminPath()+"/pedlar/pedlar/?repage";
	}

}