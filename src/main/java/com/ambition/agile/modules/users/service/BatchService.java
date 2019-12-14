/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.common.util.GUIDUtil;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.users.dao.BatchDao;
import com.ambition.agile.modules.users.dao.CdkeyDao;
import com.ambition.agile.modules.users.entity.Batch;
import com.ambition.agile.modules.users.entity.Cdkey;

/**
 * 批次Service
 * @author harry
 * @version 2019-12-07
 */
//extends BaseService implements InitializingBean
//public class BatchService extends CrudService<BatchDao, Batch> {
@Service
@Transactional(readOnly = true)
public class BatchService extends  CrudService<BatchDao, Batch>  {
	
	@Autowired
	private BatchDao batchDao;
	
	@Autowired
	private CdkeyDao cdkeyDao;
	
	public Batch get(String id) {
		return batchDao.get(id);
	}
	
	public List<Batch> findList(Batch batch) {
		return batchDao.findList(batch);
	}
	
	public Page<Batch> findPage(Page<Batch> page, Batch batch) {
		return super.findPage(page, batch);
	}
	
	@Transactional(readOnly = false)
	public void save(Batch batch) {
		
		if (batch.getIsNewRecord()){
			//entity.preInsert();
			int batchId = batchDao.insert(batch) ;
			//System.out.println("########### insert " + batchId + "batch.getId "+batch.getId());
			//batch.setId(batchId +"");
			if(null != batch && batch.getCount()>0){
				for(int i=0;i<batch.getCount();i++){
					Cdkey cdkey = new Cdkey();
					cdkey.setBatch(batch);
					cdkey.setCode(GUIDUtil.getEightCardByGUID());
					//cdkey.setCode(GUIDUtil.getEightCardByGUID());
					cdkey.setCreateBy(UserUtils.getUser());
					cdkey.setCreateDate(new Date());
					cdkey.setStatus("0");
					cdkeyDao.insert(cdkey);
				}
				
			}
			
			//cdkeyDao.insert();
		}else{
			//entity.preUpdate();
			batchDao.update(batch);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Batch batch) {
		batchDao.delete(batch);
	}
	
}