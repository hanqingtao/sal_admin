/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.users.entity.Batch;

/**
 * 批次DAO接口
 * @author harry
 * @version 2019-12-07
 */
@MyBatisDao
public interface BatchDao extends CrudDao<Batch> {
	
}