package com.ambition.agile.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambition.agile.common.util.DateTimeUtil;
import com.ambition.agile.common.util.WxHttpClientUtil;
import com.ambition.agile.modules.users.entity.Users;

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

	    Users users = new Users();
	    users.setBeginTime( DateTimeUtil.stringToDate("2019-12-07", DateTimeUtil.DATE_STRING_YMD));
	    users.setEndTime( DateTimeUtil.stringToDate("2019-12-26", DateTimeUtil.DATE_STRING_YMD));
	    Date nowDate = new Date();
	    int isActive = 0;
	    int days=0;
	    String message = "";
		String beginTime  = DateTimeUtil.dateToString(users.getBeginTime(),DateTimeUtil.DATE_STRING_YMD);
		String endTime = DateTimeUtil.dateToString(users.getEndTime(),DateTimeUtil.DATE_STRING_YMD);
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
