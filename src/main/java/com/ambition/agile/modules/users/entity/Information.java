/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 实时信息Entity
 * @author harry
 * @version 2018-03-31
 */
public class Information extends DataEntity<Information> {
	
	private static final long serialVersionUID = 1L;
	private Integer marketId;		// 集市
	private String content;		// 发布内容
	private String photoUrl;		// 图片地址(多张)
	private String productCatalogName;		// 商品分类
	private String productCatalogId;		// 商品分类
	
	public Information() {
		super();
	}

	public Information(String id){
		super(id);
	}

	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	
	@Length(min=0, max=255, message="发布内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1024, message="图片地址(多张)长度必须介于 0 和 1024 之间")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Length(min=0, max=512, message="商品分类长度必须介于 0 和 512 之间")
	public String getProductCatalogName() {
		return productCatalogName;
	}

	public void setProductCatalogName(String productCatalogName) {
		this.productCatalogName = productCatalogName;
	}
	
	@Length(min=0, max=128, message="商品分类长度必须介于 0 和 128 之间")
	public String getProductCatalogId() {
		return productCatalogId;
	}

	public void setProductCatalogId(String productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	
}