package com.boc.concurrent02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、ReentrantLock手工锁:需要在代码中手动加锁和释放锁。
 * 2、ReentrantLock比synchronized更灵活，在性能上是一样的，功能一样，可以替代synchronized
 * 3、ReentrantLock是公平锁,谁等的时间长,谁先得到被释放的锁。
 * ReentrantLock(boolean fair);//fair  true if this lock should use a fair ordering policy
 * 对 synchronized 的改进。如此看来同步相当好了，是么？那么为什么 JSR 166 小组花了这么多时间来开发 java.util.concurrent.lock 框架呢？
 * 答案很简单－同步是不错，但它并不完美。它有一些功能性的限制 —— 它无法中断一个正在等候获得锁的线程，也无法通过投票得到锁，如果不想等下去，也就没法得到锁。
 * 同步还要求锁的释放只能在与获得锁所在的堆栈帧相同的堆栈帧中进行，多数情况下，这没问题（而且与异常处理交互得很好），但是，确实存在一些非块结构的锁定更合适的情况。
 * java.util.concurrent.lock 中的 Lock 框架是锁定的一个抽象，它允许把锁定的实现作为 Java 类，而不是作为语言的特性来实现。
 * 这就为 Lock 的多种实现留下了空间，各种实现可能有不同的调度算法、性能特性或者锁定语义。 ReentrantLock 类实现了 Lock ，
 * 它拥有与 synchronized 相同的并发性和内存语义，但是添加了类似锁投票、定时锁等候和可中断锁等候的一些特性。
 * 此外，它还提供了在激烈争用情况下更佳的性能。（换句话说，当许多线程都想访问共享资源时，JVM 可以花更少的时候来调度线程，把更多时间用在执行线程上。）
 * reentrant 锁意味着什么呢？简单来说，它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放。
 * 这模仿了 synchronized 的语义；如果线程进入由线程已经拥有的监控器保护的 synchronized 块，就允许线程继续进行，
 * 当线程退出第二个（或者后续） synchronized 块的时候，不释放锁，只有线程退出它进入的监控器保护的第一个 synchronized 块时，才释放锁.
 * <p>@Title: T12.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午1:33:59 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T12 {
	Lock lock = new ReentrantLock();

	void a1() {
		try {
			lock.lock();// 加锁,lock()是不能被其它线程打断的,类似于synchronized(this)
			for (int i = 0; i < 10; i++) {
				Thread.sleep(1000);
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();// 释放锁
		}
	}

	void a2() {
		lock.lock();// 加锁
		System.out.println("a2....");
		lock.unlock();// 释放锁
	}

	void a3() {
		boolean isLock = lock.tryLock();// 尝试加锁
		System.out.println("a2...." + isLock);
		if (isLock){ // 如果加锁成功，则需要释放锁
			lock.unlock();// 释放锁
		}
	}

	void a4() {
		boolean isLock;
		try {
			isLock = lock.tryLock(5, TimeUnit.SECONDS);// 尝试5秒钟加锁
			System.out.println("a2...." + isLock);
			if (isLock) {// 如果加锁成功，则需要释放锁
				lock.unlock();// 释放锁
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void a5() {
		try {
			System.out.println("加锁,但是可以被中断!");
			lock.lockInterruptibly();// lockInterruptibly是可以被其它线程打断
			System.out.println("完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("线程中断.....");
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final T12 t = new T12();
		new Thread(new Runnable() {

			@Override
			public void run() {
				t.a1();
			}
		}).start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				t.a4();
			}
		});
		t2.start();

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		t2.interrupt();// 中断t2线程
	}
}
