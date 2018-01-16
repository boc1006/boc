package com.boc.concurrent06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1 {
	final static int len = 10;
	List<String> list = new ArrayList<String>();
	Lock lock = new ReentrantLock(true);
	static ExecutorService es = Executors.newFixedThreadPool(2);
	static CountDownLatch cdl = new CountDownLatch(len);
	public static void main(String[] args) {
		T1 t = new T1();
		for(int i = 0 ;  i< len ;i ++)
		es.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					t.init();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cdl.countDown();
			}
		});
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.list.forEach(item->{
			System.out.println(item);
		});
		es.shutdownNow();
	}
	
	private void init() throws InterruptedException {
//		lock.lock();
		if(lock.tryLock(10000000,TimeUnit.NANOSECONDS))
		try {
			TimeUnit.NANOSECONDS.sleep(1);
			list.add(Thread.currentThread().getName());
		} finally {
			lock.unlock();
		}
	}
}
