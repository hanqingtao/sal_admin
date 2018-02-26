package com.ambition.agile.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * 获取文件/文件夹的大小
 * @author  lxh
 *
 */
public class GetFileSize {
	
	public static long getFileSizes(File f) throws Exception{//取得文件大小
        long s=0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
           s= fis.available();
        } else {
            f.createNewFile();
        }
        return s;
    }
	
	 // 递归取得文件夹大小
    public static long getFileSize(File f)throws Exception{
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++){
            if (flist[i].isDirectory()){
                size = size + getFileSize(flist[i]);
            }else{
                size = size + flist[i].length();
            }
        }
        return size;
    }
    
    ///转换文件大小
    public static String  FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
    //递归求取目录文件个数
    public static long getlist(File f){
        long size = 0;
        File flist[] = f.listFiles();
        size=flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;
    }
    
}
