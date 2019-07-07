/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.common.upload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.aliyun.oss.OSSUploadUtil;
import com.ambition.agile.common.util.ComUtil;
import com.ambition.agile.common.util.PropertiesFactory;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;

/**
 * 上传公共Controller
 * @author harry
 * @version 2018-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/upload")
public class OssUploadController extends BaseController {
	
	@RequestMapping(value = "upload")
	@ResponseBody
	public ApiResponse<?> upload(@RequestParam(value = "file", required = false) MultipartFile courseFile,String dir,String oldUrl,RedirectAttributes redirectAttributes) {
		 Map<String,String> map=new HashMap<String,String>();
		 System.out.println("#a$:"+dir);
		 if(dir==null || dir.equals("") | dir.equals("undefined") ){
			 dir = "course";
		 }
		 System.out.println("#a$:"+dir);
		 String mainPath = dir;
		 if(dir!=null&&dir!=""){
              mainPath=dir;			 
		 }
		 
		 String fileType=null;
		 try {
			if(courseFile !=null){
				
				// 获取文件类型
				fileType = courseFile.getOriginalFilename();
				String type = fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				String ossUrl=null;
				if(ComUtil.isNullOrEmpty(oldUrl)){
					ossUrl=OSSUploadUtil.uploadFileNewName(courseFile.getInputStream(),type, dir);
				}else{
					String status=PropertiesFactory.getProperty("ossConfig.properties", "outernet.intranet");
					String resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
					if(Integer.valueOf(status)==1){//有外网的服务器
						resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
					}else{//没有外网的服务器
						resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.file.replace");
					}
					System.out.println(type+"c:" +resource+"a:"+oldUrl+ "b:"+dir);
					ossUrl=OSSUploadUtil.replaceFile(courseFile.getInputStream(), type, resource+oldUrl, dir);
				}
				//try catch 不判空
				if(ossUrl==null){
					return ApiResponse.fail(404,"上传失败");
				}
				/*int index=ossUrl.indexOf("/");
				index=ossUrl.indexOf(".", index+2);
		        mainPath=ossUrl.substring(index,ossUrl.length()-1);*/
		        mainPath=ossUrl.substring(ossUrl.lastIndexOf("/",ossUrl.lastIndexOf("/")-2), ossUrl.length());
				/*String[] paths = ossUrl.split("/");
				mainPath= "/" + paths[paths.length - 2] + "/" + paths[paths.length - 1];*/
				map.put("fileName",fileType);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponse.fail(404,"上传失败");
		} 
		 
		 map.put("code","200");
		 mainPath = "http:/"+ mainPath;
		 map.put("fileUrl",mainPath);
		 System.out.println("上传信息：文件类型"+fileType+"，文件路径fileUrl"+mainPath);
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
	public ApiResponse<?> uploadNotice(@RequestParam(value = "file", required = false) MultipartFile noticefile,String dir,String oldUrl,RedirectAttributes redirectAttributes) {
		
		 Map<String,String> map=new HashMap<String,String>();
		 String mainPath = dir;
		 if(dir!=null&&dir!=""){
              mainPath=dir;			 
		 }
		 String fileType=null;
		 try {
			if(noticefile !=null){
				
				// 获取文件类型
				fileType = noticefile.getOriginalFilename();
				String type = fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				String ossUrl=null;
				if(ComUtil.isNullOrEmpty(oldUrl)){
					ossUrl=OSSUploadUtil.uploadFileNewName(noticefile.getInputStream(),type, dir);
				}else{
					String status=PropertiesFactory.getProperty("ossConfig.properties", "outernet.intranet");
					String resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
					if(Integer.valueOf(status)==1){//有外网的服务器
						resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
					}else{//没有外网的服务器
						resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.file.replace");
					}
					ossUrl=OSSUploadUtil.replaceFile(noticefile.getInputStream(), type, resource+oldUrl, dir);
				}
				//try catch 不判空
				if(ossUrl==null){
					return ApiResponse.fail(404,"上传失败");
				}
				/*int index=ossUrl.indexOf("/");
				index=ossUrl.indexOf(".", index+2);
		        mainPath=ossUrl.substring(index,ossUrl.length()-1);*/
		        mainPath=ossUrl.substring(ossUrl.lastIndexOf("/",ossUrl.lastIndexOf("/")-2), ossUrl.length());
				/*String[] paths = ossUrl.split("/");
				mainPath= "/" + paths[paths.length - 2] + "/" + paths[paths.length - 1];*/
				map.put("fileName",fileType);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return ApiResponse.fail(404,"上传失败");
		} 
		 
		 map.put("code","200");
		 map.put("fileUrl",mainPath);
		 System.out.println("上传信息：文件类型"+fileType+"，文件路径fileUrl"+mainPath);
		 return ApiResponse.success(map); 
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public boolean delete(String fileUrl,RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
			String status=PropertiesFactory.getProperty("ossConfig.properties", "outernet.intranet");
			String resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
			if(Integer.valueOf(status)==1){//有外网的服务器
				resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
			}else{//没有外网的服务器
				resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.file.replace");
			}
			 if(StringUtils.isNotEmpty(fileUrl)){
				 flag=OSSUploadUtil.deleteFile(resource+fileUrl);	 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag; 
		 
	}
}