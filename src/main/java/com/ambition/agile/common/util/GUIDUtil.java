package com.ambition.agile.common.util;


import java.util.UUID;

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
	
	public static String getEightCardByGUID(){
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
	
	public static String getSixCardByGUID(){
		StringBuffer stringBuffer = new StringBuffer();
		String uuid = generateGUID();//UUID.randomUUID().toString().replace("-", "");
		System.out.println("uuid "+uuid);
		for (int i = 0; i < 6; i++) { // 32 -> 8
			String str = uuid.substring(i * 5, i * 5 + 5);
			System.out.println("str:"+str);
			// 16进制为基解析
			int strInteger = Integer.parseInt(str, 16);
			System.out.println(strInteger + "&&&" + chars[strInteger % 0x3E]);
			// 0x3E -> 字典总数 62
			stringBuffer.append(chars[strInteger % 0x3E]);
		}
		return stringBuffer.toString();
	}
	
	public static void main(String[] argv) {
		String eight = getTenCardByGUID();
		String ten = getEightCardByGUID();
		String six = getSixCardByGUID();
		System.out.println(eight);
		System.out.println(ten);
		System.out.println(six);
	}
}
