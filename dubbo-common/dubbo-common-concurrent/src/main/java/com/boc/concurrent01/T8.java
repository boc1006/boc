package com.boc.concurrent01;

import java.util.concurrent.TimeUnit;

/**
 * 对字符串对象加锁需要注意,同一个字符串在堆内存中是同一个内存地址
 * 在开发过程中,最好不要使用字符串作为锁对象
 * <p>@Title: T8.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 上午9:25:13 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T8 {
	private String a1 = "hello";
	private String a2 = "hello";
	
	
	void m1() {
		synchronized(a1) {
			try {
				System.out.println("a1");
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	void m2() {
		synchronized(a2) {
			try {
				System.out.println("a2");
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		T8 t = new T8();
		new Thread(t::m1).start();
		new Thread(t::m2).start();
	}
}
