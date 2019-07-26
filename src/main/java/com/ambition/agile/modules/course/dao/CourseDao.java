/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCategory;

/**
 * 课程DAO接口
 * @author harry
 * @version 2019-07-05
 */
@MyBatisDao
public interface CourseDao extends CrudDao<Course> {
	
	public List<Course> getByCode(String code);
	
	public List<Course> getByName(String name);
	
}