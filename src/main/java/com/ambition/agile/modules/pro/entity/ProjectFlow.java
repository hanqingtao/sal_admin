/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 项目流程Entity
 * @author harry
 * @version 2017-08-08
 */
public class ProjectFlow extends DataEntity<ProjectFlow> {
	
	private static final long serialVersionUID = 1L;
	private Integer projectId;		// 项目标识
	private String noticeFile;		// 公告附件
	private Integer noticeStatus;  // 公告状态
	private String tenderFile;		// tender_file
	private Integer tenderStatus;  //
	private String fileEvaluation;		// 评标记录附件
	private String fileEvaluationexpert;		// 评标专家
	private Integer reportStatus;  
	private String bidWinner;		// 阶段
	private String contact;		// 联系人
	private String bidUnit;		// 中标单位
	private String phone;		// 联系电话
	private Double bidMoney;		// 中标金额
	private Double capitalsavingRatio;		// 节资率
	private Date bidnoticeTime;		// 中标通知书发出时间
	private String fileBid;		// 中标通知书附件
	private Integer bidStatus;
	public Integer getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public Integer getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(Integer tenderStatus) {
		this.tenderStatus = tenderStatus;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(Integer bidStatus) {
		this.bidStatus = bidStatus;
	}

	private Integer orderNum;		// 顺序号
	
	public ProjectFlow() {
		super();
	}

	public ProjectFlow(String id){
		super(id);
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=32, message="公告附件长度必须介于 0 和 32 之间")
	public String getNoticeFile() {
		return noticeFile;
	}

	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}
	
	@Length(min=0, max=32, message="tender_file长度必须介于 0 和 32 之间")
	public String getTenderFile() {
		return tenderFile;
	}

	public void setTenderFile(String tenderFile) {
		this.tenderFile = tenderFile;
	}
	
	@Length(min=0, max=32, message="评标记录附件长度必须介于 0 和 32 之间")
	public String getFileEvaluation() {
		return fileEvaluation;
	}

	public void setFileEvaluation(String fileEvaluation) {
		this.fileEvaluation = fileEvaluation;
	}
	
	@Length(min=0, max=32, message="评标专家长度必须介于 0 和 32 之间")
	public String getFileEvaluationexpert() {
		return fileEvaluationexpert;
	}

	public void setFileEvaluationexpert(String fileEvaluationexpert) {
		this.fileEvaluationexpert = fileEvaluationexpert;
	}
	
	@Length(min=0, max=32, message="阶段长度必须介于 0 和 32 之间")
	public String getBidWinner() {
		return bidWinner;
	}

	public void setBidWinner(String bidWinner) {
		this.bidWinner = bidWinner;
	}
	
	@Length(min=0, max=32, message="联系人长度必须介于 0 和 32 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=32, message="联系电话长度必须介于 0 和 32 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Double getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(Double bidMoney) {
		this.bidMoney = bidMoney;
	}
	
	public Double getCapitalsavingRatio() {
		return capitalsavingRatio;
	}

	public void setCapitalsavingRatio(Double capitalsavingRatio) {
		this.capitalsavingRatio = capitalsavingRatio;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBidnoticeTime() {
		return bidnoticeTime;
	}

	public void setBidnoticeTime(Date bidnoticeTime) {
		this.bidnoticeTime = bidnoticeTime;
	}
	
	@Length(min=0, max=32, message="中标通知书附件长度必须介于 0 和 32 之间")
	public String getFileBid() {
		return fileBid;
	}

	public void setFileBid(String fileBid) {
		this.fileBid = fileBid;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getBidUnit() {
		return bidUnit;
	}

	public void setBidUnit(String bidUnit) {
		this.bidUnit = bidUnit;
	}
	
	
}