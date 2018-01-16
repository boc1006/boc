package com.boc.concurrent02;

/**
 * wait 会释放锁，而notify不会释放锁。
 * notify() notifyAll() wait() 必须在 同步方法中（实现线程安全即可）调用，否则抛 Java.lang.IllegalMonitorStateException
 * <p>@Title: T10.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 上午10:46:49 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T10 {
	int i = 0;
	void set() {
		i++;
		System.out.println("i="+i);
	}
	int get(){
		return i;
	}
	public static void main(String[] args) {
		final T10 t = new T10();
		final Object o = new Object();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (o) {
					System.out.println("t1线程启动");
					while(t.get() != 5) {
						try {
							System.out.println("====>");
							o.wait();//第一步：使该线程进入等待状态。并释放锁,只有被锁定的对象才能使用wait()方法.
							System.out.println("---->");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					o.notify();//第四步：该线程结束之前通知处于等待状态的线程，并且结束当前线程来释放锁。
					System.out.println("t1线程结束...");
				}
			}
		},"t1").start();
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					synchronized (o) {
						for(int i = 0 ; i < 10 ; i ++){
							t.set();
							if(t.get() == 5) {
								o.notify();//第二步：notify()会通知该被锁住的对象且处于等待状态的线程,但是由于notify()不会释放锁，被通知的对象由于不能获取到锁而仍然不会被执行。notifyAll()通知该被锁住的对象处于所有线程
								o.wait();//第三步：使用o.wait()方法释放锁，该其它线程得到该锁。但此时当前线程处理等待状态，不能继续执行。
							}
							Thread.sleep(100);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("t2线程结束!");
			}
		}).start();
	}
}
