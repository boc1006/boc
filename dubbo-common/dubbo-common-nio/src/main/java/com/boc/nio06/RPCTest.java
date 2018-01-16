package com.boc.nio06;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RPCTest {
	public static void main(String[] args) {
		RPCClient client = RPCClient.getInstance();
		client.init("127.0.01");
		SayHello sayHello = (SayHello) client.getRemoteProxy(SayHello.class);
//		System.out.println("client:" + sayHello.sayHello());
//		sayHello.sayHi("i am come from client");
//		System.out.println("1 + 4 = " + sayHello.sum(1, 4));
//		System.out.println("23 * 56 =" + sayHello.test2(23L, 56L));
		while(true) {
			int x = new Random().nextInt(100);
			int y = new Random().nextInt(100);
//			int sum = sayHello.sum(x, y);
			System.out.println(sayHello.sum(x, y)==x+y);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
