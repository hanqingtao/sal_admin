/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.product.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.ambition.agile.common.persistence.TreeEntity;

/**
 * 商品分类Entity
 * @author harry
 * @version 2018-03-31
 */
public class ProductCatalog extends TreeEntity<ProductCatalog> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品分类名称
	private ProductCatalog parent;		// 父分类ID
	private String parentIds;		// 所有父分类
	private Integer sort;		// 排序字段
	
	public ProductCatalog() {
		super();
	}

	public ProductCatalog(String id){
		super(id);
	}

	@Length(min=1, max=32, message="商品分类名称长度必须介于 1 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public ProductCatalog getParent() {
		return parent;
	}

	public void setParent(ProductCatalog parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=10, message="所有父分类长度必须介于 0 和 10 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}