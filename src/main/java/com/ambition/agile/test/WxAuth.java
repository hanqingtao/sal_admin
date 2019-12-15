package com.ambition.agile.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambition.agile.common.util.WxHttpClientUtil;

public class WxAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ?grant_type=client_credential&appid=APPID&secret=APPSECRET
	    String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
	    String wxAppId = "wx3bfbcc616d044410"; 
	    String wxSecret = "d2f1423075b2c441f785062bc218b08f";
	    String wxGrantType = "authorization_code";
		String wxCode = "04300iFw0Us91c1vkHHw0u84Fw000iFc";
	    Map<String, String> requestUrlParam = new HashMap<String, String>();
	    requestUrlParam.put("appid",wxAppId);    // resource.getString("appId"));  //开发者设置中的appId
	    requestUrlParam.put("secret", wxSecret); //resource.getString("appSecret")); //开发者设置中的appSecret
	    requestUrlParam.put("js_code", wxCode);  //小程序调用wx.login返回的code
	    requestUrlParam.put("grant_type", wxGrantType);//resource.getString("grantType"));    //默认参数 authorization_code
	    String str = WxHttpClientUtil.sendPost(requestUrl, requestUrlParam);
	    System.out.println(str);
	    JSONObject jsonObject = JSON.parseObject(str);
	    String access_token = jsonObject.getString("access_token");
	    System.out.println(access_token);
	    
	   //https://api.weixin.qq.com/sns/jscode2session?appid=wx3bfbcc616d044410&secret=d2f1423075b2c441f785062bc218b08f&js_code=04300iFw0Us91c1vkHHw0u84Fw000iFc&grant_type=authorization_code

	    
	    
	}

}
