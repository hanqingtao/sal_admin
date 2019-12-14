/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 激活码Entity
 * @author harry
 * @version 2019-12-07
 */
public class Cdkey extends DataEntity<Cdkey> {
	
	private static final long serialVersionUID = 1L;
//	private Integer batchId;		// 批次id
	private String code;		// 激活码
	private String password; //密码
	private String status;		// 状态 0 未用 1 激活 2作废
	private Date activeDate;		// 激活时间
	private Batch batch;			//批次
	
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Date beginActiveDate;		// 开始 激活时间
	private Date endActiveDate;		// 结束 激活时间
	
	
	public Cdkey() {
		super();
	}

	public Cdkey(String id){
		super(id);
	}

	//@Length(min=0, max=11, message="batch_id长度必须介于 0 和 11 之间")
//	public Integer getBatchId() {
//		return batchId;
//	}
//
//	public void setBatchId(Integer batchId) {
//		this.batchId = batchId;
//	}
	
	@Length(min=0, max=16, message="激活码长度必须介于 0 和 16 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=4, message="状态 0 未用 1 激活 2作废长度必须介于 0 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
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
		
	public Date getBeginActiveDate() {
		return beginActiveDate;
	}

	public void setBeginActiveDate(Date beginActiveDate) {
		this.beginActiveDate = beginActiveDate;
	}
	
	public Date getEndActiveDate() {
		return endActiveDate;
	}

	public void setEndActiveDate(Date endActiveDate) {
		this.endActiveDate = endActiveDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}
		
}