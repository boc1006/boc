package com.boc.concurrent01;

import java.util.concurrent.TimeUnit;

/**
 * 胀读问题
 * <p>@Title: T3.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午8:41:02 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T3 {
	private String name;
	private double balance;
	
	public synchronized void setBalance(String name,double balance) {
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
		this.name = name;
	}
	
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}
	
	public static void main(String[] args) {
		T3 t2 = new T3();
		new Thread(()->{
			t2.setBalance("zhangsan", 100d);
		}) .start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(1);
			System.out.println("balance="+t2.getBalance("zhangsan"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
