/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pro.entity.Engineer;
import com.ambition.agile.modules.pro.dao.EngineerDao;

/**
 * 工程信息表Service
 * @author harry
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class EngineerService extends CrudService<EngineerDao, Engineer> {
 
	@Resource
	private EngineerDao engineerDao;
	
	public Engineer get(String id) {
		return super.get(id);
	}
	
	public List<Engineer> findList(Engineer engineer) {
		return super.findList(engineer);
	}
	
	public Page<Engineer> findPage(Page<Engineer> page, Engineer engineer) {
		return super.findPage(page, engineer);
	}
	
	@Transactional(readOnly = false)
	public void save(Engineer engineer) {
		super.save(engineer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Engineer engineer) {
		super.delete(engineer);
	}
	
	public Engineer findInfoByOrgId(String orgId) {
		return engineerDao.findInfoByOrgId(orgId);
	}
}