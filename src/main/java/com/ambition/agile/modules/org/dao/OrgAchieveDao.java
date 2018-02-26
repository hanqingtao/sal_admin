/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgAchieve;

/**
 * 代理机构招标业绩DAO接口
 * @author harry
 * @version 2017-08-03
 */
@MyBatisDao
public interface OrgAchieveDao extends CrudDao<OrgAchieve> {
	public List<OrgAchieve> findAchieveByOrgId(String orgId);

	public List<OrgAchieve> findAchieveByOrgIdNoYears(String orgId);
	                         
}