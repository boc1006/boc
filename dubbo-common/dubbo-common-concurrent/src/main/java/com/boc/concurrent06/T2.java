package com.boc.concurrent06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T2 {
	final static int len = 10;
	List<String> list = new ArrayList<String>();
	static ReentrantLock lock = new ReentrantLock();
	static CountDownLatch cdl = new CountDownLatch(len);
	public static void main(String[] args) throws InterruptedException {
		T2 t = new T2();
		Thread threads[] = new Thread[len];
		for(int i = 0 ; i < len ; i ++) {
			threads[i] = new Thread(()->{
				try {
					t.init();
				} catch (InterruptedException e) {
//					e.printStackTrace();
					System.out.println(Thread.currentThread().getName()+"被中断!");
				} finally {
					cdl.countDown();
				}
			});
			threads[i].start();
		}
		TimeUnit.MILLISECONDS.sleep(1);
		for(int i = 0 ; i < len; i ++) {
			if(lock.hasQueuedThread(threads[i])) {
				threads[i].interrupt();
			}
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.list.forEach(item -> {
			System.out.println(item);
		});
	}

	private void init() throws InterruptedException {
		lock.lockInterruptibly();
		System.out.println(Thread.currentThread().getName()+"-"+lock.isHeldByCurrentThread());
		try {
			try {
				list.add(Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			lock.unlock();
		}
	}
}
