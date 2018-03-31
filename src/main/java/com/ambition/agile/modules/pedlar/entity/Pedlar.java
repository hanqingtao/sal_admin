/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.entity;

import org.hibernate.validator.constraints.Length;
import com.ambition.agile.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 商家信息Entity
 * @author harry
 * @version 2018-03-31
 */
public class Pedlar extends DataEntity<Pedlar> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商家名称
	private Integer marketId;		// 集市(默认)
	private User user;		// 用户
	private String marketName;		// 集市名称
	private String logoPath;		// 商贩logo
	private String mainProduct;		// 主营商品
	private String content;		// 推荐描述
	private String status;		// 状态
	private User auditBy;		// 审核人
	private Date auditDate;		// 审核时间
	
	public Pedlar() {
		super();
	}

	public Pedlar(String id){
		super(id);
	}

	@Length(min=0, max=32, message="商家名称长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=32, message="集市名称长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public User getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(User auditBy) {
		this.auditBy = auditBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}