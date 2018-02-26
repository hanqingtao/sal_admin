/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.org.entity.OrgProjectExcel;
import com.ambition.agile.modules.pro.dao.ProjectDao;

/**
 * 项目信息表Service
 * @author harry
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class ProjectService extends CrudService<ProjectDao, Project> {
   
	@Resource
	private ProjectDao projectDao;
	
	public Project get(String id) {
		return super.get(id);
	}
	
	public List<Project> findList(Project project) {
		return super.findList(project);
	}
	
	public Page<Project> findPage(Page<Project> page, Project project) {
		return super.findPage(page, project);
	}
	
	@Transactional(readOnly = false)
	public void save(Project project) {
		super.save(project);
	}
	
	@Transactional(readOnly = false)
	public void delete(Project project) {
		super.delete(project);
	}
	
	/**
	 * 根据机构ID查询项目信息
	 * @param orgId
	 * @return
	 */
	public List<Project> findInfoByOrgId(Integer orgId){
		return projectDao.findInfoByOrgId(orgId);
	}
	
	public List<Project> findProjectInfoByNum(){
		return projectDao.findProjectInfoByNum();
	}
	/**
	 * 根据orgID获取项目总数
	 * @param orgId
	 * @return
	 */
	public Integer getCountByOrgId(Integer orgId) {
		return projectDao.getCountByOrgId(orgId);
	}

	public List<OrgProjectExcel> getLimitProjects(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return projectDao.getLimitProjects(paramMap);
	}
	
	
}