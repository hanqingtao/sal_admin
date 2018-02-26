/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 项目信息表Entity
 * @author harry
 * @version 2017-08-03
 */
public class Project extends DataEntity<Project> {
	
	private static final long serialVersionUID = 1L;
	private String sn;		// 招标编号
	private String name;		// 招标项目名称
	private String tenderee;		// 招标人
	private String contact;		// 联系人及电话
	private String biddingAgency;		// 招标代理机构
	private String leader;		// 项目负责人
	private String leaderPhone;		// 负责人电话
	private String tenderType;		// 招标类型
	private String entrustUnit;		// 委托单位
	private Double entrustMoney;		// 委托金额
	private Date entrustTime;		// 委托时间
	private String tenderMethod;		// 招标方式
	private String noticeMedia;		// 公告媒体
	private Date noticeDate;		// 公告时间
	private Date opentenderTime;		// 开标时间
	private Date bidTime;		// 中标时间
	private String creditRequire;		// 资质/资信要求
	private String content;		// 招标内容
	private String remark;		// 备注
	private Integer orgId;		// 机构标识
	private Integer engineerId;		// 工程标识
	private Integer areaId; //区域ID
	private String areaName; //区域名
	private Integer status; //状态
	private Integer isCenter;  // 是否中央投资项目（0否，1是）
	private Integer isHistory;  // 是否业绩，1是0否
	private Date startBidTime;//开始中标时间
	private Date endBidTime;//结束中标时间
	private ProjectFlow projectFlow;//
	private Engineer engineer;  // 工程信息（前台添加项目信息用）
	private String  approvalNumber;
	private String bidMoney;		// 中标金额
	private String bidUnit;  // 中标单位
	

	/**
	 * @return the bidMoney
	 */
	public String getBidMoney() {
		return bidMoney;
	}

	/**
	 * @param bidMoney the bidMoney to set
	 */
	public void setBidMoney(String bidMoney) {
		this.bidMoney = bidMoney;
	}

	public String getBidUnit() {
		return bidUnit;
	}

	public void setBidUnit(String bidUnit) {
		this.bidUnit = bidUnit;
	}

	public Engineer getEngineer() {
		return engineer;
	}

	public Integer getIsCenter() {
		return isCenter;
	}

	public void setIsCenter(Integer isCenter) {
		this.isCenter = isCenter;
	}

	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Project() {
		super();
	}

	public Project(String id){
		super(id);
	}

	@Length(min=0, max=16, message="招标编号长度必须介于 0 和 16 之间")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@Length(min=0, max=32, message="招标项目名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="招标人长度必须介于 0 和 32 之间")
	public String getTenderee() {
		return tenderee;
	}

	public void setTenderee(String tenderee) {
		this.tenderee = tenderee;
	}
	
	@Length(min=0, max=32, message="联系人及电话长度必须介于 0 和 32 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=32, message="招标代理机构长度必须介于 0 和 32 之间")
	public String getBiddingAgency() {
		return biddingAgency;
	}

	public void setBiddingAgency(String biddingAgency) {
		this.biddingAgency = biddingAgency;
	}
	
	@Length(min=0, max=32, message="项目负责人长度必须介于 0 和 32 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=32, message="负责人电话长度必须介于 0 和 32 之间")
	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}
	
	@Length(min=0, max=2, message="招标类型长度必须介于 0 和 2 之间")
	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	
	@Length(min=0, max=32, message="委托单位长度必须介于 0 和 32 之间")
	public String getEntrustUnit() {
		return entrustUnit;
	}

	public void setEntrustUnit(String entrustUnit) {
		this.entrustUnit = entrustUnit;
	}
	
	public Double getEntrustMoney() {
		return entrustMoney;
	}

	public void setEntrustMoney(Double entrustMoney) {
		this.entrustMoney = entrustMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(Date entrustTime) {
		this.entrustTime = entrustTime;
	}
	
	@Length(min=0, max=2, message="招标方式长度必须介于 0 和 2 之间")
	public String getTenderMethod() {
		return tenderMethod;
	}

	public void setTenderMethod(String tenderMethod) {
		this.tenderMethod = tenderMethod;
	}
	
	@Length(min=0, max=32, message="公告媒体长度必须介于 0 和 32 之间")
	public String getNoticeMedia() {
		return noticeMedia;
	}

	public void setNoticeMedia(String noticeMedia) {
		this.noticeMedia = noticeMedia;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpentenderTime() {
		return opentenderTime;
	}

	public void setOpentenderTime(Date opentenderTime) {
		this.opentenderTime = opentenderTime;
	}
	
	@Length(min=0, max=16, message="资质/资信要求长度必须介于 0 和 16 之间")
	public String getCreditRequire() {
		return creditRequire;
	}

	public void setCreditRequire(String creditRequire) {
		this.creditRequire = creditRequire;
	}
	
	@Length(min=0, max=512, message="招标内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=512, message="备注长度必须介于 0 和 512 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getStartBidTime() {
		return startBidTime;
	}

	public void setStartBidTime(Date startBidTime) {
		this.startBidTime = startBidTime;
	}

	public Date getEndBidTime() {
		return endBidTime;
	}

	public void setEndBidTime(Date endBidTime) {
		this.endBidTime = endBidTime;
	}

	public ProjectFlow getProjectFlow() {
		return projectFlow;
	}

	public void setProjectFlow(ProjectFlow projectFlow) {
		this.projectFlow = projectFlow;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public Integer getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(Integer isHistory) {
		this.isHistory = isHistory;
	}

	 
	
	 

	 
	
}