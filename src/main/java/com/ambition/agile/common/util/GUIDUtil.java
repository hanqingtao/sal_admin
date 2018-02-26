package com.ambition.agile.common.util;


import java.util.UUID;

/*
 * @author: hqt
 * @see: 动态生成 36位字符串
 * @date:Aug 7, 2013 4:14:47 PM
 */
public class GUIDUtil {

	public static String generateGUID() {
		UUID ud = UUID.randomUUID();
		return ud.toString().replaceAll("-", "");
	}

	public static void main(String[] argv) {
		String t = generateGUID();
		System.out.println(t);
		System.out.println(t.length());
	}
}
