package com.boc.concurrent01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 只保证可见性,不保证原子性,synchronized 既保证可见性,又保证原子性
 * <p>@Title: T7.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午9:33:27 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T7 {
	volatile int count = 0;
	AtomicInteger ai = new AtomicInteger(0);
	void m() {
		for(int i = 0 ; i <10000 ; i ++) {
//			synchronized(this) {
//				count++;
			ai.incrementAndGet();
//			}
		}
	}
	
	public static void main(String[] args) {
		T7 t = new T7();
		List<Thread> list = new ArrayList<Thread>();
		for(int i = 0 ; i <10 ;i ++) {
			list.add(new Thread(t::m,"thread:"+i));
		}

		System.out.println(System.currentTimeMillis());
		list.forEach(o->o.start());
		list.forEach(o->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(System.currentTimeMillis());
//		
		System.out.println("count="+t.ai.get());
//		System.out.println("count="+t.count);
	}
}
