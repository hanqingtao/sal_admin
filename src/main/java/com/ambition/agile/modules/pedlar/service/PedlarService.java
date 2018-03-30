/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pedlar.entity.Pedlar;
import com.ambition.agile.modules.pedlar.dao.PedlarDao;

/**
 * 商贩信息Service
 * @author harry
 * @version 2018-03-09
 */
@Service
@Transactional(readOnly = true)
public class PedlarService extends CrudService<PedlarDao, Pedlar> {

	public Pedlar get(String id) {
		return super.get(id);
	}
	
	public List<Pedlar> findList(Pedlar pedlar) {
		return super.findList(pedlar);
	}
	
	public Page<Pedlar> findPage(Page<Pedlar> page, Pedlar pedlar) {
		return super.findPage(page, pedlar);
	}
	
	@Transactional(readOnly = false)
	public void save(Pedlar pedlar) {
		super.save(pedlar);
	}
	
	@Transactional(readOnly = false)
	public void delete(Pedlar pedlar) {
		super.delete(pedlar);
	}
	
}