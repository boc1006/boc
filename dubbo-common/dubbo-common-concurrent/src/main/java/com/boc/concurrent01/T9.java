package com.boc.concurrent01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T9 {
	volatile List<Object> list = new ArrayList<Object>();
	volatile int count = 0;

	public void add(Object o) {
		list.add(o);
	}
	
	public void add() {
		count ++;
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		T9 t = new T9();
		CountDownLatch cdl = new CountDownLatch(1);
		new Thread(() -> {
			cdl.countDown();
			while (true) {
				if (t.count == 5) {
					break;
				}
			}
		}).start();
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
//				t.add(new Object());
				t.add();
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}).start();
	}
}
