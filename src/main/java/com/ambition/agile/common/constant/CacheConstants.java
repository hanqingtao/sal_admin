package com.ambition.agile.common.constant;

/**
 * @Description:定义缓存常量
 * @author: lxh
 * @date: 2014-8-14 下午05:28:24
 */
public class CacheConstants {
	
	public static final String  DAOBEAN_NAME="cacheRedisDao";
	
	/**
	 * 用户前缀
	 */
	public static final String USER = "u_";
	
	/**
	 * 班级前缀
	 */
	public static final String GRADE = "g_";
	
	/**
	 * 课程前缀
	 */
	public static final String COURSE = "c_";
	
	public static final String USER_CLASS_NAME = "com.adks.ulms.center.user.model.User";
	
	public static final String GRADE_CLASS_NAME = "com.adks.ulms.center.grade.model.Grade";
	
	public static final String COURSE_CLASS_NAME = "com.adks.ulms.center.course.model.Course";
	
	/**
	 * 课程分类树
	 */
	public static final String  CATALOG_TREE = "catalogTreeStr";
	
   	
	/**
	 *  Ehcache 缓存 前缀 
	 */
	public static final String  EHCACHEKEY_USERONLINE = "userOnLine_";
	
	public static final String  EHCACHEKEY_USERCOLLECTION = "uc_";
	
	public static final String  EHCACHEKEY_GRADEUSER = "gu_";
	
	public static final String  EHCACHEKEY_GRADETHESIS = "gthes_";
	
	public static final String  EHCACHEKEY_COURSE = "co_";
	
	public static final String  EHCACHEKEY_USERCOURSEVIEW = "ucv_";
	
	public static final String  EHCACHEKEY_COURSEUSER = "cu_";
}
