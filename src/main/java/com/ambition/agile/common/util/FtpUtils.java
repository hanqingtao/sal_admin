package com.ambition.agile.common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ambition.agile.common.ftp.FtpApacheUtil;



/**
 * @author hqt
 * @since 20133:20:56 PM
 * @see ftp 工具类 
 */
public class FtpUtils {
	
	public static boolean uploadFile(String mainPath,String fileName,InputStream fileInput){
		
		boolean flag = false;
		try{
			FtpApacheUtil ftpApache = new FtpApacheUtil();
			ftpApache.setClient();
			ftpApache.login();
			ftpApache.createDir(mainPath);
			flag = ftpApache.uploadFile(mainPath, fileName, fileInput);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public void uploadDirectory(String mainPath,String fileName,String outPutDirectory){
		
		try{
			
			FtpApacheUtil ftpApache = new FtpApacheUtil();
			ftpApache.setClient();
			ftpApache.login();
			//String mainPath = BaseConfigHolder.getVideoPath();
			ftpApache.createDir(mainPath);
			ftpApache.uploadDirectory(mainPath,fileName,outPutDirectory);
			ftpApache.logout();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: 根据上传的文件名获取存入DB的文件名称(保留原文件名称)
	 * @param uploadFileName
	 * @return    
	 * @throws: 
	 * @author: yzh
	 * @date: 2013-12-9 下午03:55:21
	 */
	public static String getUploadFileNameToDB(String uploadFileName){
		return System.currentTimeMillis() + "_" +uploadFileName.substring(0, uploadFileName.lastIndexOf(".")) + uploadFileName.substring(uploadFileName.lastIndexOf("."));
	}
	/**
	 * 根据上传文件名获取存入DB文件名——新规则
	 * @param uploadFileName
	 * @return
	 * @author ckl
	 * @date 2015-9-18 14:17:39
	 */
	public static String getUploadFileNameToDB_new(String uploadFileName,String userRealname){
		return PYUtil.converterToSpell(userRealname) + System.currentTimeMillis()+uploadFileName.substring(uploadFileName.lastIndexOf("."));
	}
	/** 
	 * @Title: getUploadFileNameToDB_new_thesis 
	 * @Description:上传论文文件的文件名——新规则(用户名_文件名_时间戳)
	 * @param @param 用户名
	 * @param @param userRealname
	 * @param @return  
	 * @return String 
	 * @throws 
	 * @author gy
	 * @date Oct 31, 2016 1:53:46 PM 
	 */
	public static String getUploadFileNameToDB_new_thesis(String userName,String realName,String uploadFileName){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		if(realName == null)
			realName="";
		return userName+"_"+realName+"_"+uploadFileName.substring(0,uploadFileName.lastIndexOf("."))+"_" + sdf.format(new Date())+uploadFileName.substring(uploadFileName.lastIndexOf("."));
	}
	
	/**
	 * @Description: 获取上传文件的真实上传名称（****_sdfsd.txt 取sdfsd.txt）
	 * @param uploadFileName
	 * @author: yzh
	 * @date: 2014-8-24 下午12:12:07
	 */
	public static String getShowFileName(String uploadFileName){
		Integer i=uploadFileName.lastIndexOf("_");
		if(i != -1)
			return uploadFileName.substring(uploadFileName.lastIndexOf("_"));
		return uploadFileName;
	}
	
	/**
	 * @Description: 
	 * @return    
	 * @throws: 
	 * @author: yzh
	 * @date: 2013-12-10 下午12:07:17
	 */
	public static String getUploadFileNameMark(){
		return System.currentTimeMillis() + "";
	}
}
