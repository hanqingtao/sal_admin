/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.market.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.market.entity.Market;
import com.ambition.agile.modules.market.dao.MarketDao;

/**
 * 集市信息管理Service
 * @author harry
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class MarketService extends CrudService<MarketDao, Market> {

	public Market get(String id) {
		return super.get(id);
	}
	
	public List<Market> findList(Market market) {
		return super.findList(market);
	}
	
	public Page<Market> findPage(Page<Market> page, Market market) {
		return super.findPage(page, market);
	}
	
	@Transactional(readOnly = false)
	public void save(Market market) {
		super.save(market);
	}
	
	@Transactional(readOnly = false)
	public void delete(Market market) {
		super.delete(market);
	}
	
}