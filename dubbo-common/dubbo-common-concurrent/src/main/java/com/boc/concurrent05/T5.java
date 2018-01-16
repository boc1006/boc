package com.boc.concurrent05;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 * <p>@Title: T5.java 
 * <p>@Package com.boc.concurrent05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月2日 下午5:13:13 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class T5 {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		service.scheduleAtFixedRate(()->{//以某种固定频率执行某项任务
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				System.out.println(Thread.currentThread().getName());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}, 0,1000, TimeUnit.MILLISECONDS);
	}
}
