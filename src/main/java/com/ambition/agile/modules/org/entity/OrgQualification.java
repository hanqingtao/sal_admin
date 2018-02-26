/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 代理机构资格Entity
 * @author harry
 * @version 2017-08-03
 */
public class OrgQualification extends DataEntity<OrgQualification> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 资格名称
	private String grade;		// 资格类型
	private String path;		// 资格证书图片
	private Integer orgId;		// 机构标识
	private Date createTime;		// 创建时间
	private Integer createId;		// 创建人
	
	public OrgQualification() {
		super();
	}

	public OrgQualification(String id){
		super(id);
	}

	@Length(min=0, max=32, message="资格名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="资格类型长度必须介于 0 和 1 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=32, message="资格证书图片长度必须介于 0 和 32 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
}