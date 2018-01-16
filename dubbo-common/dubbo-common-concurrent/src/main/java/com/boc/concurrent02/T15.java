package com.boc.concurrent02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 两个构造方法DEMO
 * CyclicBarrier(int parties, Runnable barrierAction)
 * <p>@Title: T15.java 
 * <p>@Package com.boc.concurrent02 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午4:28:59 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T15 {
	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
			@Override
			public void run() {
				System.out.println("当前线程" + Thread.currentThread().getName());
			}
		});

		for (int i = 0; i < N; i++)
			new Writer(barrier).start();
	}

	static class Writer extends Thread {
		private CyclicBarrier cyclicBarrier;

		public Writer(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
			try {
				Thread.sleep(5000); // 以睡眠来模拟写入数据操作
				System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("所有线程写入完毕，继续处理其他任务...");
		}
	}
}
