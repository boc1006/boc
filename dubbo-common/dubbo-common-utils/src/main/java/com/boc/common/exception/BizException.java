package com.boc.common.exception;

/**
 * <p>@Title 业务异常基类，所有业务异常都必须继承于此异常 </p>
 * <p>@Description 一般这类异常是不需要记录log，只是展示给页面显示并提示给用户。如你的用户名、密码为空等错误<br>
 * 	定义异常时，需要先确定异常所属模块。例如：数据库操作报错 可以定义为 [99980001] 前四位数为系统模块编号，后4位为错误代码 ,唯一 </p>
 * <p>@Version 1.0.0</p>
 * <p>@author huangjie</p>
 * <p>@date 2016年11月10日</p>
 * <p>@mail huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved.</p>
 */
public class BizException extends SDKException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 默认BizException 对象
	 */
	public static final BizException INSTANCE = new BizException(99980000,"%s");
	
	/**
	 * 业务操作中参数对象为空
	 */
	public static final BizException PARAM_IS_NULL = new BizException(99980001, "参数对象为空,参数名称【%s】");
	
	/**
	 * 业务操作中参数校验失败异常
	 */
	public static final BizException PARAM_VERIFY_FAIL = new BizException(99980002, "参数校验失败,参数名称【%s】");
	

	/**
	 * Token 验证不通过
	 */
	public static final BizException TOKEN_IS_ILLICIT = new BizException(99980003, "Token验证非法,【%s】");


	protected BizException(int code, String msgFormat, Object args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	protected BizException() {
		super();
	}

	public BizException newInstance(Object args) {
		return new BizException(this.code, this.msg, args);
	}

	protected BizException(String message, Throwable cause) {
		super(message, cause);
	}

	protected BizException(Throwable cause) {
		super(cause);
	}

	public BizException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	protected BizException(int code) {
		super();
		this.code = code;
	}
}
