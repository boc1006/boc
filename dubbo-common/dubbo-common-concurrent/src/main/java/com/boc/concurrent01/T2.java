package com.boc.concurrent01;

import java.util.concurrent.TimeUnit;

/**
 * 未加锁的方法在执行过程中不受加锁方法的影响
 * <p>@Title: T2.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午8:28:06 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T2 {
	public synchronized void m1() {
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
			System.out.println("m1 method");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void m2() {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println("m2 method");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		T2 t2 = new T2();
		new Thread(t2::m1).start();
		new Thread(()->{
			t2.m2();
		}).start();
	}
}
