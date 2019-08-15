/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.open.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.ApiResponse;
import com.ambition.agile.common.BaseFrameworkConfig;
import com.ambition.agile.common.aliyun.oss.OSSUploadUtil;
import com.ambition.agile.common.constant.Constants;
import com.ambition.agile.common.guava.GuavaCacheUtil;
import com.ambition.agile.common.media.VideoUtils;
import com.ambition.agile.common.util.PropertiesFactory;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.iflytek.util.WebaiuiTtsUtil;
import com.iflytek.util.WebaiuiUtil;

/**
 * 上传公共Controller
 * @author harry
 * @version 2019-07-14
 * @see http://localhost:8080/sal/wx/tts/getTtsUrlByNlp
 */ 
@Controller
@RequestMapping(value = "${frontPath}/wx/tts")
public class WxTtsController extends BaseController {
	
	@RequestMapping(value = "getTtsUrlByNlp")
	@ResponseBody
	public ApiResponse<?> getTtsUrlByNlp(String timeStamp, 
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		
		System.out.println("###########getTtsUrlByNlp@@@@@@@"+timeStamp);
		String nlp = null;
		String answerVoice =null;
		Map<String,String> map=new HashMap<String,String>();
		if(StringUtils.isNotEmpty(timeStamp)){
			 nlp = (String)GuavaCacheUtil.getIfPresent(timeStamp);
			 if(StringUtils.isNotEmpty(nlp)){
				answerVoice = WebaiuiTtsUtil.getWebTtsVoiceUrlByText(nlp);
			 }
		}
		map.put("answerVoice",answerVoice);
		long nowTime = System.currentTimeMillis();
		//add by hqt 时间戳 orderTime  timeStamp 为 以前的 timestamp 的话，谁先返回，谁播放
		map.put("timeStamp", nowTime+"");
		System.out.println(" ######## ApiResponse.success %%%%"+map.toString());
		return ApiResponse.success(map); 
	}
	
}