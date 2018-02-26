/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.dao;

import java.util.List;
import java.util.Map;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgProjectExcel;
import com.ambition.agile.modules.pro.entity.Project;

/**
 * 项目信息表DAO接口
 * @author harry
 * @version 2017-08-03
 */
@MyBatisDao
public interface ProjectDao extends CrudDao<Project> {
	public List<Project> findInfoByOrgId(Integer orgId);
	public List<Project>  findProjectInfoByNum();
	public Integer getCountByOrgId(Integer orgId);
	public List<OrgProjectExcel> getLimitProjects(Map<String, Object> paramMap);
	public Double getProRecentMoney(Org org);
	public Double getAhieveRecentMoney(Org org);
 
}