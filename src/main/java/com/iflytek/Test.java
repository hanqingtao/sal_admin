package com.iflytek;

import com.ambition.agile.common.media.VideoUtils;
import com.baidu.ueditor.PathFormat;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String physicalPath = "/Users/harry/out/nihao.mp3";//
		String outPath = "/Users/harry/out/nihao.pcm";//
		final VideoUtils v = new VideoUtils(physicalPath,outPath,null);
		// 先截图 
			// 开启进程，在转换视频文件
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
						v.convert();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();  
			//storageState.putInfo("url", ctx + PathFormat.format(savePath) + "." + v.getOutputFileExtension());
			//storageState.putInfo("type", "." + v.getOutputFileExtension());
			//storageState.putInfo("original", originFileName +"."+ v.getInputFileExtension());
			//return storageState;
	}

}
