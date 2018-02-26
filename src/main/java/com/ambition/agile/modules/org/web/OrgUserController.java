/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 代理机构用户Controller
 * @author harry
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/org/orgUser")
public class OrgUserController extends BaseController {

	@Autowired
	private OrgUserService orgUserService;
	
	@ModelAttribute
	public OrgUser get(@RequestParam(required=false) String id) {
		OrgUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orgUserService.get(id);
		}
		if (entity == null){
			entity = new OrgUser();
		}
		return entity;
	}
	
	@RequiresPermissions("org:orgUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrgUser orgUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//根据不同的管理员权限显示 不同用户填写的  文章 信息
				//获取管理员的权限及级别进行数据查询 h  
				User user = UserUtils.getUser();
				List<String> roleIdList = user.getRoleIdList();
				if(roleIdList != null && roleIdList.size() > 0 && null != user){				
					// 默认为省级管理员
					boolean s_sys = true;
					for(String roleId : roleIdList){
						// 招标中心管理员
						if(roleId.equals("5")){  
							s_sys = false; break;
						}
					}
					if(s_sys){
						//省市 发改委 只能看自己本省 / 本市的
						//org.setArea(user.getOffice().getArea());
						if(null != user.getCompany() && null != user.getCompany().getArea() &&
								null != user.getCompany().getArea().getId()){
							if(null == orgUser){
								orgUser = new OrgUser();
							}
							if(null == orgUser.getOrg()){
								Org org = new Org();
								org.setArea(user.getCompany().getArea());
								orgUser.setOrg(org);
							}
							
						}
					}else{
						//如果是招标 中心管理员  能看全部  的 用户
						
					}
				}
		
		Page<OrgUser> page = orgUserService.findPage(new Page<OrgUser>(request, response), orgUser); 
		model.addAttribute("page", page);
		return "modules/org/orgUserList";
	}

	@RequiresPermissions("org:orgUser:view")
	@RequestMapping(value = "form")
	public String form(OrgUser orgUser, Model model) {
		model.addAttribute("orgUser", orgUser);
		return "modules/org/orgUserForm";
	}

	@RequiresPermissions("org:orgUser:edit")	
	@RequestMapping(value = "save")
	public String save(OrgUser orgUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orgUser)){
			return form(orgUser, model);
		}
		orgUserService.save(orgUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgUser/?repage";
	}
	
	@RequiresPermissions("org:orgUser:edit")
	@RequestMapping(value = "delete")
	public String delete(OrgUser orgUser, RedirectAttributes redirectAttributes) {
		orgUserService.delete(orgUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/org/orgUser/?repage";
	}

}