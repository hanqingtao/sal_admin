/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import com.ambition.agile.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 我的关注Entity
 * @author harry
 * @version 2018-03-31
 */
public class UserConcern extends DataEntity<UserConcern> {
	
	private static final long serialVersionUID = 1L;
	private User userId;		// 用户id
	private String concernType;		// 关注类型
	private Integer concernId;		// 关注 id
	private String concernName;		// 关注的内容
	
	public UserConcern() {
		super();
	}

	public UserConcern(String id){
		super(id);
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=1, message="关注类型长度必须介于 0 和 1 之间")
	public String getConcernType() {
		return concernType;
	}

	public void setConcernType(String concernType) {
		this.concernType = concernType;
	}
	
	public Integer getConcernId() {
		return concernId;
	}

	public void setConcernId(Integer concernId) {
		this.concernId = concernId;
	}
	
	@Length(min=0, max=32, message="关注的内容长度必须介于 0 和 32 之间")
	public String getConcernName() {
		return concernName;
	}

	public void setConcernName(String concernName) {
		this.concernName = concernName;
	}
	
}