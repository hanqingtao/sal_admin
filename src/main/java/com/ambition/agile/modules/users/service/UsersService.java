/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.Users;
import com.ambition.agile.modules.users.dao.UsersDao;

/**
 * 用户信息Service
 * @author harry
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class UsersService extends CrudService<UsersDao, Users> {

	public Users get(String id) {
		return super.get(id);
	}
	
	public List<Users> findList(Users users) {
		return super.findList(users);
	}
	
	public Page<Users> findPage(Page<Users> page, Users users) {
		return super.findPage(page, users);
	}
	
	@Transactional(readOnly = false)
	public void save(Users users) {
		super.save(users);
	}
	
	@Transactional(readOnly = false)
	public void delete(Users users) {
		super.delete(users);
	}
	
}