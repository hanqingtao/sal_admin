/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.org.entity.OrgAchieve;
import com.ambition.agile.modules.org.dao.OrgAchieveDao;
import com.ambition.agile.modules.org.dao.OrgStaffDao;

/**
 * 代理机构招标业绩Service
 * @author harry
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OrgAchieveService extends CrudService<OrgAchieveDao, OrgAchieve> {

	@Resource
	private OrgAchieveDao orgAchieveDao;
	public OrgAchieve get(String id) {
		return super.get(id);
	}
	
	public List<OrgAchieve> findList(OrgAchieve orgAchieve) {
		return super.findList(orgAchieve);
	}
	
	public Page<OrgAchieve> findPage(Page<OrgAchieve> page, OrgAchieve orgAchieve) {
		return super.findPage(page, orgAchieve);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgAchieve orgAchieve) {
		super.save(orgAchieve);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgAchieve orgAchieve) {
		super.delete(orgAchieve);
	}

	public List<OrgAchieve> findAchieveByOrgId(String orgId) {
		 
		return orgAchieveDao.findAchieveByOrgId(orgId);
	}
	public List<OrgAchieve> findAchieveByOrgIdNoYears(String orgId) {
		
		return orgAchieveDao.findAchieveByOrgIdNoYears(orgId);
	}
	
}