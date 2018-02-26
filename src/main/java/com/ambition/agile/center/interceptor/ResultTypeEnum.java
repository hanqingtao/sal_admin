package com.ambition.agile.center.interceptor;

/**
 * @author harry
 * @see 根据请求的类型进行处理 是否需要登录 
 */
public enum ResultTypeEnum {
	//页面dirct
    page,
    //response write json
    json,
    //验证码校验
    image_code,
    //短信码校验
    phone_code
}