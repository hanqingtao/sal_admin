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
 * @version 2018-03-10
 */
public class ProductCatalog extends TreeEntity<ProductCatalog> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品分类名称
	private String code;		// 商品分类唯一编号标识
	private ProductCatalog parent;		// 父商品分类
	private String parentName;		// 父商品分类名称
	private Integer orderNum;		// 排序字段
	
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
	
	@Length(min=0, max=64, message="商品分类唯一编号标识长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonBackReference
	public ProductCatalog getParent() {
		return parent;
	}

	public void setParent(ProductCatalog parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=32, message="父商品分类名称长度必须介于 0 和 32 之间")
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}