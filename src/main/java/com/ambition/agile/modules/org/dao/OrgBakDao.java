/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

import java.util.List;
import java.util.Map;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgBak;

/**
 * 代理机构DAO接口
 * @author harry
 * @version 2017-08-02
 */
@MyBatisDao
public interface OrgBakDao extends CrudDao<OrgBak> {
	//备份Org到org_bak
	void bakOrg(Map<String, Object> pMap);
	
	//添加org_achieve
	void bakOrgAchieve(Map<String, Object> pMap);
	//添加org_staff
	void bakOrgStaff(Map<String, Object> pMap);

	void bakOrgQualification(Map<String, Object> pMap);

	List<Map<String, Object>> getBakQualificationBySnAndOrgId(Map<String, Object> pMap);

	List<Map<String, Object>> getBakStaffBySnAndOrgId(Map<String, Object> pMap);

	List<Map<String, Object>> getBakAhieveBySnAndOrgId(Map<String, Object> pMap);
		
}