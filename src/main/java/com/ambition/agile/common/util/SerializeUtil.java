package com.ambition.agile.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

	public static byte[] serialize(Object object) {

		ObjectOutputStream oos = null;

		ByteArrayOutputStream baos = null;

		try {

			//序列化

			baos = new ByteArrayOutputStream();

			oos = new ObjectOutputStream(baos);

			oos.writeObject(object);

			byte[] bytes = baos.toByteArray();

			return bytes;

		} catch (Exception e) {
			e.printStackTrace();	
		}

		return null;

	}

	public static Object unserialize(byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {

			//反序列化

			bais = new ByteArrayInputStream(bytes);

			ObjectInputStream ois = new ObjectInputStream(bais);

			return ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();	
		}

		return null;

	}
	
	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
		| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	} 

}
