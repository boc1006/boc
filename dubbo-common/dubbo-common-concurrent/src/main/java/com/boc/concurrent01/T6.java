package com.boc.concurrent01;

/**
 * volatile是指线程之间的可见性.如果内存变量加了volatile修饰符，则当一个线程修改了该变量，
 * 会立即刷新主内存数据并通知其它线程，强制让其它线程去主内存中读取新的值；否则不会通知其它线程，
 * 只有当其它线程去主内存中主动获取该内存值的时候，才能更新到工作内存中去使用，如果当其它线程CPU无空闲时，
 * 是不会去主内存中获取内存数据的，此时是永远不会获取到该内存被改变后的数据。
 * 但是volatile只保证了线程之间可见性，并未保证数据的原子性，也就是说，当一个内存数据被一个线程修改之前，
 * 其它线程会立即获取到修改后的数据（这时所有线程中该变量值完全一致），但是由于内存变更的操作都在各自线程的工作内存中，
 * 所以volatile并不能保证主内存中的变量被顺序修改。synchronized即保证可见性，又保证原子性；而volatile只保证可见性，不保证原子性
 * <p>@Title: T6.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午9:05:31 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T6 {
	/*volatile*/ boolean running = true;
	void m() {
		System.out.println("m start");
		while(running) {
			try {
//				Thread.sleep(1);
//				System.out.println("99999999999999999999");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("m stop");
	}
	public static void main(String[] args) {
		final T6 t = new T6();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}).start();
		
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		t.running = false;
		System.out.println("running="+t.running);
	}
}
