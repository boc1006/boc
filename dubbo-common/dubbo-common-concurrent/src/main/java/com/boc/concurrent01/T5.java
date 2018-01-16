package com.boc.concurrent01;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中,如果程序出现异常,默认情况下,锁是会被释放的,所在在并发处理过程中,有异常要多加小心,不然可能会发生不一致的情况.
 * 比如,在一个web app处理过程中,多个servlet线程共同访问一个资源,这时如果异常处理不合适,在第一个线程中抛出异常,其它线程就会
 * 进入同步代码块,有可能会访问到异常产生的数据.因此要非常小心同步业务逻辑中的异常情况
 * <p>@Title: T5.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午8:53:49 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T5 {
	int count =0;
	synchronized void m() {
		System.out.println(Thread.currentThread().getName()+" start");
		while(true) {
			count++;
			System.out.println(Thread.currentThread().getName()+" count="+count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(count ==5) {
				int i = 1/0;
			}
		}
	}
	
	public static void main(String[] args) {
		T5 t5 = new T5();
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				t5.m();
			}
		};
		new Thread(r,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(r,"t2").start();
	}
}
