/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

import java.util.List;
import java.util.Map;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgStaff;

/**
 * 代理机构专职人员DAO接口
 * @author harry
 * @version 2017-08-03
 */
@MyBatisDao
public interface OrgStaffDao extends CrudDao<OrgStaff> {
	public Integer findNumByCard(String card);
	public List<OrgStaff> findStaffByOrgId(String orgId);
	public com.ambition.agile.common.util.Page<OrgStaff> findPage2(com.ambition.agile.common.util.Page<OrgStaff> page);
	public Integer findNumByCardAndOrg(Map<String, Object> map);
	
}