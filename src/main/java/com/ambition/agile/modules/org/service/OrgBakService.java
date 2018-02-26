/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.org.dao.OrgAchieveDao;
import com.ambition.agile.modules.org.dao.OrgBakDao;
import com.ambition.agile.modules.org.dao.OrgDao;
import com.ambition.agile.modules.org.dao.OrgQualificationDao;
import com.ambition.agile.modules.org.dao.OrgStaffDao;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgAchieve;
import com.ambition.agile.modules.org.entity.OrgBak;
import com.ambition.agile.modules.org.entity.OrgQualification;
import com.ambition.agile.modules.org.entity.OrgStaff;

/**
 * Org相关数据备份
 * @author ckl
 *
 */
@Service
@Transactional(readOnly = false)
public class OrgBakService extends CrudService<OrgBakDao, OrgBak> {
	
	 @Resource
	 private OrgDao orgDao;
	 
	 @Resource
	 private OrgBakDao orgBakDao;
	 @Resource
	 private OrgAchieveDao orgAchieveDao;
	 @Resource
	 private OrgStaffDao orgStaffDao;
	 @Resource
	 private OrgQualificationDao orgQualificationDao;
	 
	 /**
	  * 备份
	  * @param org
	  * @param type 首次备案0，变更1
	  */
	 
	public void bakOrgData(Org org,String type) {
		String sn = System.currentTimeMillis()+""; //编号 ，本次备案编号
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("org", org);
		pMap.put("type", type);
		pMap.put("sn", sn);
		pMap.put("bakTime", new Date());
		//bak Org
		orgBakDao.bakOrg(pMap); 
		//bak org_achieve
		List<OrgAchieve> proList = orgAchieveDao.findAchieveByOrgId(org.getId());
		for(OrgAchieve orgAchieve:proList){
			pMap.put("orgAchieve", orgAchieve);
			orgBakDao.bakOrgAchieve(pMap); 
		}
		
		//bak org_staff
		List<OrgStaff> staffList = orgStaffDao.findStaffByOrgId(org.getId());
		for(OrgStaff orgStaff:staffList){
			pMap.put("orgStaff", orgStaff);
			orgBakDao.bakOrgStaff(pMap); 
		}
		//bak org_qualification_bak
		List<OrgQualification> qualifications = orgQualificationDao.findOrgQualificationByOrgId(Integer.parseInt(org.getId()));
		for(OrgQualification orgQualification:qualifications){
			pMap.put("orgQualification", orgQualification);
			orgBakDao.bakOrgQualification(pMap); 
		}
		
		
	}

	public Page<OrgBak> findPage(Page<OrgBak> page, OrgBak orgBak) {
		return super.findPage(page, orgBak);
	}
	/**
	 * huo qu qiye zizhi bak
	 * @param pMap
	 * @return
	 */
	public List<Map<String, Object>> getBakQualificationBySnAndOrgId(Map<String, Object> pMap) {
		return orgBakDao.getBakQualificationBySnAndOrgId(pMap);
	}

	public List<Map<String, Object>> getBakStaffBySnAndOrgId(Map<String, Object> pMap) {
		return orgBakDao.getBakStaffBySnAndOrgId(pMap);
	}

	public List<Map<String, Object>> getBakAhieveBySnAndOrgId(Map<String, Object> pMap) {
		return orgBakDao.getBakAhieveBySnAndOrgId(pMap); 
	}

	
	
}