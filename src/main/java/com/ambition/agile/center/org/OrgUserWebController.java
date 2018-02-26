/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.org;

 

 
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.util.ComUtil;
import com.ambition.agile.common.util.MD5Encrypt;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.org.entity.Org;
import com.ambition.agile.modules.org.entity.OrgUser;
import com.ambition.agile.modules.org.service.OrgService;
import com.ambition.agile.modules.org.service.OrgUserService;
import com.ambition.agile.modules.pro.entity.Project;
import com.ambition.agile.modules.pro.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 代理机构用户Controller
 * @author harry
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${frontPath}/org/orgUser")
public class OrgUserWebController extends BaseController {
 
	  
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private ProjectService projectService;
	
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
	
	 

	@RequestMapping(value = "form")
	public String form(OrgUser orgUser,Model model) {
		model.addAttribute("orgUser", orgUser);
	 
		return "center/login";
	}

	@RequestMapping(value = "save")
	public String save(OrgUser orgUser,Model model, RedirectAttributes redirectAttributes,
			HttpSession session,String returnUrl) {
		if (!beanValidator(model, orgUser)){
			return form(orgUser, model);
		}
        if(StringUtils.isNotEmpty(orgUser.getLoginName())){
			if(orgUserService.findName(orgUser.getLoginName())>0){
	        	addMessage(redirectAttributes, "该名已被占用");
	        	return "center/login";	
	        }else{
	        	addMessage(redirectAttributes, "该登录名可用");
	        }
		}
        orgUser.setPassword(MD5Encrypt.encrypt(orgUser.getPassword()));
        orgUser.setStatus("1");
      
        orgUser.setOrg(null);
		orgUserService.save(orgUser);
		addMessage(redirectAttributes, "保存代理机构用户成功");
		model.addAttribute("orgUser", orgUser);
//		model.addAttribute("message", "请您登录");
		
		//注册成功自动登录
		OrgUser user=orgUserService.findInfo(orgUser.getLoginName());
        session.setAttribute("user",user);	  
        List<Org> orgList=orgService.findOrgInfoByNum();
		List<Project> projectList=projectService.findProjectInfoByNum();
		model.addAttribute("orgList",orgList);
		model.addAttribute("projectList",projectList);
		model.addAttribute("user",session.getAttribute("user"));
		//快速注册返回首页，机构入口注册，返回机构注册
		if(StringUtils.isNotEmpty(returnUrl)){
//			System.out.println("返回注册页。。。。。。。。。。。。。。");
			return "center/org/orgInfo";
		}
		return "index";
	}
	
	 /**
	  * 进入用户注册的入口
	  * @param orgUser
	  * @param model
	  * @return
	  */
	@RequestMapping(value = "entry")
	public String entry(OrgUser orgUser, Model model) {
		model.addAttribute("orgUser", orgUser);
		return "center/register";
	}
	
	/**
	 * 注册是判断登录名是否可用
	 * @param loginName
	 * @param model
	 * @param mv
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "find")
	public void find(String loginName, Model model, ModelAndView mv,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		String msg=null;
		ObjectMapper mapper = new ObjectMapper();
		 if (StringUtils.isNotEmpty(loginName)) {
			try {
				if (orgUserService.findName(loginName)>0) {
					 msg="该登录名已被占用";

				}else{
					msg="该登录名可以使用";
				}
				String str = mapper.writeValueAsString(msg);
				PrintWriter writer = response.getWriter();
				writer.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}


