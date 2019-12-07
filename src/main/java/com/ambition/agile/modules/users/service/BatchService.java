/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.Batch;
import com.ambition.agile.modules.users.dao.BatchDao;

/**
 * 批次Service
 * @author harry
 * @version 2019-12-07
 */
@Service
@Transactional(readOnly = true)
public class BatchService extends CrudService<BatchDao, Batch> {

	public Batch get(String id) {
		return super.get(id);
	}
	
	public List<Batch> findList(Batch batch) {
		return super.findList(batch);
	}
	
	public Page<Batch> findPage(Page<Batch> page, Batch batch) {
		return super.findPage(page, batch);
	}
	
	@Transactional(readOnly = false)
	public void save(Batch batch) {
		super.save(batch);
	}
	
	@Transactional(readOnly = false)
	public void delete(Batch batch) {
		super.delete(batch);
	}
	
}