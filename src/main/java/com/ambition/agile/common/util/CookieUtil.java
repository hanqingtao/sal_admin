package com.ambition.agile.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.common.constant.TimeConstants;




/*
 * @author: hqt
 * @see: Cookie工具类,封装Cookie常用操作
 * @version v 0.1
 * @date:2016-5-19 下午05:00:58
 */
public class CookieUtil {
	
	 /**
     * 设置cookie有效期，根据需要自定义[本系统设置为30天] -1 为在浏览打开周期内有效
     */
    private final static int COOKIE_MAX_AGE = -1;//30 * 24 *  60 * 60;

    /**
     *
     * @desc 删除指定Cookie
     * @param response
     * @param cookie
     */
    public static void removeCookie(HttpServletResponse response, Cookie cookie)
    {
            if (cookie != null)
            {
                    cookie.setPath("/");
                    cookie.setValue("");
                    cookie.setMaxAge(TimeConstants.CLIENT_COOKIE_MAXAGE_NOW);
                    response.addCookie(cookie);
            }
    }

    /**
     *
     * @desc 删除指定Cookie
     * @param response
     * @param cookie
     * @param domain
     */
    public static void removeCookie(HttpServletResponse response, Cookie cookie,String domain)
    {
            if (cookie != null)
            {
                    cookie.setPath("/");
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    cookie.setDomain(domain);
                    response.addCookie(cookie);
            }
    }

    /**
     *
     * @desc 根据Cookie名称得到Cookie的值，没有返回Null
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name)
    {
            Cookie cookie = getCookie(request, name);
            if (cookie != null)
            {
                    return cookie.getValue();
            }
            else
            {
                    return null;
            }
    }
    
    /**
    *
    * @desc 根据Cookie名称得到Cookie的值，没有返回 0
    * @param request
    * @param name
    * @return
    */
   public static int getUserIdByCookie(HttpServletRequest request, String name)
   {
           Cookie cookie = getCookie(request, name);
           try{
	           if (cookie != null)
	           {
	        	   	if(ComUtil.isNotNullOrEmpty( cookie.getValue())){
	        	   		String userIdStr = cookie.getValue();
	        	   		return Integer.parseInt(userIdStr);
	        	   	}
	           }
           }catch(Exception  e){
        	   e.printStackTrace();
        	   return 0;
           }
           return 0;
   }

    /**
     *
     * @desc 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     * @param request
     * @param name
     */
    public static Cookie getCookie(HttpServletRequest request, String name)
    {
            Cookie cookies[] = request.getCookies();
            if (cookies == null || name == null || name.length() == 0)
                return null;
            Cookie cookie = null;
            for (int i = 0; i < cookies.length; i++)
            {
                if (cookies[i].getName().equals(name)){
                	cookie = cookies[i];
                	break;
                }
                //if (request.getServerName().equals(cookie.getDomain()))
                //    break;
            }
            return cookie;
    }
    /*
     * Cookie[] cookies = request.getCookies();
			if(ComUtil.isNotNullOrEmpty(cookies)){
			for(Cookie cookie :cookies ){
				if("cookieId".equals(cookie.getName())){
						isExitFlag = true;
						String value = cookie.getValue();
						cookie.setValue(guid);
						logger.info("logincontroller login token-usersesion放入 redis ",guid ,_user.getId()+" 失效时间:"+maxTime+"");
						System.out.println("#########"+ value+"@@@@@"+request.getServletPath());
				}
			}
		}
     */

    /**
     *
     * @desc 添加一条新的Cookie信息，默认有效时间为一个月
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response, String name, String value)
    {
            addCookie(response, name, value, COOKIE_MAX_AGE);
    }

    /**
     *
     * @desc 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge)
    {
            if (value == null)
                    value = "";
            //对value加密
            DesEncrypt des = new DesEncrypt();
            value = des.strEnc(value, Constants.DES_KEY1, Constants.DES_KEY2, Constants.DES_KEY3);
            Cookie cookie = new Cookie(name, value);
            if(maxAge!=0){
            	cookie.setMaxAge(maxAge);
            }else{
            	cookie.setMaxAge(COOKIE_MAX_AGE);
            }
            cookie.setPath("/");
            response.addCookie(cookie);
    }
}
