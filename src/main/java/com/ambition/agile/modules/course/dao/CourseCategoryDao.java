/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.dao;

import java.util.List;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.course.entity.CourseCategory;

/**
 * 课程分类DAO接口
 * @author harry
 * @version 2019-07-05
 */
@MyBatisDao
public interface CourseCategoryDao extends TreeDao<CourseCategory> {
	
	public List<CourseCategory> getByCode(String code);
	
}