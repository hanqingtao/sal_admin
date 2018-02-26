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
import com.ambition.agile.modules.org.entity.OrgCheck;
import com.ambition.agile.modules.org.dao.OrgCheckDao;

/**
 * 抽查管理Service
 * @author harry
 * @version 2017-10-23
 */
@Service
@Transactional(readOnly = true)
public class OrgCheckService extends CrudService<OrgCheckDao, OrgCheck> {
	@Resource
	private OrgCheckDao orgCheckDao;
	
	public OrgCheck get(String id) {
		return super.get(id);
	}
	
	public List<OrgCheck> findList(OrgCheck orgCheck) {
		return super.findList(orgCheck);
	}
	
	public Page<OrgCheck> findPage(Page<OrgCheck> page, OrgCheck orgCheck) {
		return super.findPage(page, orgCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgCheck orgCheck) {
		super.save(orgCheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgCheck orgCheck) {
		super.delete(orgCheck);
	}
	/**
	 * 获取随机一个区域ID
	 * @return
	 */
	public Integer getIntAreaId() {
		return orgCheckDao.getIntAreaId();
	}
	
}