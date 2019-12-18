/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.common.util.GUIDUtil;
import com.ambition.agile.modules.sys.dao.DictDao;
import com.ambition.agile.modules.sys.entity.Dict;
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
	
	@Autowired
	private DictDao dictDao;
	
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
			//集合是没有重复的值,LinkedHashSet是有顺序不重复集合,HashSet则为无顺序不重复集合 
			List codeList = new ArrayList<String>(); 
			while(codeList.size() < batch.getCount()){
				//默认为7位数字
				String tmp = GUIDUtil.getSevenCode();
				if(StringUtils.isNotEmpty(batch.getRule())){
					/*Dict dict = dictDao.get(batch.getRule());
					if(null != dict && 
							StringUtils.isNotEmpty(dict.getId())
							&& StringUtils.isNotEmpty(dict.getValue())
							){}
					*/
						if(batch.getRule().equals("7")){
							tmp = GUIDUtil.getSevenCode();
						}
						if(batch.getRule().equals("6")){
							tmp = GUIDUtil.getSixCode();
						}
						if(batch.getRule().equals("5")){
							tmp = GUIDUtil.getFiveCode();
						}
						if(batch.getRule().equals("4")){
							tmp = GUIDUtil.getFourCode();
						}
						if(batch.getRule().equals("3")){
							tmp = GUIDUtil.getThreeCode();
						}
				}
				if(StringUtils.isNotEmpty(batch.getPre())){
					tmp = batch.getPre() + tmp;
				}
				if(!codeList.contains(tmp)){
					//根据 code 判断是否已经在数据库中，如果已经在数据库中，则不能进行 codeList 的插入,重新去生成
					Cdkey cdkey = new Cdkey();
					cdkey.setCode(tmp);
					Integer cnt = cdkeyDao.getCountByCode( cdkey);
					if(cnt < 1){
						//直接加入，当有重复值时，不会往里加入，直到set的长度为count才结束
						codeList.add(tmp);
					}
				}
			}
			//System.out.println("########### insert " + batchId + "batch.getId "+batch.getId());
			if(null != batch && batch.getCount()>0){
				for(int i=0;i<batch.getCount();i++){
					Cdkey cdkey = new Cdkey();
					cdkey.setBatch(batch);
					cdkey.setCode((String)codeList.get(i));
					cdkey.setPassword(GUIDUtil.getSixCardByGUID());
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