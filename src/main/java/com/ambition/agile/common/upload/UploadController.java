/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.common.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.ftp.FtpUtils;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.common.utils.StringUtils;

/**
 * 上传公共Controller
 * @author harry
 * @version 2018-07-27
 */
//@Controller
//@RequestMapping(value = "${adminPath}/upload")
public class UploadController extends BaseController {
	
	@RequestMapping(value = "upload")
	@ResponseBody
	public ApiResponse<?> upload(@RequestParam(value = "file", required = false) MultipartFile adjustfile,String dir,RedirectAttributes redirectAttributes) {
		
		
		
		 Map<String,String> map=new HashMap<String,String>();
		 String mainPath = dir;
		 if(dir!=null&&dir!=""){
              mainPath=dir;			 
		 }
		 String newfileName = null;
		 String fileType=null;
		 String oldFileName=null; 
		 try {
			if(adjustfile !=null){
				
				// 获取文件类型
				fileType = adjustfile.getOriginalFilename();
				
				oldFileName= fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				
				newfileName = System.currentTimeMillis()+"."+oldFileName;
				
				
				
				FtpUtils.uploadFile(mainPath, newfileName, adjustfile.getInputStream());
				map.put("fileName",fileType);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponse.fail(404,"上传失败");
		} 
		 
		 map.put("code","200");
		 map.put("fileUrl",mainPath+"/"+newfileName);
		 System.out.println("上传信息：文件类型"+fileType+"，文件路径fileUrl"+mainPath+"/"+newfileName);
		 return ApiResponse.success(map); 
	}
	
	
	/**
	 * 公告上传文件
	 * @param adjustfile
	 * @param adjust
	 * @param dir
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "uploadNotice")
	@ResponseBody
	public ApiResponse<?> uploadNotice(@RequestParam(value = "file", required = false) MultipartFile noticefile,String dir,RedirectAttributes redirectAttributes) {
		
		 Map<String,String> map=new HashMap<String,String>();
		 String mainPath = dir;
		 if(dir!=null&&dir!=""){
              mainPath=dir;			 
		 }
		 String newfileName = null;
		 String fileType=null;
		 String oldFileName=null; 
		 try {
			if(noticefile !=null){
				
				// 获取文件类型
				fileType = noticefile.getOriginalFilename();
				oldFileName= fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				newfileName = System.currentTimeMillis()+"."+oldFileName;
				
				FtpUtils.uploadFile(mainPath, newfileName, noticefile.getInputStream());
				map.put("fileName",fileType);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return ApiResponse.fail(404,"上传失败");
		} 
		 
		 map.put("code","200");
		 map.put("fileUrl",mainPath+"/"+newfileName);
		 System.out.println("上传信息：文件类型"+fileType+"，文件路径fileUrl"+mainPath+"/"+newfileName);
		 return ApiResponse.success(map); 
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public boolean delete(String fileUrl,RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
			 if(StringUtils.isNotEmpty(fileUrl)){
				 flag=FtpUtils.deleteFile(fileUrl);	 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag; 
		 
	}
}