package com.ambition.agile.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.ambition.agile.common.util.ComUtil;
 

/*
 * 
 * @author hqt
 * @since Jun 28, 2013 10:43:57 AM
 * @see apache ftp  工具类 
 * @version 1.0
 */
public class FtpApacheUtil {
	
	private FTPClient ftpClient;
	
	public static String ip;
	
	public static int port; 
	
	private static String userName; 
	
	private static String passWord;
	
	public final int BUFFER_SIZE = 204800; 
	
	protected final static Logger logger = Logger.getLogger(FtpApacheUtil.class);
	
	public FtpApacheUtil() throws Exception {
		
	}

	static
	{
		ip = FtpConfigHolder.getFtpFileIp();
		port = FtpConfigHolder.getFtpFilePort();
		userName = FtpConfigHolder.getFtpFileUserName(); 
		passWord = FtpConfigHolder.getFtpFilePassWord();
		
	}
	/**
	 * @author hqt
	 * @since 9:58:14 AM
	 * @see 
	 */
	public void setClient() throws Exception {
		
		//ftpClient = FtpClientFactory.getFtpApacheClient();
		
		try{
			if(null == ftpClient){
				ftpClient = new FTPClient();
			}
			ftpClient.connect(ip, port);
		}catch (Exception e){
			logger.error("Failed to get connection :" + e.getMessage());
		}
		
		
	}
	
	public void login() throws Exception{
		
		try{
			if(null == ftpClient){
				logger.warn(" ftp ftpApacheClient not initialization!");
				return;
			}
			boolean blogin = ftpClient.login(userName, passWord);
			
			if (!blogin) {
				ftpClient.disconnect();
				ftpClient = null;
				logger.error(" ftp login defined! ");
				return;
			}
		}catch(IOException e){
			logger.error("ftp login :"+ e.getMessage());
		}
		//FtpClientFactory.apacheLogin();
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("GBK");
		ftpClient.setBufferSize(BUFFER_SIZE);
		//ftpClient.setSendBufferSize(BUFFER_SIZE);
	}
	
	public void logout(){
		
		try{
			if(null != ftpClient && ftpClient.isConnected()){
				ftpClient.logout();
			}
		}catch(IOException e){
			logger.error("ftp logout :"+ e.getMessage());
		}finally{
			
			if (ftpClient.isConnected()) {
                try {
                	ftpClient.disconnect();
                } catch (IOException ioe) {
                	ioe.printStackTrace();
                }    
            }
			
		}
		//return flag;
		//FtpClientFactory.apacheLogout();
		
	}
	
	/*
	 * 
	 * @author hqt
	 * @param String mainPath FTP服务器上的主保存目录  
     * @param String dir 上传到FTP服务器上的文件夹名 对于 scrom课件 为 id   
     * @param File file 输入文件  
	 * @since Jun 29, 2013 2:53:16 PM
	 * @return 成功返回true，否则返回false 
	 * @see 向FTP服务器上传文件夹
	 *
	 */
	public void uploadDirectory(String mainPath,String id,String fileFullPath) throws Exception {
		
		   File file = new File(fileFullPath);
		
	       if(file.isDirectory()){
	        	
	    	    //System.out.println("current workspace: "+ ftpClient.printWorkingDirectory());
	         	String dir1 = getDir(mainPath,id,null,file.getAbsolutePath());
                //System.out.println("$$$$$$$$$$$$$$$$$"+dir1 +"file Name :"+ file.getName() +"workdirectory:%%%%%%%%%%"+ftpClient.printWorkingDirectory());
		        ftpClient.makeDirectory(dir1); 
		        ftpClient.changeWorkingDirectory(dir1);  ftpClient.printWorkingDirectory();  
		        String[] files = file.list();
		        for (int i = 0; i < files.length; i++) {     
		                File file1 = new File(file.getPath()+"\\"+files[i] );      
		                if(file1.isDirectory()){
		                	uploadDirectory(mainPath,id,file1);  
		                	ftpClient.changeToParentDirectory();      
		                }else{     
		                    File file2 = new File(file.getPath()+"\\"+files[i]);      
		                    FileInputStream input = new FileInputStream(file2); 
		                    //String dir = getDirFromFile(mainPath,id,file2.getName(),file2.getAbsolutePath());
		                    String dir = getDir(mainPath,id,file2.getName(),file2.getAbsolutePath());
				        	ftpClient.makeDirectory(dir);                
				        	ftpClient.changeWorkingDirectory(dir);ftpClient.printWorkingDirectory();
				        	//System.out.println("workdirectory:"+dir+ "file name :"+file2.getName()+"workdirectory:$$$$$$$$" +ftpClient.printWorkingDirectory());
		                    ftpClient.storeFile( new String(file2.getName().getBytes("GBK"),"iso-8859-1"), input); 
		                    input.close();   
		                }      
		            }      
		        }else{
		            File file2 = new File(file.getPath());      
		            FileInputStream input = new FileInputStream(file2); 
		            String dir = getDir(mainPath,id,file2.getName(),file2.getAbsolutePath());
		        	ftpClient.makeDirectory(dir);                
		        	ftpClient.changeWorkingDirectory(dir);
		        	//System.out.println("workdirectory:"+dir+" file name :"+ file2.getName()+"workdirectory:%%%%%%%%%%"+ftpClient.printWorkingDirectory());
		            ftpClient.storeFile( new String(file2.getName().getBytes("GBK"),"iso-8859-1"), input);      
		            input.close();        
		        }
		        
	}
	
	/*
	 * 
	 * @author hqt
	 * @param String mainPath FTP服务器上的主保存目录  
     * @param String dir 上传到FTP服务器上的文件夹名 对于 scrom课件 为 id   
     * @param File file 输入文件  
	 * @since Jun 29, 2013 2:53:16 PM
	 * @return 成功返回true，否则返回false 
	 * @see 向FTP服务器上传文件夹
	 *
	 */
	public void uploadDirectory(String mainPath,String id,File file) throws Exception {
		
		
		
	       if(file.isDirectory()){
	        	
	    	    //System.out.println("current workspace: "+ ftpClient.printWorkingDirectory());
	         	String dir1 = getDir(mainPath,id,null,file.getAbsolutePath());
                //System.out.println("$$$$$$$$$$$$$$$$$"+dir1 +"file Name :"+ file.getName() +"workdirectory:%%%%%%%%%%"+ftpClient.printWorkingDirectory());
		        ftpClient.makeDirectory(dir1); 
		        ftpClient.changeWorkingDirectory(dir1);  ftpClient.printWorkingDirectory();  
		        String[] files = file.list();
		        for (int i = 0; i < files.length; i++) {     
		                File file1 = new File(file.getPath()+"\\"+files[i] );      
		                if(file1.isDirectory()){
		                	uploadDirectory(mainPath,id,file1);  
		                	ftpClient.changeToParentDirectory();      
		                }else{     
		                    File file2 = new File(file.getPath()+"\\"+files[i]);      
		                    FileInputStream input = new FileInputStream(file2); 
		                    //String dir = getDirFromFile(mainPath,id,file2.getName(),file2.getAbsolutePath());
		                    String dir = getDir(mainPath,id,file2.getName(),file2.getAbsolutePath());
				        	ftpClient.makeDirectory(dir);                
				        	ftpClient.changeWorkingDirectory(dir);ftpClient.printWorkingDirectory();
				        	System.out.println("workdirectory:"+dir+ "file name :"+file2.getName()+"workdirectory:$$$$$$$$" +ftpClient.printWorkingDirectory());
		                    ftpClient.storeFile( new String(file2.getName().getBytes("GBK"),"iso-8859-1"), input); 
		                    input.close();   
		                }      
		            }      
		        }else{
		            File file2 = new File(file.getPath());      
		            FileInputStream input = new FileInputStream(file2); 
		            String dir = getDir(mainPath,id,file2.getName(),file2.getAbsolutePath());
		        	ftpClient.makeDirectory(dir);                
		        	ftpClient.changeWorkingDirectory(dir);
		        	//System.out.println("workdirectory:"+dir+" file name :"+ file2.getName()+"workdirectory:%%%%%%%%%%"+ftpClient.printWorkingDirectory());
		            ftpClient.storeFile( new String(file2.getName().getBytes("GBK"),"iso-8859-1"), input);      
		            input.close();        
		        }
		        
	}
	
	

    /**
     * 
     * @author hqt
     * @since Jun 28, 2013 9:08:36 AM
     * @see 往ftp 上传单个文件
     */
    public boolean uploadFile( String path, String filename, InputStream input) {
    	
        boolean flag = false;    
        try {
        	
        	ftpClient.makeDirectory(path);
        	ftpClient.changeWorkingDirectory(path);
        	//System.out.println(ftpClient.printWorkingDirectory());
        	ftpClient.storeFile(new String(filename.getBytes("GBK"),"iso-8859-1"), input);
            input.close();
            ftpClient.logout();
            flag = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()){
                try {
                	ftpClient.disconnect();
                } catch (IOException ioe) {
                	
                }    
            }
        }    
        return flag;    
    }
    
    /**
     * 
     * @author hqt
     * @since Jun 28, 2013 9:08:36 AM
     * @see 往ftp 上传单个文件
     */
    public boolean uploadFile( String path, String filename, String fileFullPath) {
    	
        boolean flag = false;    
        try {
        	FileInputStream input = new FileInputStream(fileFullPath);
        	ftpClient.changeWorkingDirectory(path);
        	ftpClient.storeFile(new String(filename.getBytes("GBK"),"iso-8859-1"), input);
            input.close();
            ftpClient.logout();
            flag = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()){
                try {
                	ftpClient.disconnect();
                } catch (IOException ioe) {
                	ioe.printStackTrace();
                }
            }
        }
        return flag;
    }
    
    /**
     * FTP上传单个文件测试
     */
    public static void testUpload() {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null ;

        try {
            ftpClient.connect("192.168.1.137" );
            ftpClient.login("ftp", "ftp" );

            File srcFile = new File("C://new .gif" );
            fis = new FileInputStream(srcFile);
            //设置上传目录
            
            ftpClient.changeWorkingDirectory("/admin/pic");
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile("3.gif" , fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！" , e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！" , e);
            }
        }
    }

    /**
     * FTP下载单个文件测试
     */
    public static void testDownload() {
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null ;

        try {
            ftpClient.connect("192.168.1.137" );
            ftpClient.login("ftp" , "ftp" );

            String remoteFileName = "/admin/pic/3.gif" ;
            fos = new FileOutputStream("c:/down.gif" );

            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！" , e);
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！" , e);
            }
        }
    } 
    
    
	 
	 /*
	 * 
	 * @author hqt
	 * @param String mainPath 多级的目录 如: /photo/user
	 * @since Jun 29, 2013 3:06:41 PM
	 * @see 创建目录 
	 *
	 */
	public String createDir(String mainPath) throws Exception{
		 
 
		 StringTokenizer dirs = new StringTokenizer(mainPath, "/"); 
		 String pathName = "";
		 while (dirs.hasMoreElements()) {
			 
			 String dir = (String) dirs.nextElement();
			 ftpClient.makeDirectory(dir);                
			 ftpClient.changeWorkingDirectory(dir);
			 //System.out.println("current workspace: "+ ftpClient.printWorkingDirectory());
			 
		 }
		 return pathName;
	 }
	 
	 /*
	 * 
	 * @author hqt
	 * @since Jun 29, 2013 3:08:12 PM
	 * @see 获取 本级要上传的多级目录的层次
	 *
	 */
	public static String getDir(String mainPath,String id,String fileName,String path){

		path = path.replace("\\","/");
		 StringTokenizer dirs = new StringTokenizer(path, "/"); 
		 String pathName = "";
		 int t = 0;//计数器
		 int m = 0;//判断器
		 while (dirs.hasMoreElements()) {
			 
			 String dir = (String) dirs.nextElement();
			 
			 if(ComUtil.isNotNullOrEmpty(dir) && dir.equals(id)){
				 m =t;
				 pathName = mainPath + "/"+id ;
			 }
			 //如果是文件夹路径过来，只返回文件夹的相关路径
			 if(t >m && m>0 && ComUtil.isNullOrEmpty(fileName) ){
				 pathName = pathName + "/" + dir + "/"; 
			 }
			 
			 //如果是文件路径过来，只返回文件夹的相关路径 ，要把文件名过滤掉
			 if(t >m && m>0 && ComUtil.isNotNullOrEmpty(fileName)&& !fileName.equals(dir)){
				 pathName = pathName + "/" + dir + "/"; 
			 }
			 
			 t = t+1;
		 }
		 return pathName;
	 }
	 
	 @SuppressWarnings("deprecation")
	public void test1() throws Exception {
			// String strTemp = "";
			// InetAddress ia = InetAddress.getByName("192.168.0.193"); 
			FTPClient ftp = new FTPClient(); 
			ftp.connect("192.168.1.137",21 );
			boolean blogin = ftp.login("ftp", "ftp");
			if (!blogin) {
				System.out.println("测试");
				ftp.disconnect();
				ftp = null;
				return;
			}
			/*
			 *  boolean bMakeFlag = ftp.makeDirectory(new
			 * String("中共中央政治局常委".getBytes( "gb2312"), "iso-8859-1")); 
			 * //File file = new File("c:\\test.properties");
			 * ftp.storeFile("test.properties",new FileInputStream(file));
			 */
			System.out.println(ftp.getSystemName());
			FTPFile[] ftpFiles = ftp.listFiles();
			if (ftpFiles != null){
				for (int i = 0; i < ftpFiles.length; i++) {
					System.out.println(ftpFiles[i].getName()); 
					// System.out.println(ftpFiles[i].isFile());
					// if(ftpFiles[i].isFile()){
					//FTPFile ftpf = new FTPFile();
					 /*System.err.println(ftpf.hasPermission(FTPFile.GROUP_ACCESS,
					 FTPFile.EXECUTE_PERMISSION));
					 System.err.println("READ_PERMISSION="+ftpf.hasPermission(FTPFile.USER_ACCESS,
					 FTPFile.READ_PERMISSION));
					 System.err.println("EXECUTE_PERMISSION="+ftpf.hasPermission(FTPFile.USER_ACCESS,
					 FTPFile.EXECUTE_PERMISSION));
					 System.err.println("WRITE_PERMISSION="+ftpf.hasPermission(FTPFile.USER_ACCESS,
					 FTPFile.WRITE_PERMISSION));
					 System.err.println(ftpf.hasPermission(FTPFile.WORLD_ACCESS,
					 FTPFile.READ_PERMISSION));*/
				}
				// System.out.println(ftpFiles[i].getUser());
			}
		}
	 
	/*
	 * 
	 * @param args String[]
	 * 
	 */
	public static void main(String[] args) {
		
		try{
			/*
			FtpApacheUtil fau = new FtpApacheUtil();
			fau.test1();
			*/
			
			FtpApacheUtil ftpAppache1 = new FtpApacheUtil();
			FTPClient ftpClient = new FTPClient();
		 	ftpClient.connect("192.168.1.137",21 );
			boolean blogin = ftpClient.login("ftp", "ftp");
			if (!blogin) {
				System.out.println("exit");
				ftpClient.disconnect();
				ftpClient = null;
			}
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setBufferSize(2048000);
			ftpClient.setSendBufferSize(20480);
			
			long begin = System.currentTimeMillis();
			String path = "/courseServer/course/";
			String id = "948022";
			//String fullPath = "D:/tomcat6.0.37/webapps//ROOT/defaultCourseWareDepot/abc"; //947998
			ftpAppache1.createDir(path);
			//String fullPath = "D:/ftpWorkspace/video/948022";
			String fullPath = "D:/tomcat6.0.37/webapps/ROOT/defaultCourseWareDepot/948022";
			ftpAppache1.uploadDirectory(path,id,new File(fullPath));
			long end = System.currentTimeMillis();
			System.out.println("耗时：" +(end -begin));
			
			/*
			String path = "/courseServer/course/";
			ftpAppache1.createDir(path);
			String id = "abc";
			String fullPath = "D:/ftpWorkspace/video/948022"; //947998
			String fileName = "t"+fullPath.substring(fullPath.lastIndexOf("."));
			FileInputStream input = new FileInputStream(fullPath);
			ftpAppache1.uploadFile(path, fileName, input);
			*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
