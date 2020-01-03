package com.ambition.agile.common.constant;


/**
 * @Description:定义系统常量
 * @author: harry
 * @date: 2014-8-14 下午05:28:24
 */
public class Constants {
	
	public static final String SYS_USER_ID = "1";
	
	public static final String INTERCEPTOR_HANDLE_USERID= "userId";
	
	public static final String INTERCEPTOR_HANDLE_USER= "user";
	
	
	public static final Integer ZERO = 0;
	
	public static final Integer  YES = 1;
	
	public static final Integer NO = 2;
	
	//前台页面使用的变量
	public static final String CACHE_CONTROL_MAX_AGE = "cache-Control.max-age";
	
	public static final String CACHE_CONTROL_CONTENT = "cache-Control.content";
	
	public static final String ANSWER_TYPE_ONE_ONE = "1";
	
	public static final String ANSWER_TYPE_ONE_TWO= "2";
	
	public static final Integer ANSWER_TYPE_LENGTH= 70;
	
	//精度值
	public static final Integer precisionNum = 100;
	
	//秒和小时的进度转换精度值
	public static final Integer secondNum = 3600;
	
	/** 页面分页条数 */
	public static final Integer PAGE_SIZE_THREE = 3;
	public static final Integer PAGE_SIZE_FIVE = 5;
	public static final Integer PAGE_SIZE_SIX = 6;
	public static final Integer PAGE_SIZE_SIXTEEN = 16;
	public static final Integer PAGE_SIZE_TEN = 10;
	
	/** 页面ajax 提交处理后返回的结果标识 **/
	public static String RESULT_SUCC = "succ";  
	public static String RESULT_ERROR = "error";
	
	/**民族枚举*/
	public static final Integer MZ = 213;
	
	public static final Integer ZIMU = 218;  //字母
	public static final Integer AUTHOR_ORG = 223; //讲师单位

	public static final String DES_KEY1 = "tddx1";
	public static final String DES_KEY2 = "tddx2";
	public static final String DES_KEY3 = "tddx3";
	//excel 
	public static final String  POINT = ".";
	
	//users cdkey  未使用
	public static final String CDKEY_STATUS_NOUSE = "0";
	//已激活
	public static final String CDKEY_STATUS_USED = "1";
	//cancel 作废
	public static final String CDKEY_STATUS_CANCEL = "2";
	
}
