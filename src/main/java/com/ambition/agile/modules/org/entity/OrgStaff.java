/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 代理机构专职人员Entity
 * @author harry
 * @version 2017-08-03
 */
public class OrgStaff extends DataEntity<OrgStaff> {
	
	private static final long serialVersionUID = 1L;
	private Integer orgId;		// 机构标识
	private String name;		// 姓名
	private String sex;		// 性别
	private String card;		// 身份证号
	private String cardPhoto;		// 身份证图片
	private Date workStart;		// 本单位工作开始时间
	private String university;		// 毕业院校
	private String degree;		// 学历
	private String ssn;		// 社会保险号
	private String protitlePhoto;		// 职称图片
	private String proTitle;		// 职称
	private String vocQua;		// 职业资格
	private String workType;		// 劳动关系
	private String dept;		// 所属部门
	
	public OrgStaff() {
		super();
	}

	public OrgStaff(String id){
		super(id);
	}

	@Length(min=0, max=11, message="机构标识长度必须介于 0 和 11 之间")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=0, max=16, message="姓名长度必须介于 0 和 16 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=32, message="身份证号长度必须介于 0 和 32 之间")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	@Length(min=0, max=32, message="身份证图片长度必须介于 0 和 32 之间")
	public String getCardPhoto() {
		return cardPhoto;
	}

	public void setCardPhoto(String cardPhoto) {
		this.cardPhoto = cardPhoto;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkStart() {
		return workStart;
	}

	public void setWorkStart(Date workStart) {
		this.workStart = workStart;
	}
	
	@Length(min=0, max=64, message="毕业院校长度必须介于 0 和 64 之间")
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
	
	@Length(min=0, max=1, message="学历长度必须介于 0 和 1 之间")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Length(min=0, max=32, message="社会保险号长度必须介于 0 和 32 之间")
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	@Length(min=0, max=32, message="职称图片长度必须介于 0 和 32 之间")
	public String getProtitlePhoto() {
		return protitlePhoto;
	}

	public void setProtitlePhoto(String protitlePhoto) {
		this.protitlePhoto = protitlePhoto;
	}
	
	@Length(min=0, max=32, message="职称长度必须介于 0 和 32 之间")
	public String getProTitle() {
		return proTitle;
	}

	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}
	
	@Length(min=0, max=32, message="职业资格长度必须介于 0 和 32 之间")
	public String getVocQua() {
		return vocQua;
	}

	public void setVocQua(String vocQua) {
		this.vocQua = vocQua;
	}
	
	@Length(min=0, max=1, message="劳动关系长度必须介于 0 和 1 之间")
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}
	
	@Length(min=0, max=32, message="所属部门长度必须介于 0 和 32 之间")
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
}