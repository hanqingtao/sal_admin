package com.ambition.agile.common;

import java.util.Properties;

import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.common.util.ComUtil;



/**
 * 
 * @Description:
 * @author: lxh
 * @date: 2014-8-14 下午05:33:08
 */
public class BaseConfigHolder {
	
	private static BaseFrameworkConfig config ;
	public static String CONFIG_FILE_NAME = "systemGlobalsConfig";
	public static String RELOAD = "base.config.reload";
	public static String systemName;
	public static String systemkeywords;
	public static String systemdescription;
	public static String cacheControlContent;
	public static String cacheControlMaxAge;
	public static String imgServer;
	public static boolean singleRedis;
	public static boolean clusterRedis;
	public static String flvServer;
	public static String scormServer;
	public static String gradeSchooleWorkPath="/org";  // 存放班级论文、学员论文附件路径
	public static String subjectUnitPath="/org"; // 存放机构主体图片
	public static String qualificationPath="/org"; // 存放机构资格证书图片
	public static String legalRepresentativePath ; // 存放公司法人图片
	
	public static boolean isMonitor;
	public static String monitorTime;
	
	public static boolean heartStart;
	public static Integer heartTime;
	public static boolean isMail;
	public static String mailFrom;
	public static String[] mailTo;
	
	public static Integer lockCount;  // 登录失败多少次将被锁住
	public static Integer lockTimeOut;  // 在多长时间内登录失败超过限制将被锁住（单位：小时）
	
	public static String key;
	public static String baseDomain;
	
	public static Integer redisMaxAge;
	
	public static Integer cotinueSeconds;
	
	public static Integer halfHour;
	
	public static void intiSystemValues(){
		getAppName();
		getAppKeyWord();
		getAppDescription();
		getCacheControlContent();
		getCacheControlMaxAge();
		getImgServer();
		getSingleRedis();
		getClusterRedis();
		getGradeSchooleWorkPath();
		getQualificationPath();
		getSubjectUnitPath();
		getGradeSchooleWorkPath();
		getFlvServer();
		getScormServer();
		getIsMonitor();
		getMonitorTime();
		getHeartStart();
		getHeartTime();
		getIsMail();
		getMailTo();
		getMailFrom();
		getLockCount();
		getLockTimeOut();
		getKey();
		getServerDomain();
	}
	
	public static Properties getProperties(){
		if(config == null)
			config = (BaseFrameworkConfig)SpringContextHolder.getBean(CONFIG_FILE_NAME);
		return config.getConfig();
	}
	
	public static boolean getFrameworkConfigReload(){
		
		String reload = getProperties().getProperty(RELOAD);
		if(reload == null)
			return false;
		
		return Boolean.parseBoolean(reload);
	}
	 
	public static String getAppName(){
		if(systemName == null){
				systemName = new String(getProperties().getProperty("app.name"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return systemName;
	}
	
	public static String getAppKeyWord(){
		if(systemkeywords == null){
				systemkeywords = new String(getProperties().getProperty("app.keywords"));
		}
		return systemkeywords;
	}
	
	public static String getAppDescription(){
		if(systemdescription == null){
				systemdescription = new String(getProperties().getProperty("app.description"));
		}
		return systemdescription;
	}
	
	public static String getCacheControlContent() {
		if(cacheControlContent == null){
			cacheControlContent = getProperties().getProperty(Constants.CACHE_CONTROL_CONTENT);
			return cacheControlContent;
		}
		return cacheControlContent;
	}
	
	public static String getCacheControlMaxAge() {
		if(cacheControlMaxAge == null){
			cacheControlMaxAge = getProperties().getProperty(Constants.CACHE_CONTROL_MAX_AGE);
			return cacheControlMaxAge;
		}
		return cacheControlMaxAge;
	}
	
	public static String getImgServer() {
		if(imgServer == null){
			imgServer = getProperties().getProperty("img.server");
			return imgServer==null?imgServer:imgServer.trim();
		}
		return imgServer;
	}
	

	/**
	 * @Description: redis服务 单个
	 * @author: lxh
	 * @date:
	 */
	public static Boolean getSingleRedis() {
		singleRedis = false;
		String singleRedisFlagStr = getProperties().getProperty("redis.single.flag");
		ObjectNull();
		if(ComUtil.isNullOrEmpty(singleRedisFlagStr)){
			singleRedis = false;
		}else if("1".equals(singleRedisFlagStr.trim())){
			singleRedis = true;
		}
		return singleRedis;
	}
	

	/**
	 * @Description: redis服务 集群
	 * @author: lxh
	 * @date:
	 */
	public static Boolean getClusterRedis() {
		clusterRedis = false;
		String clusterRedisFlagStr = getProperties().getProperty("redis.cluster.flag");
		ObjectNull();
		if(ComUtil.isNullOrEmpty(clusterRedisFlagStr)){
			clusterRedis = false;
		}else if("1".equals(clusterRedisFlagStr.trim())){
			clusterRedis = true;
		}
		return clusterRedis;
	}
	
	private static void ObjectNull(){
		if(getFrameworkConfigReload()){
			config = null;
		}
	}
	
	/** 班级作业上传
	 * @return
	 */
	public static String getGradeSchooleWorkPath(){
		if(gradeSchooleWorkPath == null){
			gradeSchooleWorkPath = new String(getProperties().getProperty("base.path.grade.schoolework"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return gradeSchooleWorkPath;
	}
	/** 机构单位主体上传
	 * @return
	 */
	public static String getSubjectUnitPath(){
		/*if(subjectUnitPath == null){
			subjectUnitPath = new String(getProperties().getProperty("base.path.org.photo.subjectUnitPath"));*/
	    subjectUnitPath=""; ;;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		return subjectUnitPath;
	}
	/** 机构单位资格证书上传
	 * @return
	 */
	public static String getQualificationPath(){
		if(qualificationPath == null){
			qualificationPath = new String(getProperties().getProperty("base.path.org.photo.qualificationPath"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return qualificationPath;
	}
	
	/** 机构单位法人代表上传
	 * @return
	 */
	public static String getLegalRepresentativePath(){
		if(legalRepresentativePath == null){
			legalRepresentativePath = new String(getProperties().getProperty("base.path.org.photo.legalRepresentativePath"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return legalRepresentativePath;
	}
	
	public static String getFlvServer(){
		if(flvServer == null){
			flvServer = new String(getProperties().getProperty("base.video.flvServer"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return flvServer;
	}
	
	/**
	 *@author: hqt
	 *@see:获取 三分屏课程地址
	 *@date: 2014-8-29 下午05:01:04
	 */
	public static String getScormServer(){
		
		if(scormServer == null){
			scormServer = new String(getProperties().getProperty("base.video.scormServer"));
		}                       
		
		return scormServer;
	}
	
	/**
	 *@author: hqt
	 *@see: 配置视频播放 页面，是否需要登录？
	 *@date: 2014-8-29 下午03:25:55
	 */
	public static boolean getVideoStudyLogin(){
		
		String videoStudyLogin = getProperties().getProperty("base.course.videoStudyLogin");
		if(videoStudyLogin == null)
			return false;
		
		return Boolean.parseBoolean(videoStudyLogin);
		
	}
	
	public static Boolean getIsMonitor() {
		isMonitor = false;
		String isMonitorStr = getProperties().getProperty("base.course.isMonitor");

		if(ComUtil.isNotNullOrEmpty(isMonitorStr)){
			isMonitor=Boolean.parseBoolean(isMonitorStr);
		}
		return isMonitor;
	}
	
	public static String getMonitorTime(){
		if(monitorTime == null){
			monitorTime = new String(getProperties().getProperty("base.course.monitorTime"));
		}
		return monitorTime;
	}
	
	public static Integer getHeartTime(){
		if(heartTime == null){
			heartTime = Integer.parseInt(getProperties().getProperty("base.heartTime"));
		}
		return heartTime;
	}
	
	public static Boolean getHeartStart(){
		
		heartStart = false;
		
		String heartStartStr = getProperties().getProperty("base.heartStart");

		if(ComUtil.isNotNullOrEmpty(heartStartStr)){
			
			heartStart=Boolean.parseBoolean(heartStartStr);
		}
		
		return heartStart;
	}
	
	public static Boolean getIsMail(){
		
		isMail = false;
		
		String isMailStr = getProperties().getProperty("base.isMail");

		if(ComUtil.isNotNullOrEmpty(isMailStr)){
			
			isMail=Boolean.parseBoolean(isMailStr);
		}
		
		return isMail;
	}
	
	public static String[]  getMailTo(){
		
		String mailToStr = getProperties().getProperty("base.mailTo");
		
		if(ComUtil.isNotNullOrEmpty(mailToStr)){
			mailTo =mailToStr.split(";") ;
		}
		
		return mailTo;
	}
	
	public static String  getMailFrom(){
		
		if(ComUtil.isNullOrEmpty(mailFrom)){
			mailFrom =getProperties().getProperty("base.mailFrom");
		}
		
		return mailFrom;
	}
	//前台登录 用户名 密码 加密 key
	public static String  getKey(){
		
		if(ComUtil.isNullOrEmpty(key)){
			key =getProperties().getProperty("base.key");
		}
		
		return key;
	}
	
	public static Integer  getLockCount(){
		if(ComUtil.isNullOrEmpty(lockCount)){
			lockCount = 3; // 默认3次
			String temp = getProperties().getProperty("base.lock.lockCount");
			if(ComUtil.isNotNullOrEmpty(temp)){
				lockCount = Integer.parseInt(temp);
			}
		}
		return lockCount;
	}
	
	public static Integer  getLockTimeOut(){
		if(ComUtil.isNullOrEmpty(lockTimeOut)){
			lockTimeOut = 24; // 默认24小时
			String temp = getProperties().getProperty("base.lock.lockTimeOut");
			if(ComUtil.isNotNullOrEmpty(temp)){
				lockTimeOut = Integer.parseInt(temp);
			}
		}
		return lockTimeOut;
	}
	
	/**
	 *@author: hqt
	 *@see: 获取 服务端 cookie与 redis会话的有效时长
	 *@date: 2016-5-19 下午05:39:35
	 */
	public static Integer getRedisMaxAge(){
		
		if(ComUtil.isNullOrEmpty(redisMaxAge)){
			redisMaxAge = 30 * 60; // 默认 半小时
			String temp = getProperties().getProperty("client.redis.maxAge");
			if(ComUtil.isNotNullOrEmpty(temp)){
				redisMaxAge = Integer.parseInt(temp);
			}
		}
		return redisMaxAge;
	}
	
	/**
	 *@author: hqt
	 *@see: 获取 服务端 cookie与 redis会话的有效时长
	 *@date: 2016-5-19 下午05:39:35
	 */
	public static Integer getContinueSecods(){
		
		if(ComUtil.isNullOrEmpty(cotinueSeconds)){
			cotinueSeconds = 30 * 60; // 默认 半小时
			String temp = getProperties().getProperty("client.continue.seconds");
			if(ComUtil.isNotNullOrEmpty(temp)){
				cotinueSeconds = Integer.parseInt(temp);
			}
		}
		return cotinueSeconds;
	}
	
	
	/**
	 *@author: hqt
	 *@see: 获取 服务端 cookie与 redis会话的有效时长
	 *@date: 2016-5-19 下午05:39:35
	 */
	public static Integer getHalfHour(){
		
		if(ComUtil.isNullOrEmpty(halfHour)){
			halfHour = 30 * 60; // 默认 半小时
			String temp = getProperties().getProperty("cache.halfHour");
			if(ComUtil.isNotNullOrEmpty(temp)){
				halfHour = Integer.parseInt(temp.trim());
			}
		}
		return halfHour;
	}
	/**
	 * 获取前台服务主域名
	 * @author ckl
	 * @date 2017-05-08 11:29:01
	 * @return
	 */
	public static String getServerDomain(){
		if(baseDomain == null){
			baseDomain = new String(getProperties().getProperty("base.domain"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return baseDomain;
	}
	public static void main(String[] args) {
		System.out.println(BaseConfigHolder.subjectUnitPath);
	}
}
