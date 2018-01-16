package com.boc.test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();
		clq.add("a");
		clq.add("b");
		clq.add("c");
		clq.add("d");

		System.out.println(clq.poll());
		System.out.println(clq.poll());
		System.out.println(clq.poll());
		System.out.println(clq.poll());
		System.out.println(clq.poll());
		System.out.println(clq.size());
		System.out.println(clq.peek());
		System.out.println(clq.size());
	}
}
