/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.course.entity.CourseCategory;
import com.ambition.agile.modules.users.entity.Users;

/**
 * 学员信息DAO接口
 * @author harry
 * @version 2019-07-10
 */
@MyBatisDao
public interface UsersDao extends CrudDao<Users> {
	
	
	public Users getByOpenId(String openId);
	
}