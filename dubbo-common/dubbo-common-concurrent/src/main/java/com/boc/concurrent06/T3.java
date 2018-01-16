package com.boc.concurrent06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁分为读锁和写锁，多个读锁之间是不需要互斥的(读操作不会改变数据，如果上了锁，反而会影响效率)，写锁和写锁之间需要互斥，
 * 也就是说，如果只是读数据，就可以多个线程同时读，但是如果你要写数据，就必须互斥，使得同一时刻只有一个线程在操作。
 * <p>@Title: T3.java 
 * <p>@Package com.boc.concurrent06 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2018年1月15日 上午9:59:18 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T3 {
	static ReadWriteLock rwlock = new ReentrantReadWriteLock();
	static Lock readLock = rwlock.readLock();
	static Lock writeLock = rwlock.writeLock();
	List<String> list = new ArrayList<>();
	static ThreadLocal<AtomicInteger> tl = new ThreadLocal<>();
	public static void main(String[] args) {
		T3 t = new T3();
		new Thread(new Runnable() {

			@Override
			public void run() {
				tl.set(new AtomicInteger(0));
				t.get(Thread.currentThread());
				t.add();
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				tl.set(new AtomicInteger(0));
				t.add();
				t.get(Thread.currentThread());
			}
		}).start();
	}

	public void add() {
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 获取写锁:");
			for (int i = 0; i < 100; i++) {
				list.add(Thread.currentThread().getName() + "-" + i);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			writeLock.unlock();
		}
	}

	public void get(Thread thread) {
		readLock.lock();
		System.out.println(Thread.currentThread().getName() + " 获取读锁:");
		try {
			while (tl.get().incrementAndGet() < 100) {
				System.out.println(thread.getName() + "正在进行读操作size="+list.size());
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			System.out.println(thread.getName() + "读操作完毕");
		} finally {
			readLock.unlock();
		}
	}
}
