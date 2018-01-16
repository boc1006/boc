package com.boc.concurrent04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class T3 {
	private final static int len = 100;
	public static void main(String[] args) {
//		Map<String,String> map = new HashMap<>();//1000
//		Map<String,String> map = new Hashtable<>();//1400
		Map<String,String> map = Collections.synchronizedMap(new HashMap<>());//1200
//		Map<String,String> map = new ConcurrentHashMap<>();//2300
//		ExecutorService ex = Executors.newFixedThreadPool(len);
		CountDownLatch cdl = new CountDownLatch(len);
		Random r = new Random();
		long start = System.currentTimeMillis();
		for(int i = 0 ; i < len ; i ++) {
			final int temp = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int k = 0 ; k < 5000 ; k ++) {
						map.put(temp+"_key_"+k, 
								temp+"_val_"+r.nextInt(100000));
					}
					cdl.countDown();
				}
			}).start();
		}
		try {
			cdl.await();
			System.out.println(System.currentTimeMillis()-start);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
