/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.pro.entity.ProjectFlow;

/**
 * 项目流程DAO接口
 * @author harry
 * @version 2017-08-08
 */
@MyBatisDao
public interface ProjectFlowDao extends CrudDao<ProjectFlow> {
	public ProjectFlow findInfoByProId(Integer id);
}