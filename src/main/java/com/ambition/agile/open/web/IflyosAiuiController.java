/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.open.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ambition.agile.common.web.BaseController;

/**
 * iflyos aiui web Controller
 * @author harry
 * @version 2019-08-12
 */
@Controller
@RequestMapping(value = "${frontPath}/iflyos/")
public class IflyosAiuiController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(IflyosAiuiController.class);
	
	//ApiResponse<?>
	@RequestMapping(value = "aiui2iflyosConvert")
	@ResponseBody
	public void aiui2iflyosConvert (
			String courseName, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes)  throws  IOException {

		// get request body.
		ServletInputStream inputStream = request.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = "";
		StringBuilder iflyosPostData = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			iflyosPostData.append(line);
		}

		System.out.println("DO  aiui2iflyosConvert POST " + iflyosPostData.toString());
		JSONObject iflyosData = (JSONObject) JSONObject.parse(iflyosPostData.toString());
		
		JSONObject aiui = iflyosData.getJSONObject("request").getJSONObject("aiui");
		System.out.println("iflyosdata request aiui " + iflyosData.toJSONString());
		
		JSONObject body = new JSONObject();
		JSONObject responsedata = new JSONObject();
		JSONObject outputSpeech = new JSONObject();
		body.put("version", "1.1");
		
		JSONArray directives = new JSONArray();
		JSONObject directive = new JSONObject();

		directive.put("type", "AudioPlayer.Play");
		directive.put("playBehavior", "REPLACE_ALL");//ENQUEUE
		JSONObject audioItem = new JSONObject();
		JSONObject stream = new JSONObject();
		//audioItem.put(key, value);

		
		if (aiui.containsKey("shouldEndSession")) {
			if (aiui.getBoolean("shouldEndSession")) {
				responsedata.put("shouldEndSession", true);
				responsedata.put("expectSpeech", false);
			} else {
				responsedata.put("shouldEndSession", false);
				responsedata.put("expectSpeech", true);
			}
			
			String text = aiui.getJSONObject("answer").getString("text");
			System.out.println("aiui.getJSONObject(answer).getString(text) "+text);
			outputSpeech.put("type", "PlainText");
			outputSpeech.put("text", text);
			//暂时放在这儿
			directives.add(directive);
			responsedata.put("directives", directives);
			
			responsedata.put("outputSpeech", outputSpeech);
			body.put("response", responsedata);
			
			System.out.println(" response body  = " + body.toJSONString());

			// repsonse to AIUI server
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().append(body.toString());
			
		} else {
			JSONObject intent = aiui.getJSONArray("data").getJSONObject(0).getJSONObject("intent");
			System.out.println( "get response form aiui server, data intent " + intent.toString());
			int rc = intent.getIntValue("rc");

			System.out.println( "get response form aiui server, rc = " + rc);

			if (rc == 0) {
				// 判断会话是否结束
				if (intent.containsKey("shouldEndSession") && !intent.getBoolean("shouldEndSession")) {
					responsedata.put("shouldEndSession", false);
					responsedata.put("expectSpeech", true);
				} else {
					responsedata.put("shouldEndSession", true);
					responsedata.put("expectSpeech", false);
				}
				
				// 判断是否有answer
				if (intent.containsKey("answer")) {
					String text = intent.getJSONObject("answer").getString("text");
					System.out.println("answer-->text  "+text);
					outputSpeech.put("text", text);
				} else {
					outputSpeech.put("text", "好的，火落落收到您的请求");
				}
				
				//判断是否自定义技能 有 mp3 url 
				if(intent.containsKey("data")){
					JSONObject intentData = intent.getJSONObject("data");
					String service = intent.getString("service");
					System.out.println("######intentData %%%%%:" +intentData.toString()+" #####service %%%%"+ service);
					if(intentData.containsKey("result") && null != service
							&& service.equals("OS7596127858.2huanglispeak")){
						JSONArray intentResultArray = intentData.getJSONArray("result");
						if(null != intentResultArray &&  intentResultArray.size()>0){
							JSONObject result = intentResultArray.getJSONObject(0);
							System.out.println("###OS7596127858.2huanglispeak####"+ result.toString());
						if(result.containsKey("voicePath")){
							String voicePath = result.getString("voicePath");
							//outputSpeech.put(key, value)
							stream.put("type","AUDIO");
							stream.put("url",voicePath);
					        Date d = new Date();
					        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
					        String formatDate = format.format(d);
							stream.put("token",formatDate);
							stream.put( "offsetInMilliseconds", 0);
							audioItem.put("stream",stream);
							System.out.println("########stream"+stream.toString());
						}
						}
					}
				}
				
				directive.put("audioItem", audioItem);
				System.out.println("########audioItem"+ audioItem.toString());
				directives.add(directive);
				System.out.println("########directive"+ directive.toString());
				responsedata.put("directives", directives);

				responsedata.put("outputSpeech", outputSpeech);
				body.put("response", responsedata);
				System.out.println(" response body  not shouldEndSession= " +body.toString());

				// 指令响应
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().append(body.toString());
				
			} else {
				//返回204状态码，内容为空
				response.setStatus(204);
			}
		}
		 //return ApiResponse.success(map);
	}
	
}