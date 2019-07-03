/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.Information;
import com.ambition.agile.modules.users.dao.InformationDao;

/**
 * 实时信息Service
 * @author harry
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class InformationService extends CrudService<InformationDao, Information> {

	public Information get(String id) {
		return super.get(id);
	}
	
	public List<Information> findList(Information information) {
		return super.findList(information);
	}
	
	public Page<Information> findPage(Page<Information> page, Information information) {
		return super.findPage(page, information);
	}
	
	@Transactional(readOnly = false)
	public void save(Information information) {
		super.save(information);
	}
	
	@Transactional(readOnly = false)
	public void delete(Information information) {
		super.delete(information);
	}
	
}