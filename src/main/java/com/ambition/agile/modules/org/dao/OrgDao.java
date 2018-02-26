/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

import java.util.List;
import java.util.Map;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.Org;

/**
 * 代理机构DAO接口
 * @author harry
 * @version 2017-08-02
 */
@MyBatisDao
public interface OrgDao extends CrudDao<Org> {
	
	//供前台机构新增 时  使用
	public int selectOrgcode(String sc_code);
	
	public List<Org> findOrgInfoByNum();
	//供前台机构变更时  使用
	public int getOrgByScCode(Org org);
	//根据
	public List<Org>  getOrgByNature(String nature);
	
	/**
	 * @param areaId
	 * @return
	 */
	public Map<String, Integer> getAreaStaticByAreaId(Integer areaId);
	
	/**
	 * 获取区域下当年的统计信息（项目数、中标金额）
	 * @param areaId
	 * @return
	 */
	//public Map<String, Integer> getAreaStaticByAreaIdYear(Integer areaId);
	public Map<String, Integer> getAreaStaticByAreaIdYear(Map map);
	
	/**
	 * 获取区域下当月每周的统计信息（项目数、中标金额）
	 * @param areaId
	 * @return
	 */
	public Map<String, Integer> getArearStaticByAreaIdWeek(Map<String, Object> map);
	
	/**
	 * 获取首页的招标业绩统计信息
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getOrgStaticForIndex(Map<String, Object> map);
	
	//获取某区域下机构个数名录
	public Integer getOrgCountByAreaId(Integer areaId);

	public List<Org> getLimitOrgs(Map<String, Object> paramMap);

	public List<Org> getStatus1OrgsByLastsDay(Map<String, Object> pMap);
	public List<Org> getStatus2OrgsByLastsDay(Map<String, Object> pMap);
	//更新近三年业绩金额
	public void updateRecentMoney(Org org);
	public void updateById(Map<String,Object> map);

	public void updateStateOn(Org org);
		
}