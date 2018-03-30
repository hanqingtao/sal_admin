/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.TreeService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.product.entity.ProductCatalog;
import com.ambition.agile.modules.product.dao.ProductCatalogDao;

/**
 * 商品分类Service
 * @author harry
 * @version 2018-03-10
 */
@Service
@Transactional(readOnly = true)
public class ProductCatalogService extends TreeService<ProductCatalogDao, ProductCatalog> {

	public ProductCatalog get(String id) {
		return super.get(id);
	}
	
	public List<ProductCatalog> findList(ProductCatalog productCatalog) {
		if (StringUtils.isNotBlank(productCatalog.getParentIds())){
			productCatalog.setParentIds(","+productCatalog.getParentIds()+",");
		}
		return super.findList(productCatalog);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductCatalog productCatalog) {
		super.save(productCatalog);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductCatalog productCatalog) {
		super.delete(productCatalog);
	}
	
}