package com.boc.aop;

/**
 * 代理人
 * <p>@Title flinkquickstart
 * <p>@Author boc
 * <p>@Date 2018/1/7
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public class ProxyMan implements Girl{
	 private Girl girl;

	 public ProxyMan(Girl girl) {
	 	this.girl=girl;
	 }


	@Override
	public boolean dating(int height) {
	 	System.out.println("Do something Before");
		boolean dating = girl.dating(height);
		System.out.println("Do something End");
		return dating;
	}
}
