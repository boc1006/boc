package com.boc.concurrent04;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
public class T4 {
	private static BlockingQueue<String> strs = new LinkedBlockingQueue<>();//无界队列
//	private static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);//有界队列
	private static Random r = new Random();
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i = 0 ; i < 100 ; i ++) {
						strs.put("a"+i);//如果容器满了,生产线程进入等待
						TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
		
		for(int i = 0 ; i < 5  ; i ++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							System.out.println(Thread.currentThread().getName()+" take -"+strs.take());//如果空了,消费线程进入等待
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			},"c"+i).start();
		}
	}
}
