/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.users.entity.Users;

/**
 * 用户信息DAO接口
 * @author harry
 * @version 2018-03-10
 */
@MyBatisDao
public interface UsersDao extends CrudDao<Users> {
	
}