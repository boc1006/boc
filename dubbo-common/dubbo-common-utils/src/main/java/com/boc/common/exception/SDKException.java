/**
 * 
 */
package com.boc.common.exception;

/**
 * <p>@Title SDKException </p>
 * <p>@Description 异常处理基类,所有自定义异常不直接继承该类</p>
 * <p>@Version 1.0.0</p>
 * <p>@author huangjie</p>
 * <p>@date 2016年11月10日</p>
 * <p>@mail huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved.</p>
 */
public class SDKException extends RuntimeException{

	private static final long serialVersionUID = 25779105158913270L;
	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;
	
	protected SDKException() {
		super();
	}

	protected SDKException(String message, Throwable cause) {
		super(message, cause);
	}

	protected SDKException(Throwable cause) {
		super(cause);
	}

	protected SDKException(String message) {
		super(message);
		this.msg = message;
	}
	protected SDKException(int code) {
		super();
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}
}
