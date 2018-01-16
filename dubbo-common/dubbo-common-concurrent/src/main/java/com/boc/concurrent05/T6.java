package com.boc.concurrent05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 工作窃取线程池,默认根据机器的CPU内核来启动线程数,是精灵线程
 * 每个线程都维护自己的工作队列,当某个线程完成自己的队列时,会主动的去从其它线程中拿任务执行
 * <p>@Title: T6.java 
 * <p>@Package com.boc.concurrent05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 下午5:15:36 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T6 {
	public static void main(String[] args) throws Exception{
		ExecutorService es = Executors.newWorkStealingPool();
//		ExecutorService es = Executors.newCachedThreadPool();
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		es.execute(new R(1000));
		es.execute(new R(2000));
		es.execute(new R(2000));
		es.execute(new R(2000));
		es.execute(new R(2000));
		System.in.read();
	}
	
	static class R implements Runnable{
		
		int time;
		
		R(int time) {
			this.time = time;
		}

		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
				System.out.println(time+"\t"+Thread.currentThread().getName()+"\t"+System.currentTimeMillis());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
}
