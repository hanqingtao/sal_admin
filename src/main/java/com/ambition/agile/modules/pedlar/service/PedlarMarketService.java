/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pedlar.entity.PedlarMarket;
import com.ambition.agile.modules.pedlar.dao.PedlarMarketDao;

/**
 * 商家集市Service
 * @author harry
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class PedlarMarketService extends CrudService<PedlarMarketDao, PedlarMarket> {

	public PedlarMarket get(String id) {
		return super.get(id);
	}
	
	public List<PedlarMarket> findList(PedlarMarket pedlarMarket) {
		return super.findList(pedlarMarket);
	}
	
	public Page<PedlarMarket> findPage(Page<PedlarMarket> page, PedlarMarket pedlarMarket) {
		return super.findPage(page, pedlarMarket);
	}
	
	@Transactional(readOnly = false)
	public void save(PedlarMarket pedlarMarket) {
		super.save(pedlarMarket);
	}
	
	@Transactional(readOnly = false)
	public void delete(PedlarMarket pedlarMarket) {
		super.delete(pedlarMarket);
	}
	
}