/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CourseCategory;
import com.ambition.agile.modules.course.dao.CourseDao;

/**
 * 课程Service
 * @author harry
 * @version 2019-07-05
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudService<CourseDao, Course> {

	public Course get(String id) {
		return super.get(id);
	}
	
	public List<Course> findList(Course course) {
		return super.findList(course);
	}
	
	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}
	
	@Transactional(readOnly = false)
	public void save(Course course) {
		super.save(course);
	}
	
	@Transactional(readOnly = false)
	public void delete(Course course) {
		super.delete(course);
	}
	
	//根据 code 获取 课程 的信息
	public List<Course> getByCode(String code) {
		List<Course> couresList = dao.getByCode(code);
		return couresList;
	}
	
	//根据 code 获取 课程 的信息
	public List<Course> getByName(Course course) {
		List<Course> couresList = dao.getByName(course);
		return couresList;
	}
	
	
}