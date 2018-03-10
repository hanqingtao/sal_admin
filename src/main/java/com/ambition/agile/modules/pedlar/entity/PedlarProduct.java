/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 商贩产品信息Entity
 * @author harry
 * @version 2018-03-10
 */
public class PedlarProduct extends DataEntity<PedlarProduct> {
	
	private static final long serialVersionUID = 1L;
	private Integer catalogId;		// 商品分类
	private String catalogName;		// 分类名称
	private Integer pedlarId;		// 商贩 id
	private Integer createId;		// 创建者
	
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
	
	@Length(min=0, max=32, message="分类名称长度必须介于 0 和 32 之间")
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	public Integer getPedlarId() {
		return pedlarId;
	}

	public void setPedlarId(Integer pedlarId) {
		this.pedlarId = pedlarId;
	}
	
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
}