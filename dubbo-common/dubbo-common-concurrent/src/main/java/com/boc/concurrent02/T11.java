package com.boc.concurrent02;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * <p>@Title: T11.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 上午11:46:06 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T11 {
	int i = 0;
	void set() {
		i++;
		System.out.println("i=" + i);
	}
	int get() {
		return i;
	}
	public static void main(String[] args) {
		final T11 t = new T11();
		final CountDownLatch cdl = new CountDownLatch(1);//2代表被锁住的次数
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t1线程启动");
				if (t.get() != 5) {
					try {
						cdl.await();//使当前线程处理等待状态,当cdl.getCount()==0时，该线程释放等待并继续往下执行。
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t1线程结束...");
			}
		}, "t1").start();

		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						t.set();
						if (t.get() == 5) {
							cdl.countDown();//被锁住次数-1
							System.out.println(cdl.getCount());
						}
						if(t.get() == 10) {
							cdl.countDown();//被锁住次数-1
							System.out.println(cdl.getCount());
						}
						Thread.sleep(100);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
}
