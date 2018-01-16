package com.boc.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * <p>@Title flinkquickstart
 * <p>@Author boc
 * <p>@Date 2018/1/7
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public class Test {
	public static void main(String[] args) {
//		ProxyMan proxyMan = new ProxyMan(new TeacherTom());
//		proxyMan.dating(250);
//		Proxy p = null;
//		InvocationHandler invocationHandler = null;
		Compments c = new Compments(new TeacherTom());
		Girl girl = (Girl) c.getProxy();
		girl.dating(120);
	}
}
