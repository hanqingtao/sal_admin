/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.pro.entity.Engineer;

/**
 * 工程信息表DAO接口
 * @author harry
 * @version 2017-08-03
 */
@MyBatisDao
public interface EngineerDao extends CrudDao<Engineer> {
	public Engineer findInfoByOrgId(String orgId);
}