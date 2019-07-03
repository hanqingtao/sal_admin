/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.dao;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.course.entity.CourseCategory;

/**
 * 课程分类DAO接口
 * @author harry
 * @version 2019-07-02
 */
@MyBatisDao
public interface CourseCategoryDao extends TreeDao<CourseCategory> {
	
}