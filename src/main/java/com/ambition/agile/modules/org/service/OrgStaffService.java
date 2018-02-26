/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.org.dao.OrgStaffDao;

/**
 * 代理机构专职人员Service
 * @author harry
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OrgStaffService extends CrudService<OrgStaffDao, OrgStaff> {

	@Resource
	private OrgStaffDao orgStaffDao;
	public OrgStaff get(String id) {
		return super.get(id);
	}
	
	public List<OrgStaff> findList(OrgStaff orgStaff) {
		return super.findList(orgStaff);
	}
	
	public Page<OrgStaff> findPage(Page<OrgStaff> page, OrgStaff orgStaff) {
		return super.findPage(page, orgStaff);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgStaff orgStaff) {
		super.save(orgStaff);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgStaff orgStaff) {
		super.delete(orgStaff);
	}
	
	public Integer findNumByCard(String card) {
		return orgStaffDao.findNumByCard(card);
	}
	public List<OrgStaff> findStaffByOrgId(String orgId) {
		return orgStaffDao.findStaffByOrgId(orgId);
	}

	public com.ambition.agile.common.util.Page<OrgStaff> findPage2(com.ambition.agile.common.util.Page<OrgStaff> page) {
		return orgStaffDao.findPage2(page);
	}

	public Integer findNumByCardAndOrg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orgStaffDao.findNumByCardAndOrg(map);
	}
}