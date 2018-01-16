package com.boc.aop.cglib.proxy;

/**
 * CGLib代理类子类
 * <p>@Title flinkquickstart
 * <p>@Author boc
 * <p>@Date 2018/1/8
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public class StudentsImpl extends Students{
	@Override
	public void saveStudents() {
		System.out.println("保存学员信息");
	}
}
