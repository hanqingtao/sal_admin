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
import com.ambition.agile.modules.org.dao.OrgAuditDao;
import com.ambition.agile.modules.org.entity.OrgAudit;

/**
 * 代理机构审批意见Service
 * @version 2017-09-18
 */
@Service
@Transactional(readOnly = true)
public class OrgAuditService extends CrudService<OrgAuditDao, OrgAudit> {

	@Resource
	private OrgAuditDao orgAuditDao;
	public OrgAudit get(String id) {
		return super.get(id);
	}
	
	public List<OrgAudit> findList(OrgAudit orgAudit) {
		return super.findList(orgAudit);
	}
	
	public Page<OrgAudit> findPage(Page<OrgAudit> page, OrgAudit orgAudit) {
		return super.findPage(page, orgAudit);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgAudit orgAudit) {
		super.save(orgAudit);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgAudit orgAudit) {
		super.delete(orgAudit);
	}
	
	public List<OrgAudit> findOrgAuditByOrgId(Integer orgId){
		return orgAuditDao.findOrgAuditByOrgId(orgId);
	}
}