package com.boc.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * <p>@Title flinkquickstart
 * <p>@Author boc
 * <p>@Date 2018/1/7
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public class Compments implements InvocationHandler{

	private Object target;//真正被代理的对象

	public Compments(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Do SomeThing Before");
		Object invoke = method.invoke(target, args);
		System.out.println("Do SomeThing End");
		return invoke;
	}

	/**
	 * 通过Proxy获取真正被代理的对象
	 * @return
	 */
	public Object getProxy() {

		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),this);
	}
}
