/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 商家集市Entity
 * @author harry
 * @version 2018-03-31
 */
public class PedlarMarket extends DataEntity<PedlarMarket> {
	
	private static final long serialVersionUID = 1L;
	private Integer marketId;		// 集市
	private Integer pedlarId;		// 商家
	private String isDefault;		// 是否默认
	private Integer sort;		// 顺序号
	
	public PedlarMarket() {
		super();
	}

	public PedlarMarket(String id){
		super(id);
	}

	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	
	public Integer getPedlarId() {
		return pedlarId;
	}

	public void setPedlarId(Integer pedlarId) {
		this.pedlarId = pedlarId;
	}
	
	@Length(min=0, max=1, message="是否默认长度必须介于 0 和 1 之间")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}