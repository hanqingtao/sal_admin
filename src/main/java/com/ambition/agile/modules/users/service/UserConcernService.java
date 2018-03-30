/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.UserConcern;
import com.ambition.agile.modules.users.dao.UserConcernDao;

/**
 * 我的关注Service
 * @author harry
 * @version 2018-03-10
 */
@Service
@Transactional(readOnly = true)
public class UserConcernService extends CrudService<UserConcernDao, UserConcern> {

	public UserConcern get(String id) {
		return super.get(id);
	}
	
	public List<UserConcern> findList(UserConcern userConcern) {
		return super.findList(userConcern);
	}
	
	public Page<UserConcern> findPage(Page<UserConcern> page, UserConcern userConcern) {
		return super.findPage(page, userConcern);
	}
	
	@Transactional(readOnly = false)
	public void save(UserConcern userConcern) {
		super.save(userConcern);
	}
	
	@Transactional(readOnly = false)
	public void delete(UserConcern userConcern) {
		super.delete(userConcern);
	}
	
}