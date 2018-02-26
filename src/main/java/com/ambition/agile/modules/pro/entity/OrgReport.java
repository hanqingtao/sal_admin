/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.org.entity.Org;

/**
 * 代理机构举报信息Entity
 * @author harry
 * @version 2017-08-03
 */
public class OrgReport extends DataEntity<OrgReport> {
	
	private static final long serialVersionUID = 1L;
	private String contact;		// 联系人
	private String phone;		// 联系电话
	private String email;		// 邮箱
	private String content;		// 举报内容
	private Integer orgId;		// 代理机构标识
	private Integer projectId;		// 项目标识
	private Date createTime;		// 创建时间
	private String status;		// 举报违法标识
	private Project project;
	private Org org;
	private String orgName;
	private String projectName;
	
	public OrgReport() {
		super();
	}

	public OrgReport(String id){
		super(id);
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
	
	@Length(min=0, max=32, message="邮箱长度必须介于 0 和 32 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=2000, message="举报内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=1, message="举报违法标识长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}