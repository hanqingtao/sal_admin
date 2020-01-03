/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 用户激活码关系Entity
 * @author harry
 * @version 2020-01-02
 */
public class UsersCdkey extends DataEntity<UsersCdkey> {
	
	private static final long serialVersionUID = 1L;
	private Cdkey cdkey;		// cdkey_id
	private Integer usersId;		// 用户
	
	public UsersCdkey() {
		super();
	}

	public UsersCdkey(String id){
		super(id);
	}

	public Cdkey getCdkey() {
		return cdkey;
	}

	public void setCdkey(Cdkey cdkey) {
		this.cdkey = cdkey;
	}
	
	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	
}