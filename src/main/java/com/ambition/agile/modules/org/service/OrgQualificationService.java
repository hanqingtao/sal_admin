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
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.dao.OrgDao;
import com.ambition.agile.modules.org.dao.OrgQualificationDao;

/**
 * 代理机构资格Service
 * @author harry
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OrgQualificationService extends CrudService<OrgQualificationDao, OrgQualification> {
	
	 @Resource
	private OrgQualificationDao orgQualificationDao;

	public OrgQualification get(String id) {
		return super.get(id);
	}
	
	public List<OrgQualification> findList(OrgQualification orgQualification) {
		return super.findList(orgQualification);
	}
	
	public Page<OrgQualification> findPage(Page<OrgQualification> page, OrgQualification orgQualification) {
		return super.findPage(page, orgQualification);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgQualification orgQualification) {
		super.save(orgQualification);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgQualification orgQualification) {
		super.delete(orgQualification);
	}
	public List<OrgQualification> findOrgQualificationByOrgId(Integer orgId){
		return  orgQualificationDao.findOrgQualificationByOrgId(orgId);
		
	}
}