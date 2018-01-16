package com.boc.nio01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * NIO之Channel
 * <p>@Title: T2.java 
 * <p>@Package com.boc.nio01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月3日 上午10:48:20 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T2 {
	public static void main(String[] args) throws Exception{
		File f = new File("D:\\1.txt");
		
		FileInputStream fis = new FileInputStream(f);
		
		FileChannel fc = fis.getChannel();
		
		ByteBuffer bf = ByteBuffer.allocate(1024);
		int byteRead = fc.read(bf);
		System.out.println("byteRead="+byteRead);
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());
		bf.flip();//切换操作模式
		System.out.println("--------------------");
		System.out.println(bf.capacity());
		System.out.println(bf.position());
		System.out.println(bf.limit());
		
		byte b[] = new byte[bf.limit()];
		bf.get(b);
		bf.rewind();//重置position到0位置
		System.out.println(new String (b,Charset.forName("utf8")));
		
		FileOutputStream fos = new FileOutputStream(new File("D:\\2.txt"));
		FileChannel fc2 = fos.getChannel();
		fc2.write(bf);
		bf.clear();//清空Buffer
		fos.close();//关闭fos
		fc.close();//关闭fc
		fc2.close();//关闭fc2
	}
}
