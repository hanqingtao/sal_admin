/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pedlar.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.pedlar.entity.PedlarMarket;

/**
 * 商家集市DAO接口
 * @author harry
 * @version 2018-03-31
 */
@MyBatisDao
public interface PedlarMarketDao extends CrudDao<PedlarMarket> {
	
}