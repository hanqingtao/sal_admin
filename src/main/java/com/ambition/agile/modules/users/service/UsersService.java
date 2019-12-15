/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.dao.CdkeyDao;
import com.ambition.agile.modules.users.dao.UsersDao;
import com.ambition.agile.modules.users.entity.Cdkey;
import com.ambition.agile.modules.users.entity.Users;

/**
 * 学员信息Service
 * @author harry
 * @version 2019-07-10
 */
@Service
@Transactional(readOnly = true)
public class UsersService extends CrudService<UsersDao, Users> {

	@Autowired
	private CdkeyDao cdkeyDao;
	
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
	
	public Users getByOpenId(String openId){
		Users users = dao.getByOpenId(openId);
		return users;
	}
	
	//这儿要加上事务，处理 users cdkey beginTime endTime 和 cdkey status
	@Transactional(readOnly = false)
	public void codeActive(Users users ,Cdkey cdkey) {
		
		//更新 用户表
		super.save(users);
		//update cdkey status  1 为已激活状态 
		cdkey.setStatus("1");
		cdkey.setActiveDate(new Date());
		cdkeyDao.update(cdkey);
		
	}
	
}