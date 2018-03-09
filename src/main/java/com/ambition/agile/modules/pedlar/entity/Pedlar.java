/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.entity;

import org.hibernate.validator.constraints.Length;
import com.ambition.agile.modules.sys.entity.User;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 商贩信息Entity
 * @author harry
 * @version 2018-03-09
 */
public class Pedlar extends DataEntity<Pedlar> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商贩名称
	private Integer marketId;		// 所属的集市
	private User user;		// 用户id
	private String marketName;		// 所属集市名称
	private String logoPath;		// 商贩logo
	private String mainProduct;		// 主营商品
	private String content;		// 推荐描述
	
	public Pedlar() {
		super();
	}

	public Pedlar(String id){
		super(id);
	}

	@Length(min=0, max=32, message="商贩名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=32, message="所属集市名称长度必须介于 0 和 32 之间")
	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	
	@Length(min=0, max=64, message="商贩logo长度必须介于 0 和 64 之间")
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	@Length(min=0, max=128, message="主营商品长度必须介于 0 和 128 之间")
	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	
	@Length(min=0, max=128, message="推荐描述长度必须介于 0 和 128 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}