package com.boc.concurrent04;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列BlockingQueue
 * <p>@Title: T4.java 
 * <p>@Package com.boc.concurrent04 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 上午11:13:57 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T5 {
	private static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);//有界队列
	private static Random r = new Random();
	public static void main(String[] args) throws Exception{
		for(int i = 0 ; i <10 ;  i ++) {
			strs.put("a"+i);
		}
		
//		strs.put("a11");//会一直阻塞直到队列被消费
//		strs.add("a11");//超出容器最大容量,会抛出java.lang.IllegalStateException: Queue full
//		boolean success = strs.offer("a11");//超出容器最大容量,不会抛出异常,通过返回值去判断是否添加成功
//		System.out.println(success);
		boolean success = strs.offer("a11",1000,TimeUnit.MILLISECONDS);//超出容器最大容量,不会抛出异常,直到超过指定时间后返回一个添加成功标识
		System.out.println(success);
	}
}
