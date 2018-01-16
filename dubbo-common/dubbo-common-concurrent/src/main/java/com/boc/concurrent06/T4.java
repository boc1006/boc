package com.boc.concurrent06;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue和SynchronousQueue区别
 * <p>@Title: T4.java
 * <p>@Package com.boc.concurrent06
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年10月17日 下午2:52:56
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T4 {
	static ExecutorService esProductor = Executors.newFixedThreadPool(2);
	static ExecutorService esConsumer = Executors.newFixedThreadPool(2);
	static BlockingQueue<String> bq = new LinkedBlockingQueue<>();
	//	static BlockingQueue<String> bq = new SynchronousQueue<String>();
	final static int intProductor = 2;
	static int intConsumer = 2;

	public static void main(String[] args) {
		for (int i = 0; i < intProductor; i++) {
			final String temp = String.valueOf(i);
			esProductor.submit(new Runnable() {

				@Override
				public void run() {
					try {
						bq.put(temp);
						System.out.println("----" + temp);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
					}
				}
			});
		}
		try {
			Thread.sleep(11110);
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (int i = 0; i < intConsumer; i++) {
			esConsumer.submit(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(bq.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
