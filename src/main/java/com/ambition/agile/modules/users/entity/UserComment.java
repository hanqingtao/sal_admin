/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 评价信息Entity
 * @author harry
 * @version 2018-03-31
 */
public class UserComment extends DataEntity<UserComment> {
	
	private static final long serialVersionUID = 1L;
	private Integer marketId;		// 集市
	private String content;		// 评论内容
	private String evaluate;		// 评价
	private String photoUrl;		// 上传图片
	private String createName;		// 创建人姓名
	private String isAnony;		// 是匿名
	
	public UserComment() {
		super();
	}

	public UserComment(String id){
		super(id);
	}

	public Integer getMarketId() {
		return marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="评价长度必须介于 0 和 1 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	@Length(min=0, max=1024, message="上传图片长度必须介于 0 和 1024 之间")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Length(min=0, max=32, message="创建人姓名长度必须介于 0 和 32 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	@Length(min=0, max=4, message="是匿名长度必须介于 0 和 4 之间")
	public String getIsAnony() {
		return isAnony;
	}

	public void setIsAnony(String isAnony) {
		this.isAnony = isAnony;
	}
	
}