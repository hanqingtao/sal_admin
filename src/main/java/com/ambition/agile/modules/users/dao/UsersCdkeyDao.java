/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.users.entity.UsersCdkey;

/**
 * 用户激活码关系DAO接口
 * @author harry
 * @version 2020-01-02
 */
@MyBatisDao
public interface UsersCdkeyDao extends CrudDao<UsersCdkey> {
	
}