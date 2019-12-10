/**
 * 
 */
package com.ambition.agile.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ambition.agile.common.util.SnowFlakeIdGenerator;

import java.util.UUID;

import java.sql.Date;

/**
 * @author harry
 *
 */
public class Tests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeCfilass() throws Exception {
		System.out.println("setUpBeforeClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
		/**
		 * 测试
		 */

			SnowFlakeIdGenerator idWorker = new SnowFlakeIdGenerator(1, 1);
			long startime = System.currentTimeMillis();
			for (int i = 0; i < 4; i++) {
				long id = idWorker.nextId();
           		//System.out.println(Long.toBinaryString(id));
            	//System.out.println(id);

			}
			//System.out.println(System.currentTimeMillis() - startime);



	}

	@Test
	public void test() {

//		UUID uuid = UUID.randomUUID();
//		System.out.println(uuid);
//		Date date = new Date();
//		System.out.println(date.toString() + "");
		StringBuffer stringBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
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
		System.out.println(stringBuffer.toString());
		
		//fail("Not yet implemented");
	}

	public static String[] chars = new String[] {
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"
	};


}
