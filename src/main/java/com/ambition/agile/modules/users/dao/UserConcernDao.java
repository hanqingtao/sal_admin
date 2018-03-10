/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.users.entity.UserConcern;

/**
 * 我的关注DAO接口
 * @author harry
 * @version 2018-03-10
 */
@MyBatisDao
public interface UserConcernDao extends CrudDao<UserConcern> {
	
}