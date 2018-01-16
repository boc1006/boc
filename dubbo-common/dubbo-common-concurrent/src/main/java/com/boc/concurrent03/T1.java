package com.boc.concurrent03;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个同步容器，提供put,get
 * <p>@Title: T1.java 
 * <p>@Package com.boc.concurrent03 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午5:05:51 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T1 <T>{
	private final LinkedList<T> list = new LinkedList<T>();
	private final static int max = 5;
	private int count = 0;

	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();

	public void put(T t) {
		try {
			lock.lock();
			System.out.println("获得锁...");
			while (list.size() == max) {
				producer.await();
				System.out.println("提供者醒了..");
			}
			list.add(t);
			++count;
			consumer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public T get() {
		T t = null;
		try {
			lock.lock();
			while (list.size() == 0) {
				consumer.await();
			}
			t = list.removeFirst();
			count--;
			producer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args) {
		final T1<String> t = new T1<String>();
		for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int k = 0; k < 10; k++) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(t.get());
					}
				}
			}, "getThread").start();
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int k = 0; k < 10; k++) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						t.put(Thread.currentThread().getName() + ":" + k);
						System.out.println("输入线程==>" + Thread.currentThread().getName() + ":" + k);
					}
				}
			}, "putThread" + i).start();
		}
	}
}
