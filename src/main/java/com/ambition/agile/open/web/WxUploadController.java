/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.open.web;

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
 * @version 2019-07-14
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/upload")
public class WxUploadController extends BaseController {
	
	@RequestMapping(value = "audioUpload")
	@ResponseBody
	public ApiResponse<?> audioUpload(@RequestParam(value = "audioFile", required = false) MultipartFile audioFile,
			String dir,String openId, RedirectAttributes redirectAttributes) {
		 Map<String,String> map=new HashMap<String,String>();
		 //所有的音频文件进行分类存放 一级目录为  dialog
		 if(dir==null || dir.equals("") || dir.equals("undefined") ){
			 dir = "dialog/";
		 }
		 //二级目录存放在 openId 每个用户自己的目录下
		 /*
		 if(null != openId && openId.equals("") || openId.equals("undefined") ){
			 dir = dir +  openId + "/"; 
		 }
		 */
		 
		 System.out.println("#audioUpload :"+dir);
		 String mainPath = dir;
		 if(dir!=null&&dir!=""){
              mainPath=dir;			 
		 }
		 
		 String fileType=null;
		 try {
			if(audioFile !=null){
				// 获取文件类型
				fileType = audioFile.getOriginalFilename();
				System.out.println("#####fileType:+++++"+fileType);
				map.put("fileName", fileType);
				String type = fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				String ossUrl=OSSUploadUtil.uploadFileNewName(audioFile.getInputStream(),type, dir);
				System.out.println("$$$$$$$$ audioUpload  new ossUrl:  "+ossUrl);
					//如果使用  video-robot.lianggehuangli.com 则使用以下方法进行过滤。
					/*
					String status=PropertiesFactory.getProperty("ossConfig.properties", "outernet.intranet");
					String resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
					if(Integer.valueOf(status)==1){//有外网的服务器 oss 存储
						resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.resource");
					}else{//没有外网的服务器 ftp 存储
						resource=PropertiesFactory.getProperty("ossConfig.properties", "oss.file.replace");
					}
					//mainPath=ossUrl.substring(ossUrl.lastIndexOf("/",ossUrl.lastIndexOf("/")-2), ossUrl.length());
					*/
				//try catch 不判空
				if(ossUrl==null){
					return ApiResponse.fail(404,"上传失败");
				}
				//暂时先用https//https://video-robot.oss-cn-beijing.aliyuncs.com/course248A0C83CC44443B9EA6E0246394FF53.png阿里的域名
				map.put("fileUrl",ossUrl);
				map.put("fileName",fileType);
				 System.out.println("上传信息：文件类型"+fileType+"，文件路径返回 fileUrl"+ossUrl);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponse.fail(404,"上传失败");
		} 
		 map.put("code","200");
		 //如果需要的话，可以用自己的域名进行替换。 replace 即可
		 //mainPath = "http:/"+ mainPath;
		 //map.put("fileUrl",mainPath);
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