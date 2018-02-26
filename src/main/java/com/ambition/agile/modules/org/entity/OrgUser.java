/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 代理机构用户Entity
 * @author harry
 * @version 2017-08-02
 */
public class OrgUser extends DataEntity<OrgUser> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String userName;		// 真实姓名
	private String password;		// 登录密码
	private String email;		// 邮箱
	private Org org;		// 机构标识
	//private String areaId;			//地区id 
	private String telphone;		// 手机号
	private String status;		// 用户状态
	private String sex;		// 性别
	private Date birthday;		// 出生日期
	private String degree;		// 学历
	private String photoPath;		// 头像
	private Date createTime;		// 创建时间
    private Date maildate;//邮件发送时间（邮件找回密码时使用）
	private String userauthvalue;//邮件找回密码时使用
	public OrgUser() {
		super();
	}

	public OrgUser(String id){
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
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="登录密码长度必须介于 0 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=64, message="邮箱长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
	
	@Length(min=0, max=11, message="手机号长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=1, message="学历长度必须介于 0 和 1 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getMaildate() {
		return maildate;
	}

	public void setMaildate(Date maildate) {
		this.maildate = maildate;
	}

	public String getUserauthvalue() {
		return userauthvalue;
	}

	public void setUserauthvalue(String userauthvalue) {
		this.userauthvalue = userauthvalue;
	}
	
}