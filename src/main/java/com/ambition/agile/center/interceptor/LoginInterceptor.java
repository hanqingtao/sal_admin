package com.ambition.agile.center.interceptor;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import com.ambition.agile.modules.org.entity.OrgUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	System.out.println("preHandle: loginInteceptor:"+request.getRequestURI());
    	//防止跨站点请求伪造，对Referer验证 
//    	String referer = request.getHeader("Referer");
//    	String baseDomain = BaseConfigHolder.getServerDomain();
//    	 System.out.println("referer:"+referer+" 	domain:"+baseDomain);
//    	if(referer != null && !(referer.trim().startsWith(baseDomain))&&!(referer.trim().startsWith("http://www.tddx.cn"))
//    			&&!(referer.trim().startsWith("https://mail.qq.com"))	
//    		){
//    		response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html;charset=UTF-8");
//            OutputStream out = response.getOutputStream();
//            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "utf-8"));
//            Map<String, Object> retInfo = new HashMap<String, Object>();
//            //retInfo.put("status", ApiRetCode.INVALID_USER_CODE);
//            retInfo.put("error", "请求来源不可信!");
//            pw.println(JSON.toJSONString(retInfo));
//            pw.flush();
//            pw.close();
//    		return false;
//    	}
    	
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {

            HandlerMethod _handler = (HandlerMethod) handler;
            RequireLogin login = _handler.getMethodAnnotation(RequireLogin.class);
            
            OrgUser orgUser = (OrgUser)request.getAttribute("user");
            
            // 没有声明权限,放行
            if (null == login) {
            	if(null != orgUser && StringUtils.isNotEmpty(orgUser.getId())){
            		request.setAttribute("userId", orgUser.getId());
            	}else{
            		request.setAttribute("userId", null);
            	}
            	return true;
            }
            //根据 cookie 获取当前登录用户
            //int userId = CookieUtil.getUserIdByCookie(request, RedisConstants.COOKIE_ID);
            if (orgUser == null) {
                // 没有获取到用户登录状态
                if (login.value() == ResultTypeEnum.page) {
	                //传统页面的登录
                	request.getRequestDispatcher("/login.jsp").forward(request, response);
	                     
                } else if (login.value() == ResultTypeEnum.json) {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/html;charset=UTF-8");
                    OutputStream out = response.getOutputStream();
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "utf-8"));
                    Map<String, Object> retInfo = new HashMap<String, Object>();
                    //retInfo.put("status", ApiRetCode.INVALID_USER_CODE);
                    retInfo.put("data", "请您登录!");
                    //retInfo.put("message", PropertiesFactory.getProperties(PropertiesFactory.MESSAGEPROPERTIES).getProperty("relogin"));
                    pw.println(JSON.toJSONString(retInfo));
                    pw.flush();
                    pw.close();
                }
                return false;
            }
            request.setAttribute("userId", orgUser.getId());
            return true;
        } else {
            return true;
        }
    }
}
