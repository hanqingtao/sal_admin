/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.org.entity.Org;

/**
 * 信用记录Entity
 * @author harry
 * @version 2017-10-18
 */
public class CreditRecord extends DataEntity<CreditRecord> {
	
	private static final long serialVersionUID = 1L;
	private Date reportTime;		// 记录时间
	private String projectName;		// 项目名称
	private String projectCode;		// 项目编号
	private String leader;		// 直接负责人
	private String instruction;		// 违规情况
	private String result;		// 处理结果
	private Date createTime;		// 创建时间
	private Org org;	
	
	public CreditRecord() {
		super();
	}

	public CreditRecord(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	@Length(min=0, max=255, message="项目名称长度必须介于 0 和 255 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=16, message="项目编号长度必须介于 0 和 16 之间")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	@Length(min=0, max=32, message="直接负责人长度必须介于 0 和 32 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=255, message="违规情况长度必须介于 0 和 255 之间")
	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	@Length(min=0, max=255, message="处理结果长度必须介于 0 和 255 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	
	
	
}