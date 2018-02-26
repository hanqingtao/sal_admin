/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pro.entity.ProjectFlow;
import com.ambition.agile.modules.pro.dao.ProjectFlowDao;

/**
 * 项目流程Service
 * @author harry
 * @version 2017-08-08
 */
@Service
@Transactional(readOnly = true)
public class ProjectFlowService extends CrudService<ProjectFlowDao, ProjectFlow> {

	@Resource
	private ProjectFlowDao projectFlowDao;
	
	public ProjectFlow get(String id) {
		return super.get(id);
	}
	
	public List<ProjectFlow> findList(ProjectFlow projectFlow) {
		return super.findList(projectFlow);
	}
	
	public Page<ProjectFlow> findPage(Page<ProjectFlow> page, ProjectFlow projectFlow) {
		return super.findPage(page, projectFlow);
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectFlow projectFlow) {
		super.save(projectFlow);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectFlow projectFlow) {
		super.delete(projectFlow);
	}
	
	
	public ProjectFlow findInfoByProId(Integer projectId) {
		return projectFlowDao.findInfoByProId(projectId);
	}
	
}