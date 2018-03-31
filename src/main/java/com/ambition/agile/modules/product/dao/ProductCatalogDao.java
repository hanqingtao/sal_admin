/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.product.dao;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.product.entity.ProductCatalog;

/**
 * 商品分类DAO接口
 * @author harry
 * @version 2018-03-31
 */
@MyBatisDao
public interface ProductCatalogDao extends TreeDao<ProductCatalog> {
	
}