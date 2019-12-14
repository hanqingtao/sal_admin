/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.BaseConfigHolder;
import com.ambition.agile.common.util.WxHttpClientUtil;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.users.entity.Users;
import com.ambition.agile.modules.users.service.UsersService;

/**
 * 关注小程序的微信用户信息Controller
 * @author harry
 * @version 2018-05-04
 */
@Controller
@RequestMapping(value = "${frontPath}/wx")
public class UserApiController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(UserApiController.class);
	
	@Autowired
	private UsersService usersService;
	/**
	 * 获取用户  open_id 接口
	 * @param （）
	 * @param timestamp （请求时间, 1970 年到此时的秒数）
	 * @param sign （签名, 所有参数名升序排列后拼接成字符串后跟密钥一起MD5）
	 * @return
	 * 
	 * http://localhost:8080/wx/user/getUserInfo?code=nm884
	 * http://localhost:8080/sal/wx/user/getUserInfo?code=043df7m31eC0iQ1kzKl31RqZl31df7mc
	 * 
	 */
	@RequestMapping(value="/user/getUserInfo")
	@ResponseBody
	public ApiResponse<?> getUserInfo(String code,String nickName){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if( StringUtils.isEmpty(code)  ){
			logger.error("params error...");
			return ApiResponse.fail(500, "原因：参数有误。");
		}
		// 间隔时间判断（前后 间隔 1分钟内允许获取课程学习记录）
		 //微信端登录code值
	    String wxCode = code;
	    //Locale locale = new Locale("en", "US");
	    //ResourceBundle resource = ResourceBundle.getBundle("config/wx-config",locale);   //读取属性文件
	    //String requestUrl = resource.getString("url");  //请求地址 https://api.weixin.qq.com/sns/jscode2session
	    
//	    String requestUrl = BaseConfigHolder.getWxRequestUrl();
//	    String wxAppId = BaseConfigHolder.getWxAppID();
//	    String wxSecret = BaseConfigHolder.getWxSecret();
//	    String wxGrantType = BaseConfigHolder.getWxGrantType();
	    //System.out.println("requestUrl @@@@"+ requestUrl +"appId:"+wxAppId + "wxSecret："+ wxSecret +"wxGrantType："+wxGrantType+"code："+wxCode);
	    
	    String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
	    String wxAppId = "wx3bfbcc616d044410";
	    String wxSecret = "d2f1423075b2c441f785062bc218b08f";
	    String wxGrantType = "authorization_code";
	    
	    Map<String, String> requestUrlParam = new HashMap<String, String>();
	    requestUrlParam.put("appid",wxAppId);// resource.getString("appId"));  //开发者设置中的appId
	    requestUrlParam.put("secret", wxSecret);//resource.getString("appSecret")); //开发者设置中的appSecret
	    requestUrlParam.put("js_code", wxCode); //小程序调用wx.login返回的code
	    requestUrlParam.put("grant_type", wxGrantType);//resource.getString("grantType"));    //默认参数 authorization_code
	    System.out.println("requestUrlParam:"+requestUrlParam);
	    //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
	    JSONObject jsonObject = JSON.parseObject(WxHttpClientUtil.sendPost(requestUrl, requestUrlParam));
	    //{"openid":"oaL5V4-2b7M5_ih5aYIipBvL0fRo","session_key":"wgOhV7khu4KCDR9VGgG3lA=="}
	    String openId = (String)jsonObject.get("openid");
	    long expiresIn = 2592000;
	    System.out.println("@@@@@@@@@@jsonObject:"+jsonObject);
//		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
//		String post  =  httpClientUtil.sendHttpPost(requestUrl, requestUrlParam);
//		System.out.println("@@@@@@@@@@post:"+post);
	    //查询下是否有该 openid 入库了，如果没有则插入数据。
		if(null != openId && !openId.isEmpty()){
		    Users users = usersService.getByOpenId(openId);
		    if(null == users || users.getId().isEmpty()){
		    		users = new Users();
		    		System.out.println("$#######$"+nickName);
		    		nickName = decode(nickName);
		    		System.out.println("$#######$"+nickName);
		    		if(null != nickName && !nickName.isEmpty()){
		    			users.setName(nickName);
		    		}
		    		System.out.println("$$$$$$$"+users.getName());
		    		users.setOpenId(openId);
		    		usersService.save(users);
		    }
		}
		jsonObject.put("expires_in", expiresIn);
	    System.out.println(jsonObject);
	    //return jsonObject;
		return ApiResponse.success("返回用户信息.", jsonObject);
	}
	
	/**
	 * 获取用户  open_id 接口
	 * @param （）
	 * @param timestamp （请求时间, 1970 年到此时的秒数）
	 * @param sign （签名, 所有参数名升序排列后拼接成字符串后跟密钥一起MD5）
	 * @return
	 * 
	 * http://localhost:8080/wx/user/getUserInfo?code=nm884
	 * http://localhost:8080/sal/wx/user/getUserInfo?code=043df7m31eC0iQ1kzKl31RqZl31df7mc
	 * 
	 */
	@RequestMapping(value="/user/codeActive")
	@ResponseBody
	public ApiResponse<?> codeActive(String openId,String code,String password){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if( StringUtils.isEmpty(code) || StringUtils.isEmpty(password) ){
			logger.error("params error...");
			return ApiResponse.fail(500, "原因：参数有误。");
		}
		// 间隔时间判断（前后 间隔 1分钟内允许获取课程学习记录）
		 //微信端登录code值
	    String wxCode = code;

	    String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
	    String wxAppId = "wx3bfbcc616d044410";
	    String wxSecret = "d2f1423075b2c441f785062bc218b08f";
	    String wxGrantType = "authorization_code";
	    
	    Map<String, String> requestUrlParam = new HashMap<String, String>();
	    requestUrlParam.put("appid",wxAppId);// resource.getString("appId"));  //开发者设置中的appId
	    requestUrlParam.put("secret", wxSecret);//resource.getString("appSecret")); //开发者设置中的appSecret
	    requestUrlParam.put("js_code", wxCode); //小程序调用wx.login返回的code
	    requestUrlParam.put("grant_type", wxGrantType);//resource.getString("grantType"));    //默认参数 authorization_code
	    System.out.println("requestUrlParam:"+requestUrlParam);
	    //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
	    JSONObject jsonObject = JSON.parseObject(WxHttpClientUtil.sendPost(requestUrl, requestUrlParam));
	    //{"openid":"oaL5V4-2b7M5_ih5aYIipBvL0fRo","session_key":"wgOhV7khu4KCDR9VGgG3lA=="}
	    openId = null;//(String)jsonObject.get("openid");
	    long expiresIn = 2592000;
	    System.out.println("@@@@@@@@@@jsonObject:"+jsonObject);
//		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
//		String post  =  httpClientUtil.sendHttpPost(requestUrl, requestUrlParam);
//		System.out.println("@@@@@@@@@@post:"+post);
	    //查询下是否有该 openid 入库了，如果没有则插入数据。
		if(null != openId && !openId.isEmpty()){
		    Users users = usersService.getByOpenId(openId);
		    if(null == users || users.getId().isEmpty()){
		    		users = new Users();
		    		
		    		System.out.println("$$$$$$$"+users.getName());
		    		users.setOpenId(openId);
		    		usersService.save(users);
		    }
		}
		jsonObject.put("expires_in", expiresIn);
	    System.out.println(jsonObject);
	    //return jsonObject;
		return ApiResponse.success("返回用户信息.", jsonObject);
	}
	
	
	@RequestMapping(value="/user/uploadUserVoice")
	@ResponseBody
	public ApiResponse<?> uploadUserVoice(String openId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if( StringUtils.isEmpty(openId)  ){
			logger.error("params error...");
			return ApiResponse.fail(500, "原因：参数有误。");
		}
		// 间隔时间判断（前后 间隔 1分钟内允许获取课程学习记录）
		 //微信端登录code值
	    /*
	    String requestUrl = BaseConfigHolder.getWxRequestUrl();
	    String wxAppId = BaseConfigHolder.getWxAppID();
	    String wxSecret = BaseConfigHolder.getWxSecret();
	    String wxGrantType = BaseConfigHolder.getWxGrantType();
	    */
	    String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
	    String wxAppId = "wxaa6536bb52e66d04";
	    String wxSecret = "fc95808e8f55b896b142e645f75e1cf4";
	    String wxGrantType = "authorization_code";
	    
	    Map<String, String> requestUrlParam = new HashMap<String, String>();
	    requestUrlParam.put("appid",wxAppId);    // resource.getString("appId"));  //开发者设置中的appId
	    requestUrlParam.put("secret", wxSecret); //resource.getString("appSecret")); //开发者设置中的appSecret
	    requestUrlParam.put("js_code", openId);  //小程序调用wx.login返回的code
	    requestUrlParam.put("grant_type", wxGrantType);//resource.getString("grantType"));    //默认参数 authorization_code
	    
	    //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
	    JSONObject jsonObject = JSON.parseObject(WxHttpClientUtil.sendPost(requestUrl, requestUrlParam));
	    //{"openid":"oaL5V4-2b7M5_ih5aYIipBvL0fRo","session_key":"wgOhV7khu4KCDR9VGgG3lA=="}
	   // String openId = (String)jsonObject.get("openid");
	    System.out.println("@@@@@@@@@@jsonObject:"+jsonObject);
	    //HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
	    //String post  =  httpClientUtil.sendHttpPost(requestUrl, requestUrlParam);
	    //System.out.println("@@@@@@@@@@post:"+post);
	    //查询下是否有该 openid 入库了，如果没有则插入数据。
		
	    //return jsonObject;
		return ApiResponse.success("返回用户信息.", jsonObject);
	}
	
	 public static String decode(String param){
	        String result= null;
	        try {
	            result = new String(param.getBytes("utf-8"), "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	
	
}