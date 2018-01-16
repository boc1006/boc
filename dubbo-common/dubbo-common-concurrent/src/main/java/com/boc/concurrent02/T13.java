package com.boc.concurrent02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁是只等待时间长的可优先获得锁
 * <p>@Title: T13.java 
 * <p>@Package com.boc.concurrent02 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午4:12:34 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T13 extends Thread{
	private static Lock lock = new ReentrantLock(true);//参数为true表示公平锁,对比参数为false的输出结果,公平锁效率没非公平锁高
	@Override
	public void run() {
		for(int i = 0 ;i < 100 ;i ++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"获得锁!");
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		T13 t= new T13();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(t);
		t1.start();
		t2.start();
		t3.start();
	}
}
