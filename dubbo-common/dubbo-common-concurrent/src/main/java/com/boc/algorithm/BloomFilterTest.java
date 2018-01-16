package com.boc.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;


/**
 * 布隆过滤器算法
 * <p>@Title: BloomFilter.java 
 * <p>@Package com.boc.algorithm 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2018年1月14日 下午5:46:00 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class BloomFilterTest {
	private static final int insertions = 1000000;
	public static void main(String[] args) {
		BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions,0.001);
		Set<String> sets = new HashSet<String>(insertions);
		List<String> lists = new ArrayList<String>(insertions);
		for(int i = 0 ; i < insertions ; i ++) {
			String uuid = UUID.randomUUID().toString();
			bf.put(uuid);
			sets.add(uuid);
			lists.add(uuid);
		}
		
		int wrong = 0;//布隆过滤器错误判断的次数
		int right = 0;//布隆过滤器正确判断的次数
		for (int i = 0 ; i < 10000 ; i ++) {
			String test = i % 100 == 0?lists.get(i/100):UUID.randomUUID().toString();
			if(bf.mightContain(test)) {
				if(sets.contains(test)) {
					right ++;
				}else {
					wrong ++;
				}
			}
		}
		
		System.out.println("right="+right+"\twrong="+wrong);
	}
	
	@PostConstruct
	private void init() {
		System.out.println("加载init()");
	}
}
