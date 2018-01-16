package com.boc.concurrent05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * newCachedThreadPool 缓存线程：会根据任务不断向上添加线,默认开启的线程超过60秒未接收到任务,则会自动结束
 * <p>@Title: T4.java 
 * <p>@Package com.boc.concurrent05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 下午4:42:48 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T4 {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
//		ExecutorService es = Executors.newSingleThreadExecutor();//单任务线程池,一般用于按顺序执行的任务,保证任务按照进入时的顺序进行执行
		System.out.println(es);
		for(int i = 0 ; i < 2 ; i ++) {
			es.execute(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		System.out.println(es);
		try {
			TimeUnit.SECONDS.sleep(70);
			System.out.println(es);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
