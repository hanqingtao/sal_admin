/**
 * Copyright &copy; 2018-2020  All rights reserved.
 */
package com.ambition.agile.open.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
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
import com.ambition.agile.common.mapper.JsonMapper;
import com.ambition.agile.common.media.VideoUtils;
import com.ambition.agile.common.util.PropertiesFactory;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.open.entity.AIUIEntity;
import com.ambition.agile.open.entity.Data;
import com.iflytek.util.WebaiuiTtsUtil;
import com.iflytek.util.WebaiuiUtil;
import com.iflytek.voicecloud.webapi_tts.FileUtil;

/**
 * 上传公共Controller
 * @author harry
 * @version 2019-07-14
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/upload")
public class WxUploadController extends BaseController {
	
	@Autowired
	private BaseFrameworkConfig config;
	
//	@Resource
//	private LocalCacheToNlp localCacheToNlp;
	
	@RequestMapping(value = "audioUpload")
	@ResponseBody
	public ApiResponse<?> audioUpload(@RequestParam(value = "audioFile", required = false) MultipartFile audioFile,
			String dir,String openId, RedirectAttributes redirectAttributes) {
		long beginTime = System.currentTimeMillis();
		System.out.println("#################### audioUpload "+beginTime);
		Map<String,String> map=new HashMap<String,String>();
		 //所有的音频文件进行分类存放 一级目录为  dialog
		 if(dir==null || dir.equals("") || dir.equals("undefined") ){
			 dir = config.getConfig().getProperty("video.dialog.name")+ VideoUtils.FILE_SEPARATOR;//"dialog"
		 }
		 //二级目录存放在 openId 每个用户自己的目录下
		 if(null != openId && !openId.equals("")  && !openId.equals("undefined") ){
			 dir = dir +  openId + VideoUtils.FILE_SEPARATOR; 
		 }
		 String ttsDir = config.getConfig().getProperty("video.dialog.tts.name")+ VideoUtils.FILE_SEPARATOR;//"dialog"
		 
		 System.out.println("######openid"+openId);
		 System.out.println("#audioUpload :"+dir);
		 //设置本地路径
		 String mainPath = config.getConfig().getProperty("video.dialog.path");//"/Users/harry/out/";//BaseConfigHolder.getVideoDialogPath();//
		 mainPath = mainPath  + dir;

	     File mainPathDir = new File(mainPath);
	     if(!mainPathDir.exists()){
	         boolean mkdirs = mainPathDir.mkdirs();
	         System.out.println("@@@@@@@@@@@@mkdirs"+mkdirs);
	     }
		 
		// 文件名称生成策略（UUID uuid = UUID.randomUUID()）
	        Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	        String formatDate = format.format(d);
	        String str = "";
	        for(int i=0 ;i <5; i++){
	            int n = (int)(Math.random()*90)+10;
	            str += n;
	        }
	        logger.info("@@@@@@str random ::",str);
	        // 获取文件的扩展名
	        String fileNameFull = audioFile.getOriginalFilename();
			String fileType = fileNameFull.substring(fileNameFull.lastIndexOf("."));
			//String fileName = fileNameFull.substring(0,fileNameFull.lastIndexOf("."));
			String PCM =".pcm";
	        // 文件名
	        String fileNameMP3 = formatDate + str+ fileType; 
	        String fileNamePCM = formatDate + str+ PCM;
	        //相对路径
	        //String relaPath = mainPath + fileName;
	        String outPutFileMP3 = mainPath + fileNameMP3;
	        String outPutFilePCM = mainPath + fileNamePCM;
		 try {
			if(audioFile !=null){
				// 获取文件类型
				fileType = audioFile.getOriginalFilename();
				//map.put("fileName", fileType);
				String type = fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				//原来的思路是上传oss ,在 oss 上进行文件转换，但oss 不支持 mp3 转成pcm 只有服务器端进行存储，然后调用转换接口。
				//dir = "dialog";
				//String ossUrl=OSSUploadUtil.uploadFileNewName(audioFile.getInputStream(),type, dir);
				
				SaveFileFromInputStream(audioFile,outPutFileMP3);
				long saveFileTime = System.currentTimeMillis();
				System.out.println("#################### audioUpload  saveFileTime "+(saveFileTime-beginTime)  + "##" +(saveFileTime-beginTime)/1000);
				final VideoUtils v = new VideoUtils(outPutFileMP3,outPutFilePCM,null);
				v.convert();
				long convertPcmTime = System.currentTimeMillis();
				System.out.println("#################### audioUpload  convertPcmTime "+(convertPcmTime -saveFileTime)  + "##" +(convertPcmTime -saveFileTime)/1000);
				
				System.out.println("$$$$$$$$ audioUpload  convert new ossUrl:  "+outPutFileMP3);
				System.out.println("$$$$$$$$ audioUpload convert new outPath:  "+outPutFilePCM);
				String iat = "";
				String nlp = "";
				Map mapAIUI = WebaiuiUtil.aiuiWebApiDealFile(outPutFilePCM);
				long getAiuiMapTime = System.currentTimeMillis();
				System.out.println("#################### audioUpload  getAiuiMapTime "+(getAiuiMapTime -convertPcmTime)  + "##" +(getAiuiMapTime -convertPcmTime)/1000);
				
				System.out.println("########result wxupload file :"+ mapAIUI);
				if(null != mapAIUI && !mapAIUI.isEmpty()){
					
					String answerType = (String)mapAIUI.get("answerType");
					logger.info("####### answerType {} ",answerType);
					System.out.println(" #######result answerType  before ::"+answerType);
					if(StringUtils.isEmpty(answerType)){
						answerType = "1";
					}
					map.put("answerType", answerType);
					
					iat = (String)mapAIUI.get("iat");
					if(StringUtils.isNotEmpty(iat)){
						map.put("quesion", iat);
					}
					nlp = (String)mapAIUI.get("nlp");
					logger.info("#######nlp {} ",nlp);
					if(StringUtils.isEmpty(nlp)){
						//如果没有返回 nlp
						nlp = "对不起，请您再说一遍.";
					}
					//当对话类型为1 时，才返回answerVoice
					if(StringUtils.isNotEmpty(nlp) && answerType.equals("1")){
						//如果当前 answerType 为 1 时，
						
						if(nlp.length()<=Constants.ANSWER_TYPE_LENGTH){
							//小于 70字时， 耗时 2秒一次性显示
							map.put("answer", nlp);
							map.put("answerOneFlag", Constants.ANSWER_TYPE_ONE_ONE);//1
							String answerVoice = WebaiuiTtsUtil.getWebTtsVoiceUrlByText(nlp);// WebTtsUtil.getWebTtsVoiceUrlByText(nlp);
							map.put("answerVoice",answerVoice);
							logger.info("nlp,answerVoice {},{}",nlp,answerVoice);
						}else{
							//如果 nlp 大于70字时， 3秒以上的耗时，就进行两次显示.
							map.put("answer", nlp);
							map.put("answerOneFlag", Constants.ANSWER_TYPE_ONE_TWO);//2
							map.put("answerVoice",null);
							//调用缓存，以程序执行时间开始时，为key 将nlp 暂存.
							GuavaCacheUtil.put(beginTime+"",nlp);
							System.out.println("GuavaCacheUtil get "+"key:"+beginTime+"value:"+GuavaCacheUtil.get(beginTime+""));
						}
					}
					long getNlpTime = System.currentTimeMillis();
					System.out.println("#################### audioUpload  getNlpTime "+(getNlpTime -getAiuiMapTime)  + "##" +(getNlpTime-getAiuiMapTime)/1000);
					
					//返回 第二种问答的  前部分音频 地址
					String reply = (String)mapAIUI.get("reply");
					if(StringUtils.isEmpty(reply)){
						reply = "对不起，请您再说一遍.";
					}
					if(StringUtils.isNotEmpty(reply) && answerType.equals("2") ){
						System.out.println("replay content "+ reply);
						map.put("reply", reply);
						String preVidePath =  WebaiuiTtsUtil.getWebTtsVoiceUrlByText(reply);//WebTtsUtil.getWebTtsVoiceUrlByText(reply);
						System.out.println("answerType 2 reply  url  "+ preVidePath);
						map.put("preVidePath", preVidePath);
					}
					long getReplyTime = System.currentTimeMillis();
					System.out.println("#################### audioUpload  getReplyTime "+(getReplyTime- getNlpTime)  + "##" +(getReplyTime- getNlpTime)/1000);
					
					String courseName = (String)mapAIUI.get("courseName");
					logger.info("#######result courseName  {} ",courseName);
					if(StringUtils.isNotEmpty(courseName)){
						map.put("courseName", courseName);
					}
					String author = (String)mapAIUI.get("author");
					logger.info("#######result author  {} ",author);
					if(StringUtils.isNotEmpty(author)){
						map.put("author", author);
					}
					
					//answerType  2 
					String voicePath  = (String)mapAIUI.get("voicePath");
					if(StringUtils.isNotEmpty(voicePath)){
						map.put("voicePath", voicePath);
					}
					String couseName = (String)mapAIUI.get("couseName");
					if(StringUtils.isNotEmpty(couseName)){
						map.put("couseName", couseName);
					}
					System.out.println("#######result answerType  after  ::"+answerType);
					String duration = (String)mapAIUI.get("duration");
					System.out.println("#######duration {} "+duration);
					if(StringUtils.isEmpty(duration)){
						duration = "00:00";
					}
					map.put("duration", duration);
					
					String durationLong  = (String)mapAIUI.get("durationLong");
					if(StringUtils.isEmpty(durationLong)){
						durationLong = "0";
					}
					map.put("durationLong", durationLong);
					
					//try catch 不判空
					if(outPutFilePCM==null){
						return ApiResponse.fail(404,"上传失败");
					}
					//暂时先用https//https://video-robot.oss-cn-beijing.aliyuncs.com/course248A0C83CC44443B9EA6E0246394FF53.png阿里的域名
					//map.put("resResult",outPutFileMP3);
					//map.put("fileName",fileType);
					//logger.info("上传信息：文件类型"+fileType+"，文件路径返回 fileUrl"+outPutFileMP3);
				}else{
						iat ="";
						nlp= "对不起，我没听清楚，请您再说一遍.";
						map.put("quesion", iat);
						map.put("answer", nlp);
						map.put("answerType", "1");//给出类型 1 正常问答的方式  
						String answerVoice =  WebaiuiTtsUtil.getWebTtsVoiceUrlByText(nlp);//WebTtsUtil.getWebTtsVoiceUrlByText(nlp);
						map.put("answerVoice",answerVoice);
						System.out.println("nlp: "+nlp+"aaaaaaa"+answerVoice);
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponse.fail(411,"上传失败");
		} 
		 //map.put("code","200");
		 //如果需要的话，可以用自己的域名进行替换。 replace 即可
		 System.out.println("#####map "+map);
		 long endTime = System.currentTimeMillis();
		 System.out.println("#################### audioUpload deal end time  "+beginTime);
		 System.out.println("#################### total time seconds  "+(endTime -beginTime)/1000);
		 //add by hqt 时间戳 orderTime
		 map.put("timeStamp", beginTime+"");
		 return ApiResponse.success(map); 
	}
	
	
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
			 System.out.println("###GuavaCacheUtil.getIfPresent ###:"+timeStamp+"&nlp:"+nlp);
			 if(StringUtils.isNotEmpty(nlp)){
				answerVoice = WebaiuiTtsUtil.getWebTtsVoiceUrlByText(nlp);
			 }
		}
		map.put("answerVoice",answerVoice);
		long nowTime = System.currentTimeMillis();
		//add by hqt 时间戳 orderTime
		map.put("timeStamp", nowTime+"");
		System.out.println(" ######## ApiResponse.success %%%%"+map.toString());
		return ApiResponse.success(map); 
	}
	
	public String getWebTtsVoiceUrlByText(String nlp) {
		
		String webttsResultUrl = null;
		try{
		//System.out.println("## before getWebTtsVoiceUrlByText ##"+text);
		//text = "坐姿通常是指人体在坐着时候的姿态。正确坐姿，除了遵循以下技巧摆放双腿外，还应时时保持上半身挺直的姿势，也就是颈、胸、腰都要保持平直。被测者挺胸坐在被调节到腓骨头高度的平面上，头部以眼耳平面定位，眼睛平视前方，左、右大腿大致平行，膝弯屈大致成直角，足平放在地面上，手轻放在大腿上。!";//火落落，舞台礼仪？";//"北京明天的天气";
		Map<String, String> header = WebaiuiTtsUtil.buildHeader();
		//String str = "你好，你能给我讲五十步笑百步吗？非常感谢您!";//火落落，舞台礼仪？";//"北京明天的天气";
		//byte[] dataByteArray = str.getBytes("utf-8");
		System.out.println("## after getWebTtsVoiceUrlByText ##"+nlp);
		byte[] dataByteArray = nlp.getBytes("utf-8");
		//byte[] dataByteArray = readFile(FILE_PATH);
		long beginTime = System.currentTimeMillis();
		System.out.println(" #####WebaiuiTtsUtil  getWebTtsVoiceUrlByText  "+ beginTime);
		System.out.println("####header :"+header);
		String resultHttpPost = httpPost(WebaiuiTtsUtil.AIUI_URL, header, dataByteArray);
		long gethttpPoseTime = System.currentTimeMillis();
		//System.out.println("httpPost resultHttpPost:"+resultHttpPost);
		System.out.println("#################### WebaiuiTtsUtil  gethttpPostTime  "+(gethttpPoseTime -beginTime)  + "##" +(gethttpPoseTime -beginTime)/1000);
		
		AIUIEntity aiuiEntity = JsonMapper.fromJson(resultHttpPost, AIUIEntity.class);
		long getaiuiEntityTime = System.currentTimeMillis();
		System.out.println(aiuiEntity.getSid() +" :sid#################### WebaiuiTtsUtil  getaiuiEntityTime  "+(getaiuiEntityTime-gethttpPoseTime)  + "##" +(getaiuiEntityTime-gethttpPoseTime)/1000);
		
		if(null != aiuiEntity && aiuiEntity.getCode() !=null && aiuiEntity.getCode().equals("0")){
			//System.out.println("22222"+);
			List<Data> dataList = aiuiEntity.getData();

			for(Data data:dataList){
				if(null != data && data.getAuth_id() != null){
					String sub = data.getSub();
					//业务类型：iat（识别）
					if(null != sub && sub.equals("tts")){
						String content = data.getContent();
						byte[] audio = Base64.decodeBase64(content.getBytes("UTF-8"));
						//System.out.println("解析完合成音频字节流");
						//FileUtil.save("/Users/harry/out/", resultMap.get("sid") + ".mp3", (byte[]) resultMap.get("body"));
						InputStream inputStream = new ByteArrayInputStream(audio); 
						//FileUtil.save("/Users/harry/out/", "22211.mp3", audio);
						 Date d = new Date();
					     SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
					     String formatDate = format.format(d);
					     String str = "";
					     for(int i=0 ;i <5; i++){
					            int n = (int)(Math.random()*90)+10;
					            str += n;
					     }
					    // System.out.println("@@@@@@str random ::",str);
					     String fileMp3Name = formatDate+str+".mp3";
					     // mainAudioTtsPath + VideoDialogTtsName +VideoUtils.FILE_SEPARATOR;
					     //所有的音频文件进行分类存放 一级目录为  dialog
						 //设置本地路径
//						 String mainPath = config.getConfig().getProperty("video.dialog.path");//"/Users/harry/out/";//BaseConfigHolder.getVideoDialogPath();//
//						 mainPath = mainPath  + dir;
					     System.out.println("before fileUtil save mainAudioTtsPath"+WebaiuiTtsUtil.mainAudioTtsPath
					    		 +"@@fileMp3Nmae^^"+fileMp3Name );
						 FileUtil.save( WebaiuiTtsUtil.mainAudioTtsPath + WebaiuiTtsUtil.VideoDialogTtsName +VideoUtils.FILE_SEPARATOR,fileMp3Name,audio);
						 //webttsResultUrl = "https://robot.lianggehuangli.com/tts/"+fileMp3Name;
						 webttsResultUrl =WebaiuiTtsUtil.audioUrlPre +fileMp3Name;
						 System.out.println("#####webttsResultUrl  not oss server file  "+ webttsResultUrl);
//						 webttsResultUrl = OSSUploadUtil.uploadFileNewName(inputStream,"mp3", VideoDialogTtsName+"/");
						 break;
						//String ossUrl=OSSUploadUtil.uploadFileNewName(inputStream,"mp3", "qa/");
						//System.out.println(ossUrl + "合成 WebAPI 调用成功，音频保存位置：resource\\" + resultMap.get("sid") + ".mp3");
					}
				}
			}
		}
		long getUploadFileNewNameTime = System.currentTimeMillis();
		System.out.println("#################### WebaiuiTtsUtil  getUploadFileNewNameTime  "+(getUploadFileNewNameTime-getaiuiEntityTime)  + "##" +(getUploadFileNewNameTime-getaiuiEntityTime)/1000);
		}catch(Exception e ){
			e.printStackTrace();
		}
		return webttsResultUrl;
		
	}

	public static String httpPost(String url, Map<String, String> header, byte[] body) {
		String result = "";
		BufferedReader in = null;
		OutputStream out = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			//connection.setConnectTimeout(20000);
			//connection.setReadTimeout(20000);
			try {
				out = connection.getOutputStream();
				out.write(body);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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