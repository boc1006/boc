package com.boc.concurrent01;

/**
 * 一个同步方法可以调用该锁对象的另一个同步方法,一个对象已经拥有某个对象的锁,再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized 获得的锁是可以重入的,也称重入锁,只是在该锁对象的锁标识上加1
 * 父类的同步方法也同样实用于重入锁策略
 * <p>@Title: T4.java 
 * <p>@Package com.boc.concurrent01 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月26日 下午8:43:06 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T4 {
	synchronized void m1() {
		System.out.println("m1");
		m2();
	}
	synchronized void m2() {
		System.out.println("m2");
	}
	public static void main(String[] args) {
		T4 t4 = new T4();
		t4.m1();
	}
}
