package com.ambition.agile.common.util;


import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/*
 * @author: hqt
 * @see: 动态生成 36位字符串
 * @date:Aug 7, 2013 4:14:47 PM
 */
public class GUIDUtil {

	public static String[] chars = new String[] {
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"
	};
	
	public static String[] charsPwdSmiple = new String[] {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K","L", "M", "N", "6", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "6", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"
	};
	public static String[] charsCodeSmiple = new String[] {
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0","1","2", "3", "4", "5", "6", "7", "8", "9", "0",  "1", "2","3", "4", "5",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1","2", "3", "4", "5"
	};

	public static String generateGUID() {
		UUID ud = UUID.randomUUID();
		return ud.toString().replaceAll("-", "");
	}

	public static String getCardByGUID(){
		StringBuffer stringBuffer = new StringBuffer();
		String uuid = generateGUID();//UUID.randomUUID().toString().replace("-", "");
		System.out.println("uuid "+uuid);
		for (int i = 0; i < 8; i++) { // 32 -> 8
			String str = uuid.substring(i * 4, i * 4 + 4);
			System.out.println("str:"+str);
			// 16进制为基解析
			int strInteger = Integer.parseInt(str, 16);
			System.out.println(strInteger + "&&&" + chars[strInteger % 0x3E]);
			// 0x3E -> 字典总数 62
			stringBuffer.append(chars[strInteger % 0x3E]);
		}
		return stringBuffer.toString();
	}
	
	public static String getTenCardByGUID(){
		StringBuffer stringBuffer = new StringBuffer();
		String uuid = generateGUID();//UUID.randomUUID().toString().replace("-", "");
		System.out.println("uuid "+uuid);
		for (int i = 0; i < 10; i++) { // 32 -> 8
			String str = uuid.substring(i * 3, i * 3 + 3);
			System.out.println("str:"+str);
			// 16进制为基解析
			int strInteger = Integer.parseInt(str, 16);
			System.out.println(strInteger + "&&&" + chars[strInteger % 0x3E]);
			// 0x3E -> 字典总数 62
			stringBuffer.append(chars[strInteger % 0x3E]);
		}
		return stringBuffer.toString();
	}
	
	private static byte[] lock = new byte[0];
	 
	// 位数，默认是8位
	private final static long w = 10000;
	//获取7位数字
	public static String getSevenCardByGUID() {
		long r = 0;
		synchronized (lock) {
			r = (long) ((Math.random() + 1) * w);
		}
		String t = System.nanoTime()+"";
		System.out.println("$@$$$$$"+System.nanoTime());
//		System.out.println("######## r "+r);
//		System.out.println("System.currentTimeMillis()  "+System.currentTimeMillis());
//		//String code = "X"+System.currentTimeMillis()+ String.valueOf(r).substring(1);
//		System.out.println("getEightCardByGUID"+System.currentTimeMillis());
//		System.out.println("$@$$$$$"+System.nanoTime());
		String code = "X" + t.substring(8,15)+String.valueOf(r).substring(1);
		System.out.println("CCCCcode"+code);
		//code 是可以生成的 
		//Random rand = new Random();
		 //String codeTmp = "X"+(rand.nextInt(1000000) + 1);
		Double dd = new Double((Math.random()*9+1)*1000000);
		int t1 = dd.intValue();
		String codeTmp = t1 +"";
		System.out.println("#####@@@"+codeTmp);
		return codeTmp;
	}
	//获取 6位数字
	public static String getEightCardByGUID() {
		long r = 0;
		synchronized (lock) {
			r = (long) ((Math.random() + 1) * w);
		}
		String t = System.nanoTime()+"";
		System.out.println("$@$$$$$"+System.nanoTime());
//		System.out.println("######## r "+r);
//		System.out.println("System.currentTimeMillis()  "+System.currentTimeMillis());
//		//String code = "X"+System.currentTimeMillis()+ String.valueOf(r).substring(1);
//		System.out.println("getEightCardByGUID"+System.currentTimeMillis());
//		System.out.println("$@$$$$$"+System.nanoTime());
		String code = "X" + t.substring(8,15)+String.valueOf(r).substring(1);
		System.out.println("CCCCcode"+code);
		//code 是可以生成的 
		Random rand = new Random();
		 String codeTmp = "X"+(rand.nextInt(1000000) + 1);
		//Double dd = new Double((Math.random()*9+1)*1000000);
		//int t1 = dd.intValue();
		//String codeTmp = t1 +"";
		//System.out.println("#####@@@"+codeTmp);
		return codeTmp;
	}
	
	public static String getEightCardByGUIDOld(){
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("X").append(RandomUtil.getRandomStringInRange(10000000, 99999999));
		return stringBuffer.toString();
		
		/*String uuid = generateGUID();//UUID.randomUUID().toString().replace("-", "");
		System.out.println("uuid "+uuid);
		for (int i = 0; i < 8; i++) { // 32 -> 8
			String str = uuid.substring(i * 4, i * 4 + 4);
			System.out.println("str:"+str);
			// 16进制为基解析
			int strInteger = Integer.parseInt(str, 16);
			System.out.println(strInteger + "&&&" + charsCodeSmiple[strInteger % 0x3E]);
			// 0x3E -> 字典总数 62
			stringBuffer.append(charsCodeSmiple[strInteger % 0x3E]);
		}
		*/
	}
	//默认生成 6位的 中文+ 字母的结合的密码 ，全大写
	public static String getSixCardByGUID(){
		StringBuffer stringBuffer = new StringBuffer();
		String uuid = generateGUID();//UUID.randomUUID().toString().replace("-", "");
		System.out.println("uuid "+uuid);
		for (int i = 0; i < 6; i++) { // 32 -> 8
			String str = uuid.substring(i * 5, i * 5 + 5);
			System.out.println("str:"+str);
			// 16进制为基解析
			int strInteger = Integer.parseInt(str, 16);
			System.out.println(strInteger + "&&&" + charsPwdSmiple[strInteger % 0x3E]);
			// 0x3E -> 字典总数 62
			stringBuffer.append(charsPwdSmiple[strInteger % 0x3E]);
		}
		return stringBuffer.toString();
	}
	
	//获取 18 位随机数
	public static String getEighteenCode(){
		String t = System.nanoTime()+"";
		System.out.println(" 18 wei "+t);
		return t;
	}
	//获取11位随机数字
		public static String getElevenCode(){
			long r = 0;
			synchronized (lock) {
				r = (long) ((Math.random() + 1) * w);
			}
			String t = System.nanoTime()+"";
			System.out.println("$@$$$$$"+System.nanoTime());
//			//String code = "X"+System.currentTimeMillis()+ String.valueOf(r).substring(1);
			System.out.println(t.substring(8,15)+"#"+r+"%sub"+String.valueOf(r).substring(1));
			String eleven =  t.substring(8,15)+String.valueOf(r).substring(1);
			System.out.println("eleven:"+eleven);  
			return eleven;
		}
		//获取10位随机数字
		public static String getTenCode(){
			
			Random random = new Random();
			int number=random.nextInt(900000)+100000;
			System.out.println("another method:"+number);
				String ten = (int)(((Math.random()*9+1)*1000000000)) + "";  
				System.out.println("eight:"+ten);  
				return ten;
		}
		//获取9位随机数字
		public static String getNineCode(){
				String nine = (int)(((Math.random()*9+1)*100000000)) + "";  
				System.out.println("eight:"+nine);  
				return nine;
		}
		//获取8位随机数字
		public static String getEightCode(){
				String eight = (int)(((Math.random()*9+1)*10000000)) + "";  
				System.out.println("eight:"+eight);  
				return eight;
		}
		//获取7位随机数字
		public static String getSevenCode() {
			//code 是可以生成的 
			//Random rand = new Random();
			 //String codeTmp = "X"+(rand.nextInt(1000000) + 1);
			Double dd = new Double((Math.random()*9+1)*1000000);
			int t1 = dd.intValue();
			String codeTmp = t1 +"";
			System.out.println("#####@@@seven:"+codeTmp);
			return codeTmp;
		}
		//获取6位随机数字
		public static String getSixCode(){
			String six = (int)(((Math.random()*9+1)*100000)) + "";  
			System.out.println(six);  
			return six;
		}
		//获取5位随机数字
		public static String getFiveCode(){
			String  five = (int)(((Math.random()*9+1)*10000))+"";  
			System.out.println(five);  
			return five;
		}
		//获取4位随机数字
		public static String getFourCode(){
			String  four = (int)(((Math.random()*9+1)*1000))+"";  
			System.out.println(four);  
			return four;
		}
		//获取4位随机数字
		public static String getThreeCode(){
			String  three = (int)(((Math.random()*9+1)*100))+"";  
			System.out.println(three);  
			return three;
		}
	
	public static void main(String[] argv) {
		//String ten = getTenCardByGUID();
		//String eight = getEightCardByGUID();
		//String six = getSixCardByGUID();
		//System.out.println(eight);
		//System.out.println(ten);
		//System.out.println(six);
		SnowFlakeIdGenerator gen = new SnowFlakeIdGenerator(1,1);
		System.out.println(gen.nextId());
		getEightCardByGUID();
		String evelen = getElevenCode();
		String ten = getTenCode();
		String nine = getNineCode();
		String eight = getEightCode();
		String seven = getSevenCode();
		String six = getSixCode();
		String five = getFiveCode();
		String four = getFourCode();
		String three = getThreeCode();
		System.out.println("ten "+ten + " nine "+nine+"eight"+eight+" 7 "+seven+" #6 "+six+" 5 "+five+" 4 "+four+" 3 "+three);
	}
}
