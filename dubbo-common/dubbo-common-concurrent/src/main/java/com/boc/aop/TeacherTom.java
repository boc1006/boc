package com.boc.aop;

/**
 * 具体的代理对象
 * <p>@Title flinkquickstart
 * <p>@Author boc
 * <p>@Date 2018/1/7
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public class TeacherTom implements Girl{

	@Override
	public boolean dating(int height) {
		if(height > 170) {
			System.out.println("可以约会!");
			return true;
		}
		System.out.println("高度不够,拒绝约会!");
		return false;
	}
}
