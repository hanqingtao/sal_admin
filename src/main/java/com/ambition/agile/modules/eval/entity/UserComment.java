/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.eval.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 评价信息Entity
 * @author harry
 * @version 2018-03-10
 */
public class UserComment extends DataEntity<UserComment> {
	
	private static final long serialVersionUID = 1L;
	private Integer marketId;		// 集市id
	private String content;		// 评论内容
	private String evaluate;		// 评价 差评0  中评 1 好评 2
	private Integer createId;		// 创建人 id
	private String photoUrl1;		// 上传图片1
	private String createName;		// 创建人姓名
	private String isAnony;		// 是匿名
	private String photoUrl2;		// 上传图片2
	private String photoUrl3;		// 上传图片3
	private String photoUrl4;		// 上传图片4
	private String photoUrl5;		// 上传图片5
	private String photoUrl6;		// 上传图片6
	
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
	
	@Length(min=0, max=1, message="评价 差评0  中评 1 好评 2长度必须介于 0 和 1 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
	@Length(min=0, max=64, message="上传图片1长度必须介于 0 和 64 之间")
	public String getPhotoUrl1() {
		return photoUrl1;
	}

	public void setPhotoUrl1(String photoUrl1) {
		this.photoUrl1 = photoUrl1;
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
	
	@Length(min=0, max=64, message="上传图片2长度必须介于 0 和 64 之间")
	public String getPhotoUrl2() {
		return photoUrl2;
	}

	public void setPhotoUrl2(String photoUrl2) {
		this.photoUrl2 = photoUrl2;
	}
	
	@Length(min=0, max=64, message="上传图片3长度必须介于 0 和 64 之间")
	public String getPhotoUrl3() {
		return photoUrl3;
	}

	public void setPhotoUrl3(String photoUrl3) {
		this.photoUrl3 = photoUrl3;
	}
	
	@Length(min=0, max=64, message="上传图片4长度必须介于 0 和 64 之间")
	public String getPhotoUrl4() {
		return photoUrl4;
	}

	public void setPhotoUrl4(String photoUrl4) {
		this.photoUrl4 = photoUrl4;
	}
	
	@Length(min=0, max=64, message="上传图片5长度必须介于 0 和 64 之间")
	public String getPhotoUrl5() {
		return photoUrl5;
	}

	public void setPhotoUrl5(String photoUrl5) {
		this.photoUrl5 = photoUrl5;
	}
	
	@Length(min=0, max=64, message="上传图片6长度必须介于 0 和 64 之间")
	public String getPhotoUrl6() {
		return photoUrl6;
	}

	public void setPhotoUrl6(String photoUrl6) {
		this.photoUrl6 = photoUrl6;
	}
	
}