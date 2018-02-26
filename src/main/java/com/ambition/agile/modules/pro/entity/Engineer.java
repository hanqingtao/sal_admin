/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.entity;

import org.hibernate.validator.constraints.Length;
import com.ambition.agile.modules.sys.entity.Area;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 工程信息表Entity
 * @author harry
 * @version 2017-08-03
 */
public class Engineer extends DataEntity<Engineer> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	private String owner;		// 项目业主
	private String reportNumber;		// 可研报告批准文件及文号
	private String approvalNumber;		// 核准文件及文号
	private String recordNumber;		// 备案文件及文号
	private String industry;		// 行业
	private String builtSite;		// 建设地点
	private String builtScale;		// 建设规模
	private Double totalMoney;		// 项目总投资
	private Double centralMoney;		// 中央投资额
	private String centralinvestType;		// 中央投资资金来源
	private String centraluseType;		// 中央投资资金使用方式
	private String centralinvestMoney;		// 中央投资资金数额
	private Area area;		// 地区标识
	private String createId;		// 创建人
	private Date createTime;		// 创建时间
	private String orgId;		// 机构标识
	
	public Engineer() {
		super();
	}

	public Engineer(String id){
		super(id);
	}

	@Length(min=0, max=32, message="项目名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="项目业主长度必须介于 0 和 32 之间")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Length(min=0, max=32, message="可研报告批准文件及文号长度必须介于 0 和 32 之间")
	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	
	@Length(min=0, max=32, message="核准文件及文号长度必须介于 0 和 32 之间")
	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	
	@Length(min=0, max=32, message="备案文件及文号长度必须介于 0 和 32 之间")
	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	@Length(min=0, max=2, message="行业长度必须介于 0 和 2 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Length(min=0, max=32, message="建设地点长度必须介于 0 和 32 之间")
	public String getBuiltSite() {
		return builtSite;
	}

	public void setBuiltSite(String builtSite) {
		this.builtSite = builtSite;
	}
	
	@Length(min=0, max=32, message="建设规模长度必须介于 0 和 32 之间")
	public String getBuiltScale() {
		return builtScale;
	}

	public void setBuiltScale(String builtScale) {
		this.builtScale = builtScale;
	}
	
	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public Double getCentralMoney() {
		return centralMoney;
	}

	public void setCentralMoney(Double centralMoney) {
		this.centralMoney = centralMoney;
	}
	
	@Length(min=0, max=1, message="中央投资资金来源长度必须介于 0 和 1 之间")
	public String getCentralinvestType() {
		return centralinvestType;
	}

	public void setCentralinvestType(String centralinvestType) {
		this.centralinvestType = centralinvestType;
	}
	
	@Length(min=0, max=1, message="中央投资资金使用方式长度必须介于 0 和 1 之间")
	public String getCentraluseType() {
		return centraluseType;
	}

	public void setCentraluseType(String centraluseType) {
		this.centraluseType = centraluseType;
	}
	
	public String getCentralinvestMoney() {
		return centralinvestMoney;
	}

	public void setCentralinvestMoney(String centralinvestMoney) {
		this.centralinvestMoney = centralinvestMoney;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=11, message="创建人长度必须介于 0 和 11 之间")
	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=11, message="机构标识长度必须介于 0 和 11 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}