/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 学员信息Entity
 * @author harry
 * @version 2019-07-10
 */
public class Users extends DataEntity<Users> {
	
	private static final long serialVersionUID = 1L;
	private String openId;		// 用户open_id
	private String name;		// 名称
	private String sort;		// 排序
	private Integer age;		// 年龄
	private String grade;		// 年级
	private Integer beginAge;		// 开始 年龄
	private Integer endAge;		// 结束 年龄
	private Date beginTime;//激活码的有效期的开始时间
	private Date endTime;//激活码有效期的结束时间
	private Integer cdkeyId;//主要用于 前台激活时，微信 openId的数据插入.
	private Cdkey cdkey;// 主要是用于 会员管理中列表的显示
	
	public Users() {
		super();
	}

	public Users(String id){
		super(id);
	}

	@Length(min=1, max=32, message="用户open_id长度必须介于 1 和 32 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Length(min=1, max=32, message="名称长度必须介于 1 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Length(min=0, max=2, message="年级长度必须介于 0 和 2 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Integer getBeginAge() {
		return beginAge;
	}

	public void setBeginAge(Integer beginAge) {
		this.beginAge = beginAge;
	}
	
	public Integer getEndAge() {
		return endAge;
	}

	public void setEndAge(Integer endAge) {
		this.endAge = endAge;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/*
	public Cdkey getCdkey() {
		return cdkey;
	}

	public void setCdkey(Cdkey cdkey) {
		this.cdkey = cdkey;
	}
	*/

	public Integer getCdkeyId() {
		return cdkeyId;
	}

	public void setCdkeyId(Integer cdkeyId) {
		this.cdkeyId = cdkeyId;
	}

	public Cdkey getCdkey() {
		return cdkey;
	}

	public void setCdkey(Cdkey cdkey) {
		this.cdkey = cdkey;
	}
}