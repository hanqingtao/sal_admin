package com.iflytek;

import com.ambition.agile.common.media.VideoUtils;
import com.baidu.ueditor.PathFormat;

public class Test {

	public static void main(String[] args) {
		
		
		String fileName = "wxaa6536bb52e66d04.o6zAJs5BqVTlVZvDNC7qPfWze2xo.NwfvgMyEd4oI1ac0131b90fedf48a8b72c952a7fa459.durationTime=6837.mp3";
		String fileNameNow = fileName.substring(fileName.lastIndexOf("."));
		String fileNameNow1 = fileName.substring(0,fileName.lastIndexOf("."));
		System.out.println(fileNameNow);
		System.out.println(fileNameNow1);
		String outPath = "/Users/harry/out/nihao.pcm";//
		//final VideoUtils v = new VideoUtils(physicalPath,outPath,null);
		// 先截图 
			// 开启进程，在转换视频文件
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(5000);
//						v.convert();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();  
			//storageState.putInfo("url", ctx + PathFormat.format(savePath) + "." + v.getOutputFileExtension());
			//storageState.putInfo("type", "." + v.getOutputFileExtension());
			//storageState.putInfo("original", originFileName +"."+ v.getInputFileExtension());
			//return storageState;
	}

}
