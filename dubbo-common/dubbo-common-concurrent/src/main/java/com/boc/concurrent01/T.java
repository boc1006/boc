package com.boc.concurrent01;

import java.util.concurrent.TimeUnit;
/**
 * JAVA API提供了一套对基本数据类型的原子操作接口,以Atomic开头的，如：AtomicInteger、AtomicLong等。
 * <p>@Title: T.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午9:09:53 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T {
	private int count = 10;
	private static int sCount = 10;
	private Object o = new Object();

	public static void main(String[] args) {
		T t = new T();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public /*synchronized*/ void run() {
					t.m2();
					t.m3();
//					t.m4();
				}
			}).start();
		}
		try {
			TimeUnit.SECONDS.sleep(4);
			System.out.println("count=" + t.count);
//			System.out.println("sCount=" + t.sCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void m() {
		synchronized (o) {// 对o对象进行加锁
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				count--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\tcount=" + count);
		}
	}

	private void m2() {
		synchronized (this) {// 对当前对象进行加锁
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				count--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\tcount=" + count);
		}
	}

	private synchronized void m3() {// 对当前对象进行加锁
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			count--;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "\tcount=" + count);
	}
	
	private synchronized static void m4() {// 对当前类的Class对象进行加锁
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			sCount--;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "\tsCount=" + sCount);
	}
}
