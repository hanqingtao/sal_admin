package com.iflytek;

import java.lang.reflect.Type;

import com.ambition.agile.open.entity.AIUIEntity;
import com.ambition.agile.open.entity.Result;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class Test {

	public static void main(String[] args)  throws Exception {
		
		
		String a = "{\"data\":[{\"sub\":\"iat\",\"auth_id\":\"779705c3e4cfd0a279fb7cf1da752663\",\"text\":\"给我讲3\",\"result_id\":1},{\"sub\":\"iat\",\"auth_id\":\"8b4373e169cc18e4c157d15b2749d5f4\",\"text\":\"\",\"result_id\":2},{\"sub\":\"nlp\",\"auth_id\":\"8b4373e169cc18e4c157d15b2749d5f4\",\"intent\":{},\"result_id\":2},{\"sub\":\"nlp\",\"auth_id\":\"8b4373e169cc18e4c157d15b2749d5f4\",\"intent\":{\"answer\":{\"text\":\"请欣赏3222\",\"type\":\"T\"},\"category\":\"OS7596127858.2huanglispeak\",\"data\":{\"result\":[{\"courseName\":\"3222\",\"duration\":\"\",\"durationLong\":\"0\",\"voicePath\":\"http://video-robot.oss-cn-beijing.aliyuncs.com/courseCC0B0BFBFC954271B918FCACA417B3F2.jpg\"}]},\"intentType\":\"custom\",\"rc\":0,\"semantic\":[{\"entrypoint\":\"ent\",\"intent\":\"query\",\"score\":0.8545160293579102,\"slots\":[{\"begin\":3,\"end\":4,\"name\":\"bizName\",\"normValue\":\"3\",\"value\":\"3\"}],\"template\":\"你能给我讲{bizName}吗\"}],\"semanticType\":1,\"service\":\"OS7596127858.2huanglispeak\",\"sessionIsEnd\":\"false\",\"shouldEndSession\":true,\"sid\":\"atn050199f7@dx000710989c50782d01\",\"state\":null,\"text\":\"给我讲3\",\"uuid\":\"atn050199f7@dx000710989c50782d01\",\"vendor\":\"OS7485603678\",\"version\":\"3.0\"},\"result_id\":1}],\"sid\":\"ara05fe7ea7@dx000110989c5f094000\",\"code\":\"0\",\"desc\":\"success\"}";
		Gson gson = new Gson();
		//AIUIEntity aiuiEntity = JsonMapper.fromJson(a, AIUIEntity.class);
		//Type collectionType = new TypeToken<Collection<ChannelSearchEnum>>(){}.getType();
		//Collection<ChannelSearchEnum> enums = gson.fromJson(yourJson, collectionType);
		Type collectionType = new TypeToken<AIUIEntity>(){}.getType();
		String data3 = gson.toJson(a);
		//AIUIEntity aiuiEntity2 = JSON.parseObject(a, AIUIEntity.class);
		//AIUIEntity aiuiEntity3 = gson.fromJson(data3, AIUIEntity.class);
		//Jackson
		 //ObjectMapper objectMapper = new ObjectMapper();
		// String jsonString = "{\"name\":\"张三\",\"sex\":\"男\",\"age\":25}";
		// AIUIEntity aIUIEntity22 = objectMapper.readValue(a, AIUIEntity.class);
		 JsonParser parser=new JsonParser();  //创建JSON解析器
		 JsonObject object=(JsonObject) parser.parse(a);  //创建JsonObject对象
		
		 JsonArray arrays=object.get("data").getAsJsonArray();
		 System.out.println(arrays);
		 Result result = null;
		 for(int i=0;i<arrays.size();i++){
             System.out.println("---------------");
             JsonObject subObject=arrays.get(i).getAsJsonObject();
             String subType = subObject.get("sub").getAsString();
             if(null != subType && subType.equals("nlp") && null != subObject.get("intent") && 
            		 !subObject.get("intent").toString().equals("{}")){
            	 	
            	 	System.out.println(subObject.get("intent").toString());
            	 	String intentStr = subObject.get("intent").toString();
            	 	JsonObject intentObject=(JsonObject) parser.parse(intentStr);
            	 	System.out.println(intentObject.get("category").toString());
            	 	String   intentDataStr = intentObject.get("data").toString();
            	 	if(null == intentObject.get("service") ){
            	 		continue;
            	 	}
            	 	String intentService = intentObject.get("service").toString();
            	 	intentService = intentService.replace("\"", "");
        	 		System.out.print("intentService ####"+intentService+"aaa");
        	 		String Service = "OS7596127858.2huanglispeak";
        	 		//此时是技能方法
        	 		if(intentService.equals(Service)){
        	 			System.out.println(intentDataStr);
                	 	intentDataStr = intentDataStr.replace("[", "").replace("]", "");
                	 	System.out.println(intentDataStr);
                		JsonObject intentDataObject=(JsonObject) parser.parse(intentDataStr);
                		String intentDataResultStr = intentDataObject.get("result").toString();
                		JsonObject intentDataResultObject=(JsonObject) parser.parse(intentDataResultStr);
                		result = gson.fromJson(intentDataResultObject,Result.class);
                		System.out.println(result.toString());
        	 		}
            	 	
             }
         }
		//AIUIEntity aiuiEntity = gson.fromJson(a, AIUIEntity.class);
		//System.out.println(aiuiEntity);
		//		String fileName = \\"wxaa6536bb52e66d04.o6zAJs5BqVTlVZvDNC7qPfWze2xo.NwfvgMyEd4oI1ac0131b90fedf48a8b72c952a7fa459.durationTime=6837.mp3\\";
//		String fileNameNow = fileName.substring(fileName.lastIndexOf(\\".\\"));
//		String fileNameNow1 = fileName.substring(0,fileName.lastIndexOf(\\".\\"));
//		System.out.println(fileNameNow);
//		System.out.println(fileNameNow1);
//		String outPath = \\"/Users/harry/out/nihao.pcm\\";//
		//final VideoUtils v = new VideoUtils(physicalPath,outPath,null);
		// 先截图 
			//storageState.putInfo(\\"url\\", ctx + PathFormat.format(savePath) + \\".\\" + v.getOutputFileExtension());
			//storageState.putInfo(\\"type\\", \\".\\" + v.getOutputFileExtension());
			//storageState.putInfo(\\"original\\", originFileName +\\".\\"+ v.getInputFileExtension());
			//return storageState;
	}

}
