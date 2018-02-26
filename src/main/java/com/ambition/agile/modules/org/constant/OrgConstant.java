package com.ambition.agile.modules.org.constant;

public class OrgConstant {

	//以下为代理机构的状态变量
	//初始注册未提交
	public static final String ORG_STATUS_INIT = "0";   
	//提交
    public static final String ORG_STATUS_SUBMIT = "1";
    //招标中心通过
	public static final String ORG_STATUS_PASS = "3";
	//黑名单
    public static final String ORG_STATUS_BLANK = "4";
    //省级通过
    public static final String ORG_STATUS_PROVINCE = "2";   
    //注销
    public static final String ORG_STATUS_CANCEL = "7";   
    //省级拒绝
    public static final String ORG_STATUS_PROVINCE5 = "5";   
  //招标中心拒绝
    public static final String ORG_STATUS_PROVINCE6 = "6";  
   //暂停
    public static final String ORG_STATUS_STOP8 = "8";  
    
    //从黑名单恢复到正常状态标识
    public static final String ORG_STATUS_USE9 = "9"; 
}
