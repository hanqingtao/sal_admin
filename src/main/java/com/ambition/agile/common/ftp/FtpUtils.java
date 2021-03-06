package com.ambition.agile.common.ftp;

import java.io.FileInputStream;
import java.io.InputStream;

import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;

 


/**
 * @author hqt
 * @since 20133:20:56 PM
 * @see ftp 工具类 
 */
public class FtpUtils {
	
	public static boolean uploadFile(String mainPath,String fileName,FileInputStream fileInput){
		
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
	
	public static boolean uploadFile(String mainPath,String fileName,InputStream inputStream){
		boolean flag = false;
		try{
			FtpApacheUtil ftpApache = new FtpApacheUtil();
			ftpApache.setClient();
			ftpApache.login();
			ftpApache.createDir(mainPath);
			flag = ftpApache.uploadFile(mainPath, fileName, inputStream);
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
	 * @Description: 根据上传的文件名获取存入FTP服务的文件名称
	 * @param uploadFileName
	 * @return    
	 * @throws: 
	 * @author: yzh
	 * @date: 2013-12-9 下午03:53:55
	 */
	public static String getUploadFileNameToFtpServer(String markName, String uploadFileName){
		return markName + uploadFileName.substring(uploadFileName.lastIndexOf("."));
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
	 * @Description: 
	 * @return    
	 * @throws: 
	 * @author: yzh
	 * @date: 2013-12-10 下午12:07:17
	 */
	public static String getUploadFileNameMark(){
		return System.currentTimeMillis() + "";
	}

	public static State uploadUeditorFile(String mainPath, String physicalPath, InputStream is) {
		State state = null;
		try{
			FtpApacheUtil ftpApache = new FtpApacheUtil();
			ftpApache.setClient();
			ftpApache.login();
			ftpApache.createDir(mainPath);
			boolean flag = ftpApache.uploadFile(mainPath, physicalPath, is);
		}catch(Exception e){
			e.printStackTrace();
			return new BaseState(false, 4);
		}
		state = new BaseState(true);
		return state;
	}

	/**
	 * 
	 * @Title deleteFile
	 * @Description:删除上传文件
	 * @author xrl
	 * @Date 2018年8月6日
	 * @param ftpDirAndFileName  文件在ftp配置目录下的路径
	 * @return
	 */
	public static boolean deleteFile(String ftpDirAndFileName) {
        try {
        	FtpApacheUtil ftpApache = new FtpApacheUtil();
        	ftpApache.setClient();
			ftpApache.login();
            return ftpApache.deleteFile(ftpDirAndFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
