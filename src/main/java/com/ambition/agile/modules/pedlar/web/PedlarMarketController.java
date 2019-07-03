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
import com.ambition.agile.modules.pedlar.entity.PedlarMarket;
import com.ambition.agile.modules.pedlar.service.PedlarMarketService;

/**
 * 商家集市Controller
 * @author harry
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pedlar/pedlarMarket")
public class PedlarMarketController extends BaseController {

	@Autowired
	private PedlarMarketService pedlarMarketService;
	
	@ModelAttribute
	public PedlarMarket get(@RequestParam(required=false) String id) {
		PedlarMarket entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pedlarMarketService.get(id);
		}
		if (entity == null){
			entity = new PedlarMarket();
		}
		return entity;
	}
	
	@RequiresPermissions("pedlar:pedlarMarket:view")
	@RequestMapping(value = {"list", ""})
	public String list(PedlarMarket pedlarMarket, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PedlarMarket> page = pedlarMarketService.findPage(new Page<PedlarMarket>(request, response), pedlarMarket); 
		model.addAttribute("page", page);
		return "modules/pedlar/pedlarMarketList";
	}

	@RequiresPermissions("pedlar:pedlarMarket:view")
	@RequestMapping(value = "form")
	public String form(PedlarMarket pedlarMarket, Model model) {
		model.addAttribute("pedlarMarket", pedlarMarket);
		return "modules/pedlar/pedlarMarketForm";
	}

	@RequiresPermissions("pedlar:pedlarMarket:edit")
	@RequestMapping(value = "save")
	public String save(PedlarMarket pedlarMarket, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pedlarMarket)){
			return form(pedlarMarket, model);
		}
		pedlarMarketService.save(pedlarMarket);
		addMessage(redirectAttributes, "保存商家集市成功");
		return "redirect:"+Global.getAdminPath()+"/pedlar/pedlarMarket/?repage";
	}
	
	@RequiresPermissions("pedlar:pedlarMarket:edit")
	@RequestMapping(value = "delete")
	public String delete(PedlarMarket pedlarMarket, RedirectAttributes redirectAttributes) {
		pedlarMarketService.delete(pedlarMarket);
		addMessage(redirectAttributes, "删除商家集市成功");
		return "redirect:"+Global.getAdminPath()+"/pedlar/pedlarMarket/?repage";
	}

}