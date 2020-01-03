/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.UsersCdkey;
import com.ambition.agile.modules.users.dao.UsersCdkeyDao;

/**
 * 用户激活码关系Service
 * @author harry
 * @version 2020-01-02
 */
@Service
@Transactional(readOnly = true)
public class UsersCdkeyService extends CrudService<UsersCdkeyDao, UsersCdkey> {

	public UsersCdkey get(String id) {
		return super.get(id);
	}
	
	public List<UsersCdkey> findList(UsersCdkey cdkeyUsers) {
		return super.findList(cdkeyUsers);
	}
	
	public Page<UsersCdkey> findPage(Page<UsersCdkey> page, UsersCdkey cdkeyUsers) {
		return super.findPage(page, cdkeyUsers);
	}
	
	@Transactional(readOnly = false)
	public void save(UsersCdkey cdkeyUsers) {
		super.save(cdkeyUsers);
	}
	
	@Transactional(readOnly = false)
	public void delete(UsersCdkey cdkeyUsers) {
		super.delete(cdkeyUsers);
	}
	
}