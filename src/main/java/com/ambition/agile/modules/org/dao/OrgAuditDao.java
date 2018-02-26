/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgAudit;

/**
 * 代理机构审批记录DAO接口
 * @version 2017-09-18
 */
@MyBatisDao
public interface OrgAuditDao extends CrudDao<OrgAudit> {
	public List<OrgAudit>  findOrgAuditByOrgId(Integer orgId);
}