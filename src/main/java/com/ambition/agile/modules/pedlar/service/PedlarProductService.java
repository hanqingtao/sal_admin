/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pedlar.entity.PedlarProduct;
import com.ambition.agile.modules.pedlar.dao.PedlarProductDao;

/**
 * 商贩产品信息Service
 * @author harry
 * @version 2018-03-10
 */
@Service
@Transactional(readOnly = true)
public class PedlarProductService extends CrudService<PedlarProductDao, PedlarProduct> {

	public PedlarProduct get(String id) {
		return super.get(id);
	}
	
	public List<PedlarProduct> findList(PedlarProduct pedlarProduct) {
		return super.findList(pedlarProduct);
	}
	
	public Page<PedlarProduct> findPage(Page<PedlarProduct> page, PedlarProduct pedlarProduct) {
		return super.findPage(page, pedlarProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(PedlarProduct pedlarProduct) {
		super.save(pedlarProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PedlarProduct pedlarProduct) {
		super.delete(pedlarProduct);
	}
	
}