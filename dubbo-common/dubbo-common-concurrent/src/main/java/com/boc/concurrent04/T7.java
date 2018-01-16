package com.boc.concurrent04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue:容量为0的一种特殊的TransferQueue
 * <p>@Title: T7.java 
 * <p>@Package com.boc.concurrent04 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 下午1:15:38 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T7 {

	private static BlockingQueue<String> bq = new SynchronousQueue<>();//一种特殊的TransferQueue
	
	public static void main(String[] args) throws Exception {
		new Thread(()->{
			try {
				System.out.println("获取中...");
				System.out.println(bq.take());
				System.out.println("获取结束...");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}).start(); 
		//bq.add("aa");//java.lang.IllegalStateException: Queue full
		bq.put("aaa");//使用LinkedTransferQueue中的transfer()方法进行实现
	}
}
