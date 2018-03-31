/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.market.web;

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
import com.ambition.agile.modules.market.entity.Market;
import com.ambition.agile.modules.market.service.MarketService;

/**
 * 集市信息管理Controller
 * @author harry
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/market/market")
public class MarketController extends BaseController {

	@Autowired
	private MarketService marketService;
	
	@ModelAttribute
	public Market get(@RequestParam(required=false) String id) {
		Market entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = marketService.get(id);
		}
		if (entity == null){
			entity = new Market();
		}
		return entity;
	}
	
	@RequiresPermissions("market:market:view")
	@RequestMapping(value = {"list", ""})
	public String list(Market market, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Market> page = marketService.findPage(new Page<Market>(request, response), market); 
		model.addAttribute("page", page);
		return "modules/market/marketList";
	}

	@RequiresPermissions("market:market:view")
	@RequestMapping(value = "form")
	public String form(Market market, Model model) {
		model.addAttribute("market", market);
		return "modules/market/marketForm";
	}

	@RequiresPermissions("market:market:edit")
	@RequestMapping(value = "save")
	public String save(Market market, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, market)){
			return form(market, model);
		}
		marketService.save(market);
		addMessage(redirectAttributes, "保存集市信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/market/market/?repage";
	}
	
	@RequiresPermissions("market:market:edit")
	@RequestMapping(value = "delete")
	public String delete(Market market, RedirectAttributes redirectAttributes) {
		marketService.delete(market);
		addMessage(redirectAttributes, "删除集市信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/market/market/?repage";
	}

}