/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.TreeService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.course.entity.CourseCategory;
import com.ambition.agile.modules.course.dao.CourseCategoryDao;

/**
 * 课程分类Service
 * @author harry
 * @version 2019-07-05
 */
@Service
@Transactional(readOnly = true)
public class CourseCategoryService extends TreeService<CourseCategoryDao, CourseCategory> {

	public CourseCategory get(String id) {
		return super.get(id);
	}
	
	public List<CourseCategory> findList(CourseCategory courseCategory) {
		if (StringUtils.isNotBlank(courseCategory.getParentIds())){
			courseCategory.setParentIds(","+courseCategory.getParentIds()+",");
		}
		return super.findList(courseCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseCategory courseCategory) {
		super.save(courseCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseCategory courseCategory) {
		super.delete(courseCategory);
	}
	
}