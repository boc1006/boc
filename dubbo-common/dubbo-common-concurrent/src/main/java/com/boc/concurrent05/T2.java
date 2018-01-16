package com.boc.concurrent05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T2 {
	public static void main(String[] args) throws Exception{
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.SECONDS.sleep(2);
			return 1000;
		});
		new Thread(task).start();
		System.out.println(task.get());//是一个阻塞方法,直到任务完成后得到返回值
		
		ExecutorService es = Executors.newFixedThreadPool(5);
		Future<Integer> fu = es.submit(()->{
			TimeUnit.SECONDS.sleep(2);
			return 3000;
		});
		
		System.out.println(fu.isDone());
		System.out.println(fu.get());
		es.shutdown();
	}
}
