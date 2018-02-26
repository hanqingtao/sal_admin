/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import java.util.Date;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 代理机构审批记录Entity
 * @author harry
 * @version 2017-08-03
 */
public class OrgAudit extends DataEntity<OrgAudit> {
	
	private static final long serialVersionUID = 1L;
	private Integer orgId;		 // 代理机构标识
	private Integer applyId;    // 申请标识
	private String  content;	 // 审核意见
	private Integer createId;   // 创建人标识
	private Date createDate;	 // 创建时间
	private String  orgStatus;  // 省通过2    省拒绝 5   3招标中心通过 
	private User user;
	
	
	public OrgAudit() {
		super();
	}
	public OrgAudit(String id){
		super(id);
	}

	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}