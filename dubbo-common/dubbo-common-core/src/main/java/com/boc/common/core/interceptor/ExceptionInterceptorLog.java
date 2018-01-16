package com.boc.common.core.interceptor;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

import com.boc.common.exception.SDKException;


/**
 * 业务异常拦截器
 * <p>@Title ExceptionInterceptorLog</p>
 * <p>@Description com.boc.common.core.interceptor</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月22日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class ExceptionInterceptorLog implements ThrowsAdvice {

	private static final Log log = LogFactory.getLog(ExceptionInterceptorLog.class);

	/**
	 * 对未知异常的处理. <br>
	 * Method method 执行的方法 Object[] args <br>
	 * 方法参数 Object target <br>
	 * 代理的目标对象 Throwable BizException 产生的异常 <br>
	 */
	public void afterThrowing(Method method, Object[] args, Object target, SDKException ex) {

		log.info("==>ExceptionInterceptorLog.BizException");
		log.info("==>errCode:" + ex.getCode() + " errMsg:" + ex.getMsg());
		log.info("==>" + ex.fillInStackTrace());
	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

		log.error("==>ExceptionInterceptorLog.Exception");

		log.error("==>Error class: " + target.getClass().getName());
		log.error("==>Error method: " + method.getName());

		for (int i = 0; i < args.length; i++) {
			log.error("==>args[" + i + "]: " + args[i]);
		}

		log.error("==>Exception class: " + ex.getClass().getName());
		log.error("==>" + ex.fillInStackTrace());
		ex.printStackTrace();
	}

}
