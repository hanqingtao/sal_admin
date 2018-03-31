/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.market.entity;

import org.hibernate.validator.constraints.Length;
import com.ambition.agile.modules.sys.entity.Area;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 集市信息管理Entity
 * @author harry
 * @version 2018-03-31
 */
public class Market extends DataEntity<Market> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 集市名称
	private String address;		// 地址
	private String photoPath;		// 集市全景观图
	private String content;		// 简介
	private Area areap;		// 省
	private Area areac;		// 市
	private Area areaa;		// 县
	private Area areat;		// 镇
	private String areaFullName;		// 省市县集合名称
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private Integer clickCount;		// 多少人去过
	private Integer collectCount;		// 关注数
	private String recommend;		// 推荐指数
	private String mainProduct;		// 主营产品
	
	public Market() {
		super();
	}

	public Market(String id){
		super(id);
	}

	@Length(min=0, max=32, message="集市名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="地址长度必须介于 0 和 256 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="集市全景观图长度必须介于 0 和 64 之间")
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	@Length(min=0, max=256, message="简介长度必须介于 0 和 256 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Area getAreap() {
		return areap;
	}

	public void setAreap(Area areap) {
		this.areap = areap;
	}
	
	public Area getAreac() {
		return areac;
	}

	public void setAreac(Area areac) {
		this.areac = areac;
	}
	
	public Area getAreaa() {
		return areaa;
	}

	public void setAreaa(Area areaa) {
		this.areaa = areaa;
	}
	
	public Area getAreat() {
		return areat;
	}

	public void setAreat(Area areat) {
		this.areat = areat;
	}
	
	@Length(min=0, max=128, message="省市县集合名称长度必须介于 0 和 128 之间")
	public String getAreaFullName() {
		return areaFullName;
	}

	public void setAreaFullName(String areaFullName) {
		this.areaFullName = areaFullName;
	}
	
	@Length(min=0, max=16, message="经度长度必须介于 0 和 16 之间")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=16, message="纬度长度必须介于 0 和 16 之间")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	
	@Length(min=0, max=1, message="推荐指数长度必须介于 0 和 1 之间")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	@Length(min=0, max=128, message="主营产品长度必须介于 0 和 128 之间")
	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	
}