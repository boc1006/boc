package com.boc.concurrent05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T1 {
	public static void main(String[] args) throws Exception{
		ExecutorService es = Executors.newFixedThreadPool(5);
		for(int i = 0 ; i < 11 ;i ++) {
			es.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
						System.out.println(Thread.currentThread().getName());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}
		System.out.println(es);//[Running, pool size = 5, active threads = 5, queued tasks = 6, completed tasks = 0]
		System.out.println(es.isTerminated());
		System.out.println(es.isShutdown());
//		es.shutdown();//正常关闭,直到所有任务执行完成
		es.shutdownNow();//强制关闭,立即中断所有未完成 的任务
		System.out.println(es);
		es.awaitTermination(2000, TimeUnit.MILLISECONDS);
		System.out.println(es);
	}
}
