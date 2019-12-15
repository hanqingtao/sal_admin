package com.ambition.agile.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambition.agile.common.util.WxHttpClientUtil;

public class WxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ?grant_type=client_credential&appid=APPID&secret=APPSECRET
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
		//String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
	    String wxAppId = "wx3bfbcc616d044410";
	    String wxSecret = "d2f1423075b2c441f785062bc218b08f";
	    String wxGrantType = "client_credential";
	    String openId = "023mVZRk1ozNSn0KttSk1DQjSk1mVZRr";
	    Map<String, String> requestUrlParam = new HashMap<String, String>();
	    requestUrlParam.put("appid",wxAppId);    // resource.getString("appId"));  //开发者设置中的appId
	    requestUrlParam.put("secret", wxSecret); //resource.getString("appSecret")); //开发者设置中的appSecret
	    requestUrlParam.put("js_code", openId);  //小程序调用wx.login返回的code
	    requestUrlParam.put("grant_type", wxGrantType);//resource.getString("grantType"));    //默认参数 authorization_code
	    String str = WxHttpClientUtil.sendPost(requestUrl, requestUrlParam);
	    System.out.println(str);
	    JSONObject jsonObject = JSON.parseObject(str);
	    String access_token = jsonObject.getString("access_token");
	    System.out.println(access_token);
	    
	    //获取二维码，有的还需要传入参数
	    String wxacodeunlimitUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
	    wxacodeunlimitUrl = wxacodeunlimitUrl.replace("ACCESS_TOKEN", access_token);
	    Map<String, String> wxacodeunlimitUrlParam = new HashMap<String, String>();
	    //wxacodeunlimitUrlParam.put("access_token", access_token);
	    wxacodeunlimitUrlParam.put("scene","1");    // resource.getString("appId"));  //开发者设置中的appId
	    wxacodeunlimitUrlParam.put("page", "pages/index/index"); //resource.getString("appSecret")); //开发者设置中的appSecret
	    String strWxacode = WxHttpClientUtil.sendPost(wxacodeunlimitUrl, wxacodeunlimitUrlParam);
	    System.out.println("@@@@@@"+strWxacode);
	    
	}

}
