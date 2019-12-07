/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.Cdkey;
import com.ambition.agile.modules.users.dao.CdkeyDao;

/**
 * 激活码Service
 * @author harry
 * @version 2019-12-07
 */
@Service
@Transactional(readOnly = true)
public class CdkeyService extends CrudService<CdkeyDao, Cdkey> {

	public Cdkey get(String id) {
		return super.get(id);
	}
	
	public List<Cdkey> findList(Cdkey cdkey) {
		return super.findList(cdkey);
	}
	
	public Page<Cdkey> findPage(Page<Cdkey> page, Cdkey cdkey) {
		return super.findPage(page, cdkey);
	}
	
	@Transactional(readOnly = false)
	public void save(Cdkey cdkey) {
		super.save(cdkey);
	}
	
	@Transactional(readOnly = false)
	public void delete(Cdkey cdkey) {
		super.delete(cdkey);
	}
	
}