/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.product.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.product.entity.ProductCatalog;
import com.ambition.agile.modules.product.service.ProductCatalogService;

/**
 * 商品分类Controller
 * @author harry
 * @version 2018-03-10
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productCatalog")
public class ProductCatalogController extends BaseController {

	@Autowired
	private ProductCatalogService productCatalogService;
	
	@ModelAttribute
	public ProductCatalog get(@RequestParam(required=false) String id) {
		ProductCatalog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productCatalogService.get(id);
		}
		if (entity == null){
			entity = new ProductCatalog();
		}
		return entity;
	}
	
	@RequiresPermissions("product:productCatalog:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductCatalog productCatalog, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ProductCatalog> list = productCatalogService.findList(productCatalog); 
		model.addAttribute("list", list);
		return "modules/product/productCatalogList";
	}

	@RequiresPermissions("product:productCatalog:view")
	@RequestMapping(value = "form")
	public String form(ProductCatalog productCatalog, Model model) {
		if (productCatalog.getParent()!=null && StringUtils.isNotBlank(productCatalog.getParent().getId())){
			productCatalog.setParent(productCatalogService.get(productCatalog.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(productCatalog.getId())){
				ProductCatalog productCatalogChild = new ProductCatalog();
				productCatalogChild.setParent(new ProductCatalog(productCatalog.getParent().getId()));
				List<ProductCatalog> list = productCatalogService.findList(productCatalog); 
				if (list.size() > 0){
					productCatalog.setSort(list.get(list.size()-1).getSort());
					if (productCatalog.getSort() != null){
						productCatalog.setSort(productCatalog.getSort() + 30);
					}
				}
			}
		}
		if (productCatalog.getSort() == null){
			productCatalog.setSort(30);
		}
		model.addAttribute("productCatalog", productCatalog);
		return "modules/product/productCatalogForm";
	}

	@RequiresPermissions("product:productCatalog:edit")
	@RequestMapping(value = "save")
	public String save(ProductCatalog productCatalog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productCatalog)){
			return form(productCatalog, model);
		}
		productCatalogService.save(productCatalog);
		addMessage(redirectAttributes, "保存商品分类成功");
		return "redirect:"+Global.getAdminPath()+"/product/productCatalog/?repage";
	}
	
	@RequiresPermissions("product:productCatalog:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductCatalog productCatalog, RedirectAttributes redirectAttributes) {
		productCatalogService.delete(productCatalog);
		addMessage(redirectAttributes, "删除商品分类成功");
		return "redirect:"+Global.getAdminPath()+"/product/productCatalog/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProductCatalog> list = productCatalogService.findList(new ProductCatalog());
		for (int i=0; i<list.size(); i++){
			ProductCatalog e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}