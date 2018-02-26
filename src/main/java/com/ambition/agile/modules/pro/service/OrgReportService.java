/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.pro.entity.OrgReport;
import com.ambition.agile.modules.pro.dao.OrgReportDao;

/**
 * 代理机构举报信息Service
 * @author harry
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class OrgReportService extends CrudService<OrgReportDao, OrgReport> {

	public OrgReport get(String id) {
		return super.get(id);
	}
	
	public List<OrgReport> findList(OrgReport orgReport) {
		return super.findList(orgReport);
	}
	
	public Page<OrgReport> findPage(Page<OrgReport> page, OrgReport orgReport) {
		return super.findPage(page, orgReport);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgReport orgReport) {
		super.save(orgReport);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgReport orgReport) {
		super.delete(orgReport);
	}
	
}