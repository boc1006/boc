/**
 * 
 */
package com.boc.common.exception;

/**
 * <p>@Title 是业务系统异常处理基类 </p>
 * <p>@Description 这类异常一般需要捕捉并记录log，比如数据库的主键冲突、sql语句错误等</p>
 * <p>@Version 1.0.0</p>
 * <p>@author huangjie</p>
 * <p>@date 2016年11月10日</p>
 * <p>@mail huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved.</p>
 */
public class BizSystemException extends SDKException {

	private static final long serialVersionUID = 6213417832954546727L;
	/**
	 * 默认BizException 对象
	 */
	public static final BizSystemException INSTANCE = new BizSystemException(99990000,"%s");
	
	/**
	 * 数据库操作,insert返回0
	 */
	public static final BizSystemException DB_INSERT_RESULT_0 = new BizSystemException(99990001, "数据库操作,insert返回0,SQL==>【%s】");

	/**
	 * 数据库操作,update返回0
	 */
	public static final BizSystemException DB_UPDATE_RESULT_0 = new BizSystemException(99990002, "数据库操作,update返回0,SQL==>【%s】");

	/**
	 * 数据库操作,selectOne返回null
	 */
	public static final BizSystemException DB_SELECTONE_IS_NULL = new BizSystemException(99990003, "数据库操作,selectOne返回null,SQL==>【%s】");

	/**
	 * 数据库操作,list返回null
	 */
	public static final BizSystemException DB_LIST_IS_NULL = new BizSystemException(99990004, "数据库操作,list返回null,SQL==>【%s】");
	
	/**
	 * 获取序列出错
	 */
	public static final BizSystemException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizSystemException(99990005, "获取序列出现错误!序列名称==>【%s】");
	
	protected BizSystemException(int code, String msgFormat, Object args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	protected BizSystemException() {
		super();
	}

	public BizSystemException newInstance(Object args) {
		return new BizSystemException(this.code, this.msg, args);
	}

	protected BizSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	protected BizSystemException(Throwable cause) {
		super(cause);
	}

	public BizSystemException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	protected BizSystemException(int code) {
		super();
		this.code = code;
	}

}
