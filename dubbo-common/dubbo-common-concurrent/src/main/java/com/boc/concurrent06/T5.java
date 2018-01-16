package com.boc.concurrent06;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T5 {
	public static void main(String[] args) {
		Integer array[] = new Integer[] {1,2,3,4,5,6,7,8};
		Integer newArray[] = new Integer[3];
		Integer a = 1;
		Integer b = 2;
		//TODO 实现代码逻辑
		if(a==b) {


		}
		System.arraycopy(array, 2, newArray, 0, 3);
		Arrays.asList(newArray).forEach(item->{
			System.out.println(item);
		});
		
		List<Integer> list = new ArrayList<Integer>();
		list.parallelStream().sorted().toArray();
	}
}
