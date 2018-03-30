/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.market.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.market.entity.Market;

/**
 * 集市信息管理DAO接口
 * @author harry
 * @version 2018-03-09
 */
@MyBatisDao
public interface MarketDao extends CrudDao<Market> {
	
}