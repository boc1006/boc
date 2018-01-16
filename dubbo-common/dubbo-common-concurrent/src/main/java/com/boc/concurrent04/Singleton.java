package com.boc.concurrent04;

/**
 * 通过内部类实现懒加载单例模式
 * <p>@Title: Singleton.java 
 * <p>@Package com.boc.concurrent04 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年9月27日 下午10:06:30 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class Singleton {
	private Singleton() {
		System.out.println("实例化...");
	}
	
	private static class Innter{
		private static Singleton s = new Singleton();
	}
	
	public static Singleton getSingleton() {
		return Innter.s;
	}
	
	public static void main(String[] args) {
		for(int i = 0 ;i < 1000;i++) {
			new Thread(Singleton::getSingleton).start();
		}
	}
}
