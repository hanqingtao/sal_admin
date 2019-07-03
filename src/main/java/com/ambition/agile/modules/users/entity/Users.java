/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import org.hibernate.validator.constraints.Length;
import com.ambition.agile.modules.sys.entity.Area;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 用户信息Entity
 * @author harry
 * @version 2018-03-31
 */
public class Users extends DataEntity<Users> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String name;		// 真实姓名
	private String password;		// 登录 密码
	private String email;		// email
	private Area areap;		// 省
	private Area areac;		// 市
	private Area areaa;		// 区/县
	private String fullName;		// 区域名称
	private String telphone;		// 手机号
	private String status;		// 用户状态
	private String kind;		// 用户类型
	private String gender;		// 性别
	private Date birthday;		// 出生日期
	private String degree;		// 学历
	private String photoPath;		// 头像
	
	public Users() {
		super();
	}

	public Users(String id){
		super(id);
	}

	@Length(min=1, max=32, message="登录名长度必须介于 1 和 32 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=32, message="真实姓名长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="登录 密码长度必须介于 0 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=64, message="email长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Area getAreap() {
		return areap;
	}

	public void setAreap(Area areap) {
		this.areap = areap;
	}
	
	public Area getAreac() {
		return areac;
	}

	public void setAreac(Area areac) {
		this.areac = areac;
	}
	
	public Area getAreaa() {
		return areaa;
	}

	public void setAreaa(Area areaa) {
		this.areaa = areaa;
	}
	
	@Length(min=0, max=64, message="区域名称长度必须介于 0 和 64 之间")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Length(min=0, max=16, message="手机号长度必须介于 0 和 16 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=0, max=1, message="用户状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="用户类型长度必须介于 0 和 1 之间")
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=6, message="学历长度必须介于 0 和 6 之间")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Length(min=0, max=64, message="头像长度必须介于 0 和 64 之间")
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}