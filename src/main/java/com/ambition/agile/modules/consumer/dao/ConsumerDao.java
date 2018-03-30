/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.consumer.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.consumer.entity.Consumer;

/**
 * 用户信息DAO接口
 * @author harry
 * @version 2018-03-09
 */
@MyBatisDao
public interface ConsumerDao extends CrudDao<Consumer> {
	
}