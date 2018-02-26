/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgStaff;
import com.ambition.agile.modules.pro.entity.CreditRecord;

/**
 * 信用记录DAO接口
 * @author harry
 * @version 2017-10-18
 */
@MyBatisDao
public interface CreditRecordDao extends CrudDao<CreditRecord> {
	public List<CreditRecord> findCreditRecordByOrgId(Integer orgId);
}