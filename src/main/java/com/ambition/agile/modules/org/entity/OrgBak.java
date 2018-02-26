/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.Area;

/**
 * 代理机构Entity
 * @author harry
 * @version 2017-08-02
 */
public class OrgBak extends DataEntity<OrgBak> {
	
	private static final long serialVersionUID = 1L;
	private String sn;		// 编号备份
	private Integer orgId;		// 机构标识
	private String bakType;		// 备份类型 0首次备案 1 变更
	private String name;		// 企业名称
	private String scCode;		// 社会信用代码
	private Double regMoney;		// 注册资金
	private String regAddress;		// 通讯地址
	private String buslicePath;		// 营业执照地址
	private String email;		// 电子邮箱
	private Double totalAsset;		// 总资产
	private Double netAsset;		// 净资产
	private String logoPath;		// 单位图片
	private String status;		// 申报状态
	private Area area;		// 所属区域
	private String areaParentids;		// 区域code
	private String nature;		// 企业性质
	private String createId;		// 创建人标识
	private String type;		// 类型
	private String description;		// 企业简介
	private String legalrepName;		// 法人代表姓名
	private String legalrepCard;		// 法人代表身份证
	private String legalrepPhone;		// 法人联系电话
	private String legalrepAddress;		// 通信地址
	private String legalrepZipcode;		// 邮政编码
	private String legalrepPhoto;		// 法人证件图片
	private String socialinsuPhoto;		// 社保证明
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Double recentMoney;     //近三年总业绩
	
	private Date submitTime;		// 提交时间
	private Date auditTime;		// 省发改委审核时间
	private Date finalTime;		// 招标中心审核时间
	private Date bakTime;		// 
	
	private Integer yearSearch; // 年份（查询用）
	private Integer monthSearch;  // 月份（查询用）
	
	
	
	
	/**
	 * @return the bakTime
	 */
	public Date getBakTime() {
		return bakTime;
	}

	/**
	 * @param bakTime the bakTime to set
	 */
	public void setBakTime(Date bakTime) {
		this.bakTime = bakTime;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the bakType
	 */
	public String getBakType() {
		return bakType;
	}

	/**
	 * @param bakType the bakType to set
	 */
	public void setBakType(String bakType) {
		this.bakType = bakType;
	}

	/**
	 * @return the submitTime
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * @param submitTime the submitTime to set
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * @return the auditTime
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * @param auditTime the auditTime to set
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * @return the finalTime
	 */
	public Date getFinalTime() {
		return finalTime;
	}

	/**
	 * @param finalTime the finalTime to set
	 */
	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public Integer getYearSearch() {
		return yearSearch;
	}

	public void setYearSearch(Integer yearSearch) {
		this.yearSearch = yearSearch;
	}

	public Integer getMonthSearch() {
		return monthSearch;
	}

	public void setMonthSearch(Integer monthSearch) {
		this.monthSearch = monthSearch;
	}

	public OrgBak() {
		super();
	}

	public OrgBak(String id){
		super(id);
	}

	@Length(min=1, max=32, message="企业名称长度必须介于 1 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="社会信用代码长度必须介于 0 和 32 之间")
	public String getScCode() {
		return scCode;
	}

	public void setScCode(String scCode) {
		this.scCode = scCode;
	}
	
	public Double getRegMoney() {
		return regMoney;
	}

	public void setRegMoney(Double regMoney) {
		this.regMoney = regMoney;
	}
	
	@Length(min=0, max=128, message="通讯地址长度必须介于 0 和 128 之间")
	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	
	@Length(min=0, max=64, message="营业执照地址长度必须介于 0 和 64 之间")
	public String getBuslicePath() {
		return buslicePath;
	}

	public void setBuslicePath(String buslicePath) {
		this.buslicePath = buslicePath;
	}
	
	@Length(min=0, max=32, message="电子邮箱长度必须介于 0 和 32 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}
	
	public Double getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(Double netAsset) {
		this.netAsset = netAsset;
	}
	
	@Length(min=0, max=64, message="单位图片长度必须介于 0 和 64 之间")
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	@Length(min=0, max=1, message="申报状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@NotNull(message="所属区域不能为空")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=1, max=32, message="区域code长度必须介于 1 和 32 之间")
	public String getAreaParentids() {
		return areaParentids;
	}

	public void setAreaParentids(String areaParentids) {
		this.areaParentids = areaParentids;
	}
	
	@Length(min=0, max=1, message="企业性质长度必须介于 0 和 1 之间")
	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
	
	@Length(min=0, max=11, message="创建人标识长度必须介于 0 和 11 之间")
	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="企业简介长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=32, message="法人代表姓名长度必须介于 0 和 32 之间")
	public String getLegalrepName() {
		return legalrepName;
	}

	public void setLegalrepName(String legalrepName) {
		this.legalrepName = legalrepName;
	}
	
	@Length(min=0, max=18, message="法人代表身份证长度必须介于 0 和 18 之间")
	public String getLegalrepCard() {
		return legalrepCard;
	}

	public void setLegalrepCard(String legalrepCard) {
		this.legalrepCard = legalrepCard;
	}
	
	@Length(min=0, max=11, message="法人联系电话长度必须介于 0 和 11 之间")
	public String getLegalrepPhone() {
		return legalrepPhone;
	}

	public void setLegalrepPhone(String legalrepPhone) {
		this.legalrepPhone = legalrepPhone;
	}
	
	@Length(min=0, max=255, message="通信地址长度必须介于 0 和 255 之间")
	public String getLegalrepAddress() {
		return legalrepAddress;
	}

	public void setLegalrepAddress(String legalrepAddress) {
		this.legalrepAddress = legalrepAddress;
	}
	
	@Length(min=0, max=32, message="邮政编码长度必须介于 0 和 32 之间")
	public String getLegalrepZipcode() {
		return legalrepZipcode;
	}

	public void setLegalrepZipcode(String legalrepZipcode) {
		this.legalrepZipcode = legalrepZipcode;
	}
	
	@Length(min=0, max=32, message="法人证件图片长度必须介于 0 和 32 之间")
	public String getLegalrepPhoto() {
		return legalrepPhoto;
	}

	public void setLegalrepPhoto(String legalrepPhoto) {
		this.legalrepPhoto = legalrepPhoto;
	}
	
	@Length(min=0, max=32, message="社保证明长度必须介于 0 和 32 之间")
	public String getSocialinsuPhoto() {
		return socialinsuPhoto;
	}

	public void setSocialinsuPhoto(String socialinsuPhoto) {
		this.socialinsuPhoto = socialinsuPhoto;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Double getRecentMoney() {
		return recentMoney;
	}

	public void setRecentMoney(Double recentMoney) {
		this.recentMoney = recentMoney;
	}

	
	
}