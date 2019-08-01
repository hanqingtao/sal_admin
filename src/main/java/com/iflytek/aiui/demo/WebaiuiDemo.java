package com.iflytek.aiui.demo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.ambition.agile.common.mapper.JsonMapper;
import com.ambition.agile.open.entity.AIUIEntity;
import com.ambition.agile.open.entity.Data;
import com.ambition.agile.open.entity.Intent;

/**
 * AIUI WebAPI V2接口调用示例
 * 
 * 运行方法：直接运行 main()
 * aiui 技能调用，进行语义分析，返回处理结果
 * 结果： 控制台输出接口返回值信息
 * 
 * @author iflytek_aiui
 * 
 */
public class WebaiuiDemo {
	
	private static final String AIUI_URL = "http://openapi.xfyun.cn/v2/aiui";
	private static final String APPID = "5d3d95ea";
	private static final String API_KEY = "5fd24cbae0bd5917660afaa479929669";
	private static final String DATA_TYPE = "audio";// data_type text 文本  audio音频 
	private static final String SCENE = "main_box";//main_box 测试 text 类型  audio 用  main即可
	private static final String RESULT_LEVEL = "complete";
	private static final String SAMPLE_RATE = "16000"; //默认 16000 16k采样率   8000 8K采样率 
	private static final String AUTH_ID = "779705c3e4cfd0a279fb7cf1da752663";//29f0b5211cb74caf5320fb0625d6fb98";
	private static final String AUE = "raw";//可选值：raw（未压缩的pcm或wav格式）、speex（speex格式，即sample_rate=8000的speex音频）、speex-wb（宽频speex格式，即sample_rate=16000的speex音频），默认为 raw
	private static final String FILE_PATH = "/Users/harry/out/nihao.pcm";//16k_10.pcm";////test.txt";//16k_10.pcm";//bj_weather.wav";////16k_10.pcm"; // 中文
	// 个性化参数，需转义 个性化参数，json字符串，目前支持用户级（auth_id）、应用级（appid）和用户自定义级，不支持透传其他参数。
	private static final String PERS_PARAM = "{\\\"auth_id\\\":\\\"779705c3e4cfd0a279fb7cf1da752663\\\"}";
	
	public static void main(String[] args) throws IOException,ParseException, InterruptedException{
		Map<String, String> header = buildHeader();
		//String str = "火落落，舞台礼仪？";//"北京明天的天气";
		//byte[] dataByteArray = str.getBytes("utf-8");
		byte[] dataByteArray = readFile(FILE_PATH);
		//System.out.println(dataByteArray.toString());
		String result = httpPost(AIUI_URL, header, dataByteArray);
		System.out.println(result);
		AIUIEntity aiuiEntity = JsonMapper.fromJson(result, AIUIEntity.class);
		if(null != aiuiEntity && aiuiEntity.getCode() !=null && aiuiEntity.getCode().equals("0")){
			System.out.println("#####"+aiuiEntity.getSid());
			List<Data> dataList = aiuiEntity.getData();

			for(Data data:dataList){
			
				if(null != data && data.getAuth_id() != null){
					String sub = data.getSub();
					//业务类型：iat（识别）
					if(null != sub && sub.equals("iat")){
						System.out.println(data.getText());
					}
					//如果类型是 nlp 业务类型： nlp（语义），tpp（后处理），itrans（翻译）
					if(null != sub && sub.equals("nlp")){
						Intent intent = data.getIntent();
						if(null != intent && intent.getAnswer() != null){
							System.out.println(intent.getAnswer().getText());
						}
						
					}
				}
			}
		}
	}

	private static Map<String, String> buildHeader() throws UnsupportedEncodingException, ParseException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		//text 
		String param = "{\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\"}";
		//音频方式
		//String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\"}";		
		//complete
		//String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"result_level\":\""+RESULT_LEVEL+"\",\"scene\":\""+SCENE+"\"}";
		
		//使用个性化参数时参数格式如下：
		//String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);

		Map<String, String> header = new HashMap<String, String>();
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Appid", APPID);
		return header;
	}
	
	private static byte[] readFile(String filePath) throws IOException {
		InputStream in = new FileInputStream(filePath);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		byte[] data = out.toByteArray();
		in.close();
		return data;
	}
	
	private static String httpPost(String url, Map<String, String> header, byte[] body) {
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
}
