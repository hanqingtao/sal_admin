/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.dao;

 
 

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.org.entity.OrgUser;

/**
 * 代理机构用户DAO接口
 * @author harry
 * @version 2017-08-02
 */
@MyBatisDao
public interface OrgUserDao extends CrudDao<OrgUser> {
   public Integer findLoginName(String loginName);
   public OrgUser findInfoByName(String loginName);
   public List<String>  findEmailById(String id);
}