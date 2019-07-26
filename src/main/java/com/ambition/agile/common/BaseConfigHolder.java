package com.ambition.agile.common;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.common.util.ComUtil;
import com.iflytek.util.WebaiuiUtil;



/**
 * 
 * @Description:
 * @author: lxh
 * @date: 2014-8-14 下午05:33:08
 */
public class BaseConfigHolder {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseConfigHolder.class);
	
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

	//ai 应用的相关参数
	public static String aiuiWebApiUrl;//AI_APP_VHOSTURL;  // 应用的 ai.app.vhostUrl
	public static String aiAppAppid;//AI_APP_APPID; // 应用的 ai.app.appid
	public static String aiAppAppkey;//AI_APP_APIKEY; // 应用的 ai.app.apiKey
	public static String aiAppApisecret;//AI_APP_APISECRET ; // 应用的 ai.app.apiSecret
	public static String aiAppAuthid;//AiAppAuthid 应用的测试 authId
	//web tts 
	public static String aiuiWebApiUrlTts;
	public static String aiuiAppApikeyTts;

	public static String wxAppId;//小程序的appid 
	public static String wxSecret;//小程序的 secret
	public static String wxGrantType;//加密类型
	public static String wxRequestUrl;//微信小程序后台请求接口地址
	
	public static String videoFFmpegFile;//ffmpeg 的工作路径
	
	public static String videoDialogPath;//video.dialog.path=
	public static String videoDialogName;//video.dialog.name=dialog
	public static String videoDialogTtsName;//voice  tts 
	
	public static String flvServer;
	public static String scormServer;
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
	
	public static void  intiSystemValues() {
		logger.info("@@@@@@ intiSystemValues %%%%%%");
		getAppName();
		getAppKeyWord();
		getAppDescription();
		getCacheControlContent();
		getCacheControlMaxAge();
		getImgServer();
		getSingleRedis();
		getClusterRedis();
		//aiui webapi url 
		getAiuiWebApiUrl();
		//aiui 应用 appid
		getAiAppAppid();
		//aiui 应用 apikey
		getAiAppApikey();
		//aiui 应用的调试 authId
		getAiAppAuthid();
		
		getAiuiWebApiUrlTts();
		
		
		//getAiAppApisecret();
		
		getWxAppID();
		getWxSecret();
		getWxGrantType();
		getWxRequestUrl();
		
		getVideoFFmpegFile();
		getVideoDialogPath();
		getVideoDialogName();
		getVideoDialogTtsName();
		logger.info("@@@@@@ intiSystemValues  end %%%%%%");
//		getFlvServer();
//		getScormServer();
//		getIsMonitor();
//		getMonitorTime();
//		getHeartStart();
//		getHeartTime();
//		getIsMail();
//		getMailTo();
//		getMailFrom();
//		getLockCount();
//		getLockTimeOut();
//		getKey();
//		getServerDomain();
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

	/** 
	 * @see 应用的 ai.app.vhostUrl
	 * @return
	 */
	public static String getAiuiWebApiUrl(){
		if(aiuiWebApiUrl == null){
			aiuiWebApiUrl = new String(getProperties().getProperty("ai.aiui.webapi.url"));
		}
		if(null != aiuiWebApiUrl ){
			aiuiWebApiUrl = StringUtils.trim(aiuiWebApiUrl);
		}
		return aiuiWebApiUrl;
	}
	//ai 应用的相关参数
	/** 
	 * @see 应用的 ai.app.appid
	 * @return       
	 */
	public static String getAiAppAppid(){
		if(aiAppAppid == null){
			aiAppAppid = getProperties().getProperty("ai.aiui.appid");
		}
		if(null != aiAppAppid){
			aiAppAppid = StringUtils.trim(aiAppAppid);
		}
		System.out.println("#####BaseConfigHolder aiAppAppid"+aiAppAppid+"2222");
		return aiAppAppid;
	}
	/** 
	 * @see 应用的 ai.app.apiKey
	 * @return
	 */
	public static String getAiAppApikey(){
		if(aiAppAppkey == null){
			aiAppAppkey = new String(getProperties().getProperty("ai.aiui.apikey"));
		}
		if(null != aiAppAppkey ){
			aiAppAppkey = StringUtils.trim(aiAppAppkey);
		}
		return aiAppAppkey;
	}
	
	/** 
	 * @see 应用的 ai.app.authid
	 * @return 已使用
	 */
	public static String getAiAppAuthid(){
		if(aiAppAuthid == null){
			aiAppAuthid = new String(getProperties().getProperty("ai.aiui.authid"));
		}
		if(null != aiAppAuthid ){
			aiAppAuthid = StringUtils.trim(aiAppAuthid);
		}
		return aiAppAuthid;
	}
	/** 
	 * @see 应用的 ai.app.apiSecret
	 * @return 暂时未用
	 */
	public static String getAiAppApisecret(){
		if(aiAppApisecret == null){
			aiAppApisecret = new String(getProperties().getProperty("ai.app.apiSecret"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return aiAppApisecret;
	}
	
	/** 
	 * @see 应用的 ai. app. tts 
	 * @return 
	 */
	public static String getAiuiWebApiUrlTts(){
		if(aiuiWebApiUrlTts == null && getProperties()!= null){
			aiuiWebApiUrlTts = new String(getProperties().getProperty("ai.aiui.webapi.ttsurl"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return aiuiWebApiUrlTts;
	}
	/** 
	 * @see 应用的 ai. app. tts 
	 * @return 
	 */
	public static String getAiuiAppApikeyTts(){
		if(aiuiAppApikeyTts == null && getProperties()!= null){
			aiuiAppApikeyTts = new String(getProperties().getProperty("ai.aiui.tts.apikey"));
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return aiuiAppApikeyTts;
	}
	/**
	 * @see 微信相关appi secret 
	 * @author harry
	 */
	public static String getWxAppID(){
		if(wxAppId == null){
			wxAppId = new String(getProperties().getProperty("wx.appid"));
		}
		return wxAppId;
	}
	/**  
	 * @see 微信相关 secret 
	 * @return
	 */
	public static String getWxSecret(){
		if(wxSecret == null){
			wxSecret = new String(getProperties().getProperty("wx.secret"));
		}
		return wxSecret;
	}
	/**
	 * @see 微信认证 类型 
	 * @return
	 */
	public static String getWxGrantType(){
		if(wxGrantType== null ){
			wxGrantType = new String(getProperties().getProperty("wx.grant_type"));
		}
		return wxGrantType;
	}
	/**
	 * @see 微信认证 类型 
	 * @return
	 */
	public static String getWxRequestUrl(){
		if(wxRequestUrl== null ){
			wxRequestUrl = new String(getProperties().getProperty("wx.requestUrl"));
		}
		return wxRequestUrl;
	}
	
	/**
	 * @see 微信认证 类型 
	 * @return
	 */
	public static String getVideoFFmpegFile(){
		if(videoFFmpegFile== null ){
			videoFFmpegFile = new String(getProperties().getProperty("video.ffmpegFile"));
		}
		return videoFFmpegFile;
		
	}
	
	
	/**
	 * @see 音频文件的服务器路径 
	 * @return
	 */
	public static String getVideoDialogPath(){
		if(videoDialogPath == null){
			videoDialogPath = new String(getProperties().getProperty("video.dialog.path"));
		}
		if(null != videoDialogPath ){
			videoDialogPath = StringUtils.trim(videoDialogPath);
		}
		return videoDialogPath;
	}
	
	/**
	 * @see 音频文件的目录
	 * @return
	 */
	public static String getVideoDialogName(){
		if(videoDialogName == null){
			videoDialogName = new String(getProperties().getProperty("video.dialog.name"));
		}
		if(null != videoDialogName ){
			videoDialogName = StringUtils.trim(videoDialogName);
		}
		return videoDialogName;
	}
	
	/**
	 * @see 音频文件的tts 目录
	 * @return
	 */
	public static String getVideoDialogTtsName(){
		if(videoDialogTtsName == null && getProperties() != null){
			videoDialogTtsName = new String(getProperties().getProperty("video.dialog.tts.name"));
		}
		if(null != videoDialogTtsName ){
			videoDialogTtsName = StringUtils.trim(videoDialogTtsName);
		}
		return videoDialogTtsName;
	}
	
	
	public static void main(String[] args) {
		System.out.println("abc");
	}
}
