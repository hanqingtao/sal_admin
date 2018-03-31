/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 商贩产品信息Entity
 * @author harry
 * @version 2018-03-31
 */
public class PedlarProduct extends DataEntity<PedlarProduct> {
	
	private static final long serialVersionUID = 1L;
	private Integer catalogId;		// 商品分类
	private Integer pedlarId;		// 商家
	private Integer sort;		// 顺序号
	
	public PedlarProduct() {
		super();
	}

	public PedlarProduct(String id){
		super(id);
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	
	public Integer getPedlarId() {
		return pedlarId;
	}

	public void setPedlarId(Integer pedlarId) {
		this.pedlarId = pedlarId;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}