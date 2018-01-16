package com.boc.agent;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

/**
 * JAVA AGENT
 * <p>@Title: Test.java 
 * <p>@Package com.boc.agent 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2018年1月13日 上午9:33:53 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class Test {
	public static void premain(String agentOps, Instrumentation inst) {
		System.out.println(agentOps);
	}
	public static void main(String[] args) {
		String key = "222";
		int h;
        int hashKey =  (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hashKey);
	}
}
