package com.boc.aop.cglib.proxy;

/**
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
		Students students = new StudentsImpl();
		CGLibProxy proxy= new CGLibProxy();
		Students bind = (Students) proxy.bind(students);
		bind.saveStudents();
	}
}
