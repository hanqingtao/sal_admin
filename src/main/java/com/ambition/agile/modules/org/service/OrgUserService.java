/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.dao.OrgUserDao;

/**
 * 代理机构用户Service
 * @author harry
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class OrgUserService extends CrudService<OrgUserDao, OrgUser> {

	 @Resource
	 private OrgUserDao orgUserDao;
	 
	public OrgUser get(String id) {
		OrgUser orgUser = super.get(id);
		return orgUser;
	}
	
	public List<OrgUser> findList(OrgUser orgUser) {
		return super.findList(orgUser);
	}
	
	public Page<OrgUser> findPage(Page<OrgUser> page, OrgUser orgUser) {
		return super.findPage(page, orgUser);
	}
	
	@Transactional(readOnly = false)
	public void save(OrgUser orgUser) {
		super.save(orgUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrgUser orgUser) {
		super.delete(orgUser);
	}
	/**
	 * 查询用户名是否重复
	 * @param loginName
	 * @return
	 */
	public Integer findName(String loginName) {
		return orgUserDao.findLoginName(loginName);
	}
	/**
	 * 根据用户名查询用户信息
	 * @param loginName
	 * @return
	 */
	public OrgUser findInfo(String loginName) {
		return orgUserDao.findInfoByName(loginName);
	}
	
	public List<String> findEmailById(String id){
		return orgUserDao.findEmailById(id);
	}
}