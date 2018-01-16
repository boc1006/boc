package com.boc.concurrent03;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.FutureTask;

/**
 * ThreadLocal
 * ThreadLocal中的内容只能在当前线程中有效，用空间换时间，类似于把对象在该线程中进行了一次拷贝。
 * 一般在各线程中只维护当前线程状态时，可使用ThreadLocal来提升性能。
 * <p>@Title: T2.java 
 * <p>@Package com.boc.concurrent03 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午8:41:00 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T2 {
	static ThreadLocal<Person> tl = new ThreadLocal<Person>();

	public static void main(String[] args) {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
			return 1;
		});
		Thread thread = new Thread(futureTask);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(tl.get());
			}
		}).start();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					tl.set(new Person());
				}
			}).start();
		}
	}
}

class Person {
	String name = "zhangsan";

}
