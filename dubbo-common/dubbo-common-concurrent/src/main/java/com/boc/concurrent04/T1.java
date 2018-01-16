package com.boc.concurrent04;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class T1 {
	
//	private static List<String> tickets =  Collections.synchronizedList(new ArrayList<String>());
	private static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	static {
		for(int i = 0 ; i<1000 ; i ++) {
			tickets.add("车票号："+i);
		}
	}
	/**
	 * 使用synchronized实现卖票并发问题
	 */
//	public static void main(String[] args) {
//		for(int i = 0 ; i < 10 ; i ++) {
//			new Thread(new Runnable() {
//				
//				@Override
//				public  void run() {
//					while(true) {
//						synchronized (tickets) {
//							if(tickets.size() ==0)break;
//							try {
//								TimeUnit.MILLISECONDS.sleep(1);
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//							System.out.println(Thread.currentThread().getName()+"\t销售中..."+tickets.remove(0));
//						}
//					}
//				}
//			}).start();
//		}
//	}
	
	public static void main(String []args) {
		
		for(int i = 0 ; i < 10 ;i ++) {
			new Thread(()->{
				while(true) {
					String s = tickets.poll();
					if(s == null) {
						break;
					}else {
						try {
							TimeUnit.MILLISECONDS.sleep(1);
						} catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println(Thread.currentThread().getName()+"\t销售中..."+s);
					}
				}
			}).start();
		}
	}
}
