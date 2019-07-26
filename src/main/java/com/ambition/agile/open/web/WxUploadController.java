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
import com.ambition.agile.common.media.VideoUtils;
import com.ambition.agile.common.util.PropertiesFactory;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.iflytek.util.WebaiuiUtil;
import com.iflytek.voicecloud.webapi_tts.WebTtsUtil;

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
	
	@RequestMapping(value = "audioUpload")
	@ResponseBody
	public ApiResponse<?> audioUpload(@RequestParam(value = "audioFile", required = false) MultipartFile audioFile,
			String dir,String openId, RedirectAttributes redirectAttributes) {
		
		Map<String,String> map=new HashMap<String,String>();
		 //所有的音频文件进行分类存放 一级目录为  dialog
		 if(dir==null || dir.equals("") || dir.equals("undefined") ){
			 dir = config.getConfig().getProperty("video.dialog.name")+ VideoUtils.FILE_SEPARATOR;//"dialog"
		 }
		 //二级目录存放在 openId 每个用户自己的目录下
		 if(null != openId && !openId.equals("")  && !openId.equals("undefined") ){
			 dir = dir +  openId + VideoUtils.FILE_SEPARATOR; 
		 }
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
				map.put("fileName", fileType);
				String type = fileType.substring(fileType.lastIndexOf(".") + 1, fileType.length());
				//原来的思路是上传oss ,在 oss 上进行文件转换，但oss 不支持 mp3 转成pcm 只有服务器端进行存储，然后调用转换接口。
				//dir = "dialog";
				//String ossUrl=OSSUploadUtil.uploadFileNewName(audioFile.getInputStream(),type, dir);
				
				SaveFileFromInputStream(audioFile,outPutFileMP3);
				
				//outPutFileMP3;"/Users/harry/out/nihao.mp3";//
				//String outPath = outPutFile.replace(".mp3", ".pcm");//"/Users/harry/out/nihao.pcm";//
				final VideoUtils v = new VideoUtils(outPutFileMP3,outPutFilePCM,null);
				v.convert();
				// 开启进程，在转换视频文件
				/*new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(100);
								v.convert();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start(); 
				*/
				
				
				System.out.println("$$$$$$$$ audioUpload  convert new ossUrl:  "+outPutFileMP3);
				System.out.println("$$$$$$$$ audioUpload convert new outPath:  "+outPutFilePCM);
				
				Map mapAIUI = WebaiuiUtil.aiuiWebApiDealFile(outPutFilePCM);
				System.out.println("########result wxupload file :"+ mapAIUI);
				String iat = (String)mapAIUI.get("iat");
				if(StringUtils.isNotEmpty(iat)){
					map.put("quesion", iat);
				}
				String nlp = (String)mapAIUI.get("nlp");
				if(StringUtils.isNotEmpty(nlp)){
					map.put("answer", nlp);
					String answerVoice =  WebTtsUtil.getWebTtsVoiceUrlByText(nlp);
					map.put("answerVoice",answerVoice);
				}
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
				if(outPutFilePCM==null){
					return ApiResponse.fail(404,"上传失败");
				}
				//暂时先用https//https://video-robot.oss-cn-beijing.aliyuncs.com/course248A0C83CC44443B9EA6E0246394FF53.png阿里的域名
				//map.put("resResult",outPutFileMP3);
				//map.put("fileName",fileType);
				logger.info("上传信息：文件类型"+fileType+"，文件路径返回 fileUrl"+outPutFileMP3);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ApiResponse.fail(411,"上传失败");
		} 
		 //map.put("code","200");
		 //如果需要的话，可以用自己的域名进行替换。 replace 即可
		 //mainPath = "http:/"+ mainPath;
		 //map.put("fileUrl",mainPath);
		 
		
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