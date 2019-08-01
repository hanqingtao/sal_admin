package com.thinkgem.jeesite.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambition.agile.common.util.WxHttpClientUtil;

public class Test {

	public static void main(String[] args) {
		
		 String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
		    String wxAppId = "wx3bfbcc616d044410";
		    String wxSecret = "d2f1423075b2c441f785062bc218b08f";
		    String wxGrantType = "authorization_code";
		    System.out.println("SSS");
		    Map<String, String> requestUrlParam = new HashMap<String, String>();
		    requestUrlParam.put("appid",wxAppId);    // resource.getString("appId"));  //开发者设置中的appId
		    requestUrlParam.put("secret", wxSecret); //resource.getString("appSecret")); //开发者设置中的appSecret
		   // requestUrlParam.put("js_code", openId);  //小程序调用wx.login返回的code
		    requestUrlParam.put("grant_type", wxGrantType);//resource.getString("grantType"));    //默认参数 authorization_code
		    System.out.println(" #$$$$$$$$");
		    //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
		    JSONObject jsonObject = JSON.parseObject(WxHttpClientUtil.sendPost(requestUrl, requestUrlParam));
		    //{"openid":"oaL5V4-2b7M5_ih5aYIipBvL0fRo","session_key":"wgOhV7khu4KCDR9VGgG3lA=="}
		   // String openId = (String)jsonObject.get("openid");
		    System.out.println("@@@@@@@@@@jsonObject:"+jsonObject);
		    
	}
}
