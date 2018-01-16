package com.boc.concurrent05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 并行计算 1-200000之间有多少质数
 * <p>@Title: T3.java 
 * <p>@Package com.boc.concurrent05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 下午4:38:36 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T3 {
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
//		List<Integer> results  = getPrime(1,200000);
		long end = System.currentTimeMillis()-start;
//		System.out.println(end);
//		System.out.println(results.size());
		
		final int cpuCoreNum = 4;
		ExecutorService es = Executors.newFixedThreadPool(cpuCoreNum);//固定个数的线程池
		
		MyTask t1 = new MyTask(1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);
		
		Future<List<Integer>> f1 = es.submit(t1);
		Future<List<Integer>> f2 = es.submit(t2);
		Future<List<Integer>> f3 = es.submit(t3);
		Future<List<Integer>> f4 = es.submit(t4);
		
		start = System.currentTimeMillis();
		
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		
		end = System.currentTimeMillis();
		System.out.println(end-start);
		es.shutdown();
	}
	
	static class MyTask implements Callable<List<Integer>>{
		int startPos,endPos; 
		
		public MyTask(int s,int e) {
			this.startPos = s;
			this.endPos = e;
		}

		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos,endPos);
			return r;
		}
		
	}
	
	static boolean isPrime(int num) {
		for(int i = 2 ; i <=num/2; i ++) {
			if(num%i == 0)return false;
		}
		return true;
	}
	
	static List<Integer> getPrime(int start,int end) {
		List<Integer> results = new ArrayList<>();
		for(int i = start ; i <= end ; i ++) {
			if(isPrime(i))results.add(i);
		}
		return results;
	}
}
