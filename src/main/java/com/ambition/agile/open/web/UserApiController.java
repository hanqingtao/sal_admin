/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.ambition.agile.common.util.DateTimeUtil;
import com.ambition.agile.common.util.WxHttpClientUtil;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.users.entity.Cdkey;
import com.ambition.agile.modules.users.entity.Users;
import com.ambition.agile.modules.users.service.CdkeyService;
import com.ambition.agile.modules.users.service.UsersService;
import com.google.gson.JsonObject;

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
	
	@Autowired
	private CdkeyService cdkeyService;
	
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
		System.out.println("########"+code);
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
	    int isActive = 0;//状态
	    String beginTime=null;
	    String endTime=null;
	    int days=0;//
	    String message="返回用户信息";//信息
	    Date nowDate = new Date();
	    //查询下是否有该 openid 入库了，如果没有则插入数据。
		if(null != openId && !openId.isEmpty()){
		    Users users = usersService.getByOpenId(openId);
		    //获取 users 表中的数据 如果存在，则判断时间上是否过期
		    if(null != users && StringUtils.isNotEmpty(users.getId())){
		    		//如果用户存在，则判断开始和结束时间 是否在有效期内
		    		if(users.getBeginTime() != null && 
		    				users.getEndTime() != null){
		    			beginTime  = DateTimeUtil.dateToString(users.getBeginTime(),DateTimeUtil.DATE_STRING_YMD);
		    			endTime = DateTimeUtil.dateToString(users.getEndTime(),DateTimeUtil.DATE_STRING_YMD);
		    			int begin = DateTimeUtil.compare_date(nowDate,users.getBeginTime());
		    			int end = DateTimeUtil.compare_date(users.getEndTime(),nowDate);
		    			if( begin>=1 && end >=1  ){
		    				isActive = 1;
		    			     days = DateTimeUtil.daysBetween(nowDate, users.getEndTime());
		    			     message = "激活码有效，使用期限至"+endTime+"还有多少"+days+"天到期.";
		    			}
		    			if(begin<0 ){
		    				isActive = 0;
		    				message = "激活码无效，未到使用期限,生效时间是: "+beginTime;
		    			}
		    			if(end<0 ){
		    				isActive = 0;
		    				message = "激活码无效，已过使用期限,最后使用时间是: "+endTime;
		    			}
		    		}
		    }

		}
		jsonObject.put("isActive",isActive );
		jsonObject.put("days", days);
		jsonObject.put("beginTime", beginTime);
		jsonObject.put("endTime", endTime);
		jsonObject.put("expires_in", expiresIn);
	    System.out.println(jsonObject);
	    //return jsonObject;
		return ApiResponse.success(message, jsonObject);
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
		System.out.println("openId:"+openId + "code:"+code + "password"+password);
		//间隔时间判断（前后 间隔 1分钟内允许获取课程学习记录）
		Cdkey cdkey = new Cdkey();
		cdkey.setCode(code);
		cdkey.setPassword(password);
		cdkey.setStatus("0");
		//0 未用 1 激活 2作废
		List<Cdkey> cdkeyList = cdkeyService.findByCodePassword(cdkey);
		//如果没有查到激活码信息，则返回错误提示
		String message = "";
		int isActive = 0;//状态
		String beginTime=null;
	    String endTime=null;
	    int days=0;//
	    Date nowDate = new Date();
		if(cdkeyList.isEmpty()){
			message = "激活码或密码错误!";
		}else{
			//如果查到激活码信息，则激活成功
			if(cdkeyList.size()>0){
				Cdkey cdkeyTemp = cdkeyList.get(0);
				if(null != cdkeyTemp && StringUtils.isNotEmpty(cdkeyTemp.getId())){
					//查询 openid 是否插入到数据库中过
					Users users = usersService.getByOpenId(openId);
					//如果查询出来的 users 为空,则进行如下判断
					if(null == users || StringUtils.isEmpty(users.getId())){
					    //if(null == users || users.getId().isEmpty()){
					    		users = new Users();
					    		//nickName = decode(nickName);
	//				    		//System.out.println("$#######$"+nickName);
	//				    		if(null != nickName && !nickName.isEmpty()){
	//				    			users.setName(nickName);
	//				    		}
					    		users.setOpenId(openId);
					    		users.setCdkeyId(Integer.parseInt(cdkeyTemp.getId()));
					    		users.setBeginTime(cdkeyTemp.getBatch().getBeginTime());
					    		users.setEndTime(cdkeyTemp.getBatch().getEndTime());
					    		//这儿要加上事务，处理 users cdkey beginTime endTime 和 cdkey status
					    		
					    		usersService.codeActive(users,cdkeyTemp);
					    }else{
					    		//如果 该用户已经在 users 表中有记录，则进行时间的更新
						    	users.setOpenId(openId);
					    		users.setCdkeyId(Integer.parseInt(cdkeyTemp.getId()));
					    		users.setBeginTime(cdkeyTemp.getBatch().getBeginTime());
					    		users.setEndTime(cdkeyTemp.getBatch().getEndTime());
					    		//这儿要加上事务，处理 users cdkey beginTime endTime 和 cdkey status
					    		usersService.codeActive(users,cdkeyTemp);
					    }
					//进行数据的判断 逻辑
					if(users.getBeginTime() != null && 
							users.getEndTime() != null){
						beginTime  = DateTimeUtil.dateToString(users.getBeginTime(),DateTimeUtil.DATE_STRING_YMD);
						endTime = DateTimeUtil.dateToString(users.getEndTime(),DateTimeUtil.DATE_STRING_YMD);
						int begin = DateTimeUtil.compare_date(nowDate,users.getBeginTime());
						int end = DateTimeUtil.compare_date(users.getEndTime(),nowDate);
						if( begin>=1 && end >=1  ){
							isActive = 1;
						     days = DateTimeUtil.daysBetween(nowDate, users.getEndTime());
						     message = "激活码有效，使用期限至"+endTime+"还有多少"+days+"天到期.";
						}
						if(begin<0 ){
							isActive = 0;
							message = "激活码无效，未到使用期限,生效时间是: "+beginTime;
						}
						if(end<0 ){
							isActive = 0;
							message = "激活码无效，已过使用期限,最后使用时间是: "+endTime;
						}
					}
					
				}
			}
			}
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("isActive",isActive );
		jsonObject.put("days", days);
		jsonObject.put("beginTime", beginTime);
		jsonObject.put("endTime", endTime);
		jsonObject.put("message", message);
		//jsonObject.put("expires_in", expiresIn);
	    System.out.println(jsonObject);
	    //return jsonObject;
		return ApiResponse.success(message, jsonObject);
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