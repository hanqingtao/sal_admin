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
import com.ambition.agile.modules.pedlar.entity.PedlarProduct;
import com.ambition.agile.modules.pedlar.service.PedlarProductService;

/**
 * 商贩产品信息Controller
 * @author harry
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pedlar/pedlarProduct")
public class PedlarProductController extends BaseController {

	@Autowired
	private PedlarProductService pedlarProductService;
	
	@ModelAttribute
	public PedlarProduct get(@RequestParam(required=false) String id) {
		PedlarProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pedlarProductService.get(id);
		}
		if (entity == null){
			entity = new PedlarProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("pedlar:pedlarProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PedlarProduct pedlarProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PedlarProduct> page = pedlarProductService.findPage(new Page<PedlarProduct>(request, response), pedlarProduct); 
		model.addAttribute("page", page);
		return "modules/pedlar/pedlarProductList";
	}

	@RequiresPermissions("pedlar:pedlarProduct:view")
	@RequestMapping(value = "form")
	public String form(PedlarProduct pedlarProduct, Model model) {
		model.addAttribute("pedlarProduct", pedlarProduct);
		return "modules/pedlar/pedlarProductForm";
	}

	@RequiresPermissions("pedlar:pedlarProduct:edit")
	@RequestMapping(value = "save")
	public String save(PedlarProduct pedlarProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pedlarProduct)){
			return form(pedlarProduct, model);
		}
		pedlarProductService.save(pedlarProduct);
		addMessage(redirectAttributes, "保存商贩产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/pedlar/pedlarProduct/?repage";
	}
	
	@RequiresPermissions("pedlar:pedlarProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(PedlarProduct pedlarProduct, RedirectAttributes redirectAttributes) {
		pedlarProductService.delete(pedlarProduct);
		addMessage(redirectAttributes, "删除商贩产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/pedlar/pedlarProduct/?repage";
	}

}