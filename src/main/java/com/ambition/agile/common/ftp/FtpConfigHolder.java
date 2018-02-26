package com.ambition.agile.common.ftp;

import java.util.Properties;

import com.ambition.agile.common.BaseConfigHolder;
import com.ambition.agile.common.BaseFrameworkConfig;
import com.ambition.agile.common.util.ComUtil;
import com.ambition.agile.common.utils.SpringContextHolder;
 

/**
 * ʱftp 配置工具类
 * @author hqt
 * @since 2013-06-25
 *
 */
public class FtpConfigHolder {
	
	private static BaseFrameworkConfig config ;
	
	//ftp 
	private static final String FTP_FLAG_FALSE = "0";
	private static final int FTP_PORT = 21;
	private static final String FTP_IP = "192.168.2.238";
	private static final String FTP_ADDRESS = "http://192.168.2.238:1899/";
	private static final String FTP_USERNAME = "gclc";
	private static final String FTP_PASSWORD = "gclc";
	
	/*private static final String FTP_FILE_FLAG = "ftp.file.flag";
	private static final String FTP_FILE_PORT = "ftp.file.port";
	private static final String FTP_FILE_IP = "ftp.file.ip";
	private static final String FTP_FILE_ADDRESS = "ftp.file.address";
	private static final String FTP_FILE_USERNAME = "ftp.file.username";
	private static final String FTP_FILE_PASSWORD = "ftp.file.password";*/
	
	
	private static final String FTP_FILE_FLAG = "1";
	private static final String FTP_FILE_PORT = "21";
	private static final String FTP_FILE_IP = "192.168.2.238";
	private static final String FTP_FILE_ADDRESS = "http://192.168.2.238:1899/";
	private static final String FTP_FILE_USERNAME = "gclc";
	private static final String FTP_FILE_PASSWORD = "gclc";
	
	
	private static final String FTP_VIDEO_FLAG = "ftp.video.flag";
	private static final String FTP_VIDEO_DEL_FLAG = "ftp.video.del.flag";
	private static final String FTP_VIDEO_PORT = "ftp.video.port";
	private static final String FTP_VIDEO_IP = "ftp.video.ip";
	private static final String FTP_VIDEO_ADDRESS = "ftp.video.address";
	private static final String FTP_VIDEO_USERNAME = "ftp.video.username";
	private static final String FTP_VIDEO_PASSWORD = "ftp.video.password";
	
	
	public static Properties getProperties(){
		if(config == null)
			config = (BaseFrameworkConfig)SpringContextHolder.getBean(BaseConfigHolder.CONFIG_FILE_NAME);
		return config.getConfig();
	}
	
	public static boolean getFrameworkConfigReload(){
		
		String reload = getProperties().getProperty(BaseConfigHolder.RELOAD);
		if(reload == null)
			return false;
		
		return Boolean.parseBoolean(reload);
	}
	
	private static void ObjectNull(){
		if(getFrameworkConfigReload()){
			config = null;
		}
	}	
	
	/**
	 * 
	 * @author hqt
	 * @since 10:58:10 AM
	 * @see  
	 */
	public static boolean getFtpFileFlag(){
		
		String ftpFileFlag = getProperties().getProperty(FTP_FILE_FLAG);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpFileFlag)){
			ftpFileFlag = FTP_FLAG_FALSE; 
		}
		return ftpFileFlag.equals(FTP_FLAG_FALSE)?false:true;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:36:20 PM
	 * @see ��ȡ ftp ip ���� ����
	 */
	public static String getFtpFileIp(){
		
		String ftpFileIp = getProperties().getProperty(FTP_FILE_IP);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpFileIp)){
			return FTP_IP;
		}
		return ftpFileIp;
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:09 PM
	 * @see ��ȡftp�˿� 
	 */
	public static int getFtpFilePort(){
		
		String ftpFilePort = getProperties().getProperty(FTP_FILE_PORT);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpFilePort)){
			return FTP_PORT;
		}
		int port = FTP_PORT;
		try {
			port =Integer.parseInt(ftpFilePort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return port;
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:27 PM
	 * @see ��ȡftp ���ص�ַ
	 */
	public static String getFtpFileAddress(){
		
		String ftpFileAddress = getProperties().getProperty(FTP_FILE_ADDRESS);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpFileAddress)){
			return FTP_ADDRESS;
		}
		return ftpFileAddress;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:27 PM
	 * @see ��ȡftp �û���
	 */
	public static String getFtpFileUserName(){
		
		String ftpFileUserName = getProperties().getProperty(FTP_FILE_USERNAME);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpFileUserName)){
			return FTP_USERNAME;
		}
		return ftpFileUserName;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:27 PM
	 * @see ��ȡftp ����
	 */
	public static String getFtpFilePassWord(){
		
		String ftpFilePassword = getProperties().getProperty(FTP_FILE_PASSWORD);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpFilePassword)){
			return FTP_PASSWORD;
		}
		return ftpFilePassword;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 10:58:10 AM
	 * @see
	 */
	public static boolean getFtpVideoFlag(){
		
		String ftpVideoFlag = getProperties().getProperty(FTP_VIDEO_FLAG);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoFlag)){
			ftpVideoFlag = FTP_FLAG_FALSE; 
		}
		return ftpVideoFlag.equals(FTP_FLAG_FALSE)?false:true;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 10:58:10 AM
	 * @see
	 */
	public static boolean getFtpVideoDelFlag(){
		
		String ftpVideoFlag = getProperties().getProperty(FTP_VIDEO_DEL_FLAG);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoFlag)){
			ftpVideoFlag = FTP_FLAG_FALSE; 
		}
		return ftpVideoFlag.equals(FTP_FLAG_FALSE)?false:true;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:36:20 PM
	 * @see ��ȡ ftp ip ���� ����
	 */
	public static String getFtpVideoIp(){
		
		String ftpVideoIp = getProperties().getProperty(FTP_VIDEO_IP);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoIp)){
			return FTP_IP;
		}
		return ftpVideoIp;
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:09 PM
	 * @see ��ȡftp�˿� 
	 */
	public static int getFtpVideoPort(){
		
		String ftpVideoPort = getProperties().getProperty(FTP_VIDEO_PORT);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoPort)){
			return FTP_PORT;
		}
		int port = FTP_PORT;
		try {
			port =Integer.parseInt(ftpVideoPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return port;
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:27 PM
	 * @see 
	 */
	public static String getFtpVideoAddress(){
		
		String ftpVideoAddress = getProperties().getProperty(FTP_VIDEO_ADDRESS);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoAddress)){
			return FTP_ADDRESS;
		}
		return ftpVideoAddress;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:27 PM
	 * @see ��ȡftp �û���
	 */
	public static String getFtpVideoUserName(){
		
		String ftpVideoUserName = getProperties().getProperty(FTP_VIDEO_USERNAME);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoUserName)){
			return FTP_USERNAME;
		}
		return ftpVideoUserName;
		
	}
	
	/**
	 * 
	 * @author hqt
	 * @since 4:32:27 PM
	 * @see ��ȡftp ����
	 */
	public static String getFtpVideoPassWord(){
		
		String ftpVideoPassword = getProperties().getProperty(FTP_VIDEO_PASSWORD);
		ObjectNull();
		if(ComUtil.isNullOrEmpty(ftpVideoPassword)){
			return FTP_PASSWORD;
		}
		return ftpVideoPassword;
		
	}
	public static void main(String[] args) {
		System.out.println(FtpConfigHolder.FTP_ADDRESS);
		System.out.println(FtpConfigHolder.getFtpFileFlag());
	}
	
}
