package com.boc.nio01;

import java.nio.ByteBuffer;

/**
 * NIO之 Buffer
 * <p>@Title: T1.java
 * <p>@Package com.boc.nio01
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年10月3日 上午10:47:41
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T1 {
	public static void main(String[] args) {
//		IntBuffer ib = IntBuffer.allocate(1024);//除boolean之外的7大基本数据类型都有一个与之对应的Buffer
		ByteBuffer bf = ByteBuffer.allocate(1024);
		System.out.println("------------init--------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());
		String str = "abcde";
		bf.put(str.getBytes());
		System.out.println("------------data write1--------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());

		bf.put("fghij".getBytes());
		System.out.println("------------data write2--------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());

		bf.flip();//切换数据读取模式
		System.out.println("------------data read1--------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());

		byte b[] = new byte[bf.limit()];
		bf.get(b);
		System.out.println(new String(b));
		System.out.println("------------data read2--------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());
		bf.rewind();//重读，相当于把position位置重置为0
		byte b2[] = new byte[2];
		bf.get(b2);
		System.out.println(new String(b2));//打印ab
		System.out.println("------------data read3--------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());
		bf.mark();//步骤1、在需要重读到某个指定位置时，需要在读取的时候使用该 方法进行标记，然后使用bf.reset()重置position到mark的位置.例如当前标记的位置为2
		bf.get(b2);
		System.out.println("------------data read4--------------");
		System.out.println(new String(b2));//打印cd
		bf.reset();//重置position到2的位置，也就是步骤1
		bf.get(b2);
		System.out.println("------------data read5--------------");
		System.out.println(new String(b2));//打印cd
	}
}
