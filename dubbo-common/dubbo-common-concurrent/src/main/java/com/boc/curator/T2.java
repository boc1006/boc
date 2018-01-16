package com.boc.curator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>@Title: T2.java 
 * <p>@Package com.boc.curator 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月29日 下午1:46:41 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T2 {
	/** zookeeper地址 */
	static final String CONNECT_ADDR = "localhost:2181";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 5000;

	static int count = 10;

	public static void genarNo() {
		try {
			count--;
			TimeUnit.SECONDS.sleep(2);
			System.out.println(Thread.currentThread().getName()+"\t"+count);
		} catch(Exception e){
			
		}finally {

		}
	}

	public static void main(String[] args) throws Exception {

		// 1 重试策略：初试时间为1s 重试10次
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		// 2 通过工厂创建连接
		CuratorFramework cf = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
				.sessionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy)
				// .namespace("super")
				.build();
		// 3 开启连接
		cf.start();

		// 4 分布式锁
		final InterProcessMutex lock = new InterProcessMutex(cf, "/super");
		final CountDownLatch countdown = new CountDownLatch(1);
		final CountDownLatch over = new CountDownLatch(10);

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						countdown.await();
						TimeUnit.SECONDS.sleep(2);
						// 加锁
						lock.acquire();
						// -------------业务处理开始
						genarNo();
						// -------------业务处理结束
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							// 释放
							lock.release();
						} catch (Exception e) {
							e.printStackTrace();
						}
						over.countDown();
					}
				}
			}, "t2_" + i).start();
		}
		System.out.println("准备中...");
		Thread.sleep(2000);
		countdown.countDown();
		System.out.println("执行中...");
		over.await();
//		System.out.println("关闭连接...");
		System.out.println("执行完成...");
//		cf.close();
		try {
			TimeUnit.SECONDS.sleep(50);
			System.out.println("结束进程....");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
