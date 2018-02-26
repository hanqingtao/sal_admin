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
import com.ambition.agile.modules.pro.entity.CreditRecord;
import com.ambition.agile.modules.pro.dao.CreditRecordDao;
 

/**
 * 信用记录Service
 * @author harry
 * @version 2017-10-18
 */
@Service
@Transactional(readOnly = true)
public class CreditRecordService extends CrudService<CreditRecordDao, CreditRecord> {

	@Resource
	private CreditRecordDao creditRecordDao;
	
	
	public CreditRecord get(String id) {
		return super.get(id);
	}
	
	public List<CreditRecord> findList(CreditRecord creditRecord) {
		return super.findList(creditRecord);
	}
	
	public Page<CreditRecord> findPage(Page<CreditRecord> page, CreditRecord creditRecord) {
		return super.findPage(page, creditRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(CreditRecord creditRecord) {
		super.save(creditRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(CreditRecord creditRecord) {
		super.delete(creditRecord);
	}
	
	public List<CreditRecord>  findCreditRecordByOrgId(Integer orgId){
		return creditRecordDao.findCreditRecordByOrgId(orgId);
	}
	
}