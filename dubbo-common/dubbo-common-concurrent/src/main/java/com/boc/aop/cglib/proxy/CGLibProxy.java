package com.boc.aop.cglib.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib代理类
 * <p>@Title flinkquickstart
 * <p>@Author boc
 * <p>@Date 2018/1/8
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public class CGLibProxy implements MethodInterceptor {

	public Object bind(Object obj) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("CGLib代理方法执行之前");
		Object o1 = methodProxy.invokeSuper(obj, args);
		System.out.println("CGLib代理方法执行之后");
		return o1;
	}
}
