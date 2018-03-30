/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.consumer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.consumer.entity.Consumer;
import com.ambition.agile.modules.consumer.dao.ConsumerDao;

/**
 * 用户信息Service
 * @author harry
 * @version 2018-03-09
 */
@Service
@Transactional(readOnly = true)
public class ConsumerService extends CrudService<ConsumerDao, Consumer> {

	public Consumer get(String id) {
		return super.get(id);
	}
	
	public List<Consumer> findList(Consumer consumer) {
		return super.findList(consumer);
	}
	
	public Page<Consumer> findPage(Page<Consumer> page, Consumer consumer) {
		return super.findPage(page, consumer);
	}
	
	@Transactional(readOnly = false)
	public void save(Consumer consumer) {
		super.save(consumer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Consumer consumer) {
		super.delete(consumer);
	}
	
}