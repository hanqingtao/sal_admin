/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgCheck;

/**
 * 抽查管理DAO接口
 * @author harry
 * @version 2017-10-23
 */
@MyBatisDao
public interface OrgCheckDao extends CrudDao<OrgCheck> {
	
	public Integer getIntAreaId();
	
}