/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.center.main;

 

 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.util.MD5Encrypt;
import com.ambition.agile.common.util.MailBean;
import com.ambition.agile.common.util.MailUtil;
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
@RequestMapping(value = "${frontPath}")
public class LoginWebController extends BaseController {
	 
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private ProjectService projectService;
	
	
	
	@Autowired
	private MailUtil mailUtil;
	
	@Value("${mail.from}")  
	private String mailFrom; 
	
	/**
	 * 用户登录
	 * @param loginName
	 * @param password
	 * @param verification
	 * @param org
	 * @param orgUser
	 * @param model
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String signIn(String qualificationManagement,String loginName,String password,String verification,Org org,OrgUser orgUser,Model model, RedirectAttributes redirectAttributes,HttpSession session) {
		System.out.println("登录时显示"+qualificationManagement);
		
		if(StringUtils.isNotEmpty(qualificationManagement)&&"qualificationManagement".equals(qualificationManagement)){
			
			if(StringUtils.isNotEmpty(loginName)&&StringUtils.isNotEmpty(password)&&StringUtils.isNotEmpty(verification)){
				  model.addAttribute("loginName",loginName);
				  model.addAttribute("password",password);
	              orgUser=orgUserService.findInfo(loginName);
	              if(orgUser!=null){
	            	  if(!loginName.equals(orgUser.getLoginName())||!MD5Encrypt.encrypt(password).equals(orgUser.getPassword())){
	                	  model.addAttribute("message","用户名或密码错误" );
	                	  
	                	  return "center/login";  	
	                  }
	                  if(!"1".equals(orgUser.getStatus())){
	                	  model.addAttribute("message","用户状态失效！" );
	                	  return "center/login";   	
	                  }
	                   OrgUser user=(OrgUser)session.getAttribute("user");
	                  if(user==null){
	                	  session.setAttribute("user",orgUser);	  
	                  }
	                
	              }else{
	            	  model.addAttribute("message","您还未注册,请您注册！" );
	            	  return "center/register";    	  
	              }
	              
	              
			}
			
			
			OrgUser user = (OrgUser) session.getAttribute("user");
			OrgUser orgUser1=orgUserService.get(user);
		    Org orgObj = orgUser1.getOrg();
		    model.addAttribute("org", orgObj);
			
			return "center/mechanism/mechanism";
		}
		
		
		
		if(StringUtils.isNotEmpty(loginName)&&StringUtils.isNotEmpty(password)&&StringUtils.isNotEmpty(verification)){
			  model.addAttribute("loginName",loginName);
			  model.addAttribute("password",password);
              orgUser=orgUserService.findInfo(loginName);
              if(orgUser!=null){
            	  if(!loginName.equals(orgUser.getLoginName())||!MD5Encrypt.encrypt(password).equals(orgUser.getPassword())){
                	  model.addAttribute("message","用户名或密码错误" );
                	  
                	  return "center/login";  	
                  }
                  if(!"1".equals(orgUser.getStatus())){
                	  model.addAttribute("message","用户状态失效" );
                	  return "center/login";   	
                  }
                   OrgUser user=(OrgUser)session.getAttribute("user");
                  if(user==null){
                	  session.setAttribute("user",orgUser);	  
                  }
                
              }else{
            	  model.addAttribute("message","您还未注册,请您注册" );
            	  return "center/login";    	  
              }
		}
		
		List<Org> orgList=orgService.findOrgInfoByNum();
		List<Project> projectList=projectService.findProjectInfoByNum();
		model.addAttribute("orgList",orgList);
		model.addAttribute("projectList",projectList);
		model.addAttribute("user",session.getAttribute("user"));
		model.addAttribute("org", org);
		model.addAttribute("loginName",loginName);
		model.addAttribute("password", password);
		model.addAttribute("verification", verification);
		return "index";
	 }
	/**
	 * 退出登录
	 * @param orgUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut(OrgUser orgUser, Model model,HttpSession session) {
		model.addAttribute("orgUser", orgUser);
		session.removeAttribute("user");
		return "index";
	}
	 
	 /**
	  * 进入用户登录的入口
	  * @param orgUser
	  * @param model
	  * @return
	  */
	@RequestMapping(value = "/toLogin")
	public String toLogin(OrgUser orgUser, Model model) {
		model.addAttribute("orgUser", orgUser);
		return "center/login";
	}
	
	@RequestMapping(value = "/toValidateEmail")
	public String tofindPassWord(OrgUser orgUser, Model model) {
		model.addAttribute("orgUser", orgUser);
		return "center/org/toValidateEmail";
	}
	
	/**
	 * @see 退出登录
	 * @param orgUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	
	/**
	 * 验证用户名和邮箱是否匹配
	 *  
	 */
	@RequestMapping(value="vailUserInfoForFindPsw")
	public void vailUserInfoForFindPsw(String userName,String mail,HttpSession session,HttpServletRequest request,HttpServletResponse response,ModelAndView mv) throws IOException  {
		Map<String,String> msg=new HashMap<String,String>();
		if(StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(mail)){
			try {
				ObjectMapper mapper = new ObjectMapper();
				PrintWriter writer = response.getWriter();
				Integer num=orgUserService.findName(userName);
				if(num==0){
					msg.put("m1", "no");
					msg.put("m2", "该用户名不存在");
					String str = mapper.writeValueAsString(msg);
					writer.println(str);
				}
				
				OrgUser orgUser=orgUserService.findInfo(userName);
				String mailName=orgUser.getEmail();
				
	           if(!mailName.equals(mail)){
	        	   msg.put("m1", "no");
					msg.put("m2", "该用户名和邮箱不匹配！");
	        	   String str = mapper.writeValueAsString(msg);
					writer.println(str);
	           }else{
	        	   msg.put("m1", "yes");
					msg.put("m2", "验证成功！");
	        	   String str = mapper.writeValueAsString(msg);
					writer.println(str);
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送修改密码邮件
	 *  
	 */
	@RequestMapping(value="sendPwdMail")
	public void sendPwdMail(String userName,HttpSession session,HttpServletRequest request,HttpServletResponse response,ModelAndView mv) throws IOException  {
		 
		OrgUser user = orgUserService.findInfo(userName);
		if(null!=user&&null!=user.getEmail()){
			
			 user.setMaildate(new Date());
			 user.setUserauthvalue(MD5Encrypt.encrypt(new Date().toString()+"adks"));
			 //userDao.updateUserForFindPsd(user);
			//String  appName = BaseConfigHolder.getAppName();
			String  appName = "中央投资项目招标服务与管理平台";
			
			//创建邮件  
			StringBuffer maiContents = new StringBuffer(255);
		    MailBean mailBean = new MailBean();
		    System.out.println("################## mailFrom:"+mailFrom);
		    String mailFrom = "dragonhanqingtao@163.com";
		    mailBean.setFrom(mailFrom);
		   
		    mailBean.setSubject("密码重置邮件");  
		        
		    try{
		        String[] addresses = {user.getEmail()};
				mailBean.setToEmails(addresses); 
				String webPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				maiContents.append("请你30分钟内点击链接完成密码修改链接： <a href="+webPath+"/toPwdChangeFromMail?loginName="+ user.getLoginName() + "> 点我重置 </a>");
				maiContents.append(" <br> 如果打不开链接，请复制以下内容在 浏览器中打开."+webPath+"/toPwdChangeFromMail?loginName="+ user.getLoginName());
				maiContents.append("点我重置 ");
				mailBean.setTemplate(maiContents.toString());  
		    }catch(Exception e){
		        e.printStackTrace();
		    }
		    
		    System.out.println(mailBean.toString());
		    
		    //发送邮件  
		    try{  
		       mailUtil.send(mailBean);
		    } catch (Exception e) {  
		       e.printStackTrace();  
		    } 
		}
		response.setContentType("application/text;charset=utf-8");
		response.getWriter().write("ok"); 
	}
	
	/**
	 * 通过邮件修改密码
	 * @param request
	 * @param response
	 * @param mv
	 * @return
	 * @throws IOException 
	 *  
	 */
	/*
	@RequestMapping(value="/user/toPwdChangeFromMail")
	public ModelAndView passChangeFromMail(String userAuthValue,ModelAndView mv) {
		String view = "/pwdChange";
		if(null!=userAuthValue&&!"".equals(userAuthValue)){
			mv.addObject("userAuthValue", userAuthValue);
		}
		mv.setViewName(view);
		return mv;
	}
	*/
	
	/**
	 * @param userId
	 * @param mv
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @see   从邮箱跳到 密码修改页面
	 * @return
	 */
	@RequestMapping(value = "toPwdChangeFromMail")
	public String toPwdChangeFromMail(String loginName, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute("loginName",loginName);
		return "center/org/toPwdChangeFromMail";
	}
	 
	@RequestMapping(value="/passChange",method = RequestMethod.POST)
	public String passChange(HttpServletResponse response,String NewPwd,String loginName,Model model) {
	    if(StringUtils.isNotEmpty(NewPwd)&&StringUtils.isNotEmpty(loginName)){
	    	OrgUser user=orgUserService.findInfo(loginName);
	    	user.setPassword(MD5Encrypt.encrypt(NewPwd));
	    	orgUserService.save(user);
	    	model.addAttribute("message", "密码重置成功，请您登录！");
	    }
		return "center/login";
    }
}


