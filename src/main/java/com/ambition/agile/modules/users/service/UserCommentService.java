/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.users.entity.UserComment;
import com.ambition.agile.modules.users.dao.UserCommentDao;

/**
 * 评价信息Service
 * @author harry
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class UserCommentService extends CrudService<UserCommentDao, UserComment> {

	public UserComment get(String id) {
		return super.get(id);
	}
	
	public List<UserComment> findList(UserComment userComment) {
		return super.findList(userComment);
	}
	
	public Page<UserComment> findPage(Page<UserComment> page, UserComment userComment) {
		return super.findPage(page, userComment);
	}
	
	@Transactional(readOnly = false)
	public void save(UserComment userComment) {
		super.save(userComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(UserComment userComment) {
		super.delete(userComment);
	}
	
}