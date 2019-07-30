/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.open.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.BaseFrameworkConfig;
import com.ambition.agile.common.aliyun.oss.OSSUploadUtil;
import com.ambition.agile.common.util.PropertiesFactory;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.service.CourseService;

/**
 * aiui web Controller
 * @author harry
 * @version 2019-07-14
 */
@Controller
@RequestMapping(value = "${frontPath}/aiui/")
public class AiuiWebController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(AiuiWebController.class);
	
	@Autowired
	private BaseFrameworkConfig config;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "getVoiceByName")
	@ResponseBody
	public ApiResponse<?> audioUpload(
			String courseName, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		System.out.println("##############courseName"+courseName);
		if(StringUtils.isEmpty(courseName)){
			courseName = request.getParameter("courseName");
			System.out.println("##############courseName"+request.getParameter("courseName"));
		}
		try{
			courseName = java.net.URLDecoder.decode(courseName, "UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		logger.info(" voicenname {}",courseName);
		
		Map<String,String> map=new HashMap<String,String>();
		 //二级目录存放在 openId 每个用户自己的目录下 老鼠偷瓜
		 if(null == courseName &&  StringUtils.isEmpty(courseName)){
			 courseName = ""; 
		 }
		 Course courseQuery = new Course();
		 courseQuery.setName(courseName);
		 //设置本地路径
		 List<Course> courseList = courseService.getByName(courseQuery);
		 if(null != courseList && courseList.size()>0){
			 Course course = courseList.get(0);
			 if(null != course && StringUtils.isNotEmpty(course.getId())){
				if(StringUtils.isNotEmpty(course.getName())){
					map.put("courseName", course.getName());
				}
				if(StringUtils.isNotEmpty(course.getVideoPath())){
					map.put("voicePath", course.getVideoPath());
				}
				String duration = "";
				if(StringUtils.isNotEmpty(course.getDuration())){
					duration = course.getDuration();
				}
				map.put("duration", duration);
				String durationLong = "0";
				if(null != course.getDurationLong() && course.getDurationLong()>0){
					durationLong = course.getDurationLong()+"";
				}
				map.put("durationLong", durationLong);
			 }
		 }
		 //map.put("answerVoice","abc.mp3");
		 return ApiResponse.success(map);
	}
	
	 /**
     * 将MultipartFile转化为file并保存到服务器上的某地
     */
    public void SaveFileFromInputStream(MultipartFile  inputFile,String outPutFile) throws IOException
    {      
    		//接收文件，并保存 
		InputStream fileInput = inputFile.getInputStream(); 
		//实例化对象fileSave
		OutputStream fileOutput = new FileOutputStream(outPutFile); 
		//建立数组buf
		byte[] buffer =new byte[1024*1024]; 
		int len = 0; 
		//判断是否读到文件末尾
		while((len=fileInput.read(buffer)) != -1) 
		{
			fileOutput.write(buffer, 0, len); 
			fileOutput.flush(); 
		} 
		fileOutput.close();
		fileInput.close(); 
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