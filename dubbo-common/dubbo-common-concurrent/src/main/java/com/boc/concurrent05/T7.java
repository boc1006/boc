package com.boc.concurrent05;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin 任务拆分执行,并合并结果,先分拆再合并
 * <p>@Title: T7.java 
 * <p>@Package com.boc.concurrent05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 下午6:00:21 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T7 {
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		long start = System.currentTimeMillis();
		System.out.println(Arrays.stream(nums).sum());
		System.out.println(System.currentTimeMillis()-start);
	}

	/*
	static class AddTask extends RecursiveAction {
		int start, end;

		public AddTask(int s, int e) {
			this.start = s;
			this.end = e;
		}

		@Override
		protected void compute() {
			if (end - start <= MAX_NUM) {
				long sum = 0l;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from:" + start + " to:" + end + "=" + sum);
			} else {
				int middle = start + (end - start) / 2;
				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}
		}

	}
	*/
	
	static class AddTask extends RecursiveTask<Long>{
		int start, end;

		public AddTask(int s, int e) {
			this.start = s;
			this.end = e;
		}
		@Override
		protected Long compute() {
			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
//				System.out.println("from:" + start + " to:" + end + "=" + sum);
				return sum;
			} 
			int middle = start + (end - start) / 2;
			AddTask subTask1 = new AddTask(start, middle);
			AddTask subTask2 = new AddTask(middle, end);
			subTask1.fork();
			subTask2.fork();
			return subTask1.join()+subTask2.join();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		long start = System.currentTimeMillis();
		fjp.execute(task);
		long result =task.join();
		System.out.println(result);
		System.out.println(System.currentTimeMillis()-start);
		System.in.read();
	}
}
