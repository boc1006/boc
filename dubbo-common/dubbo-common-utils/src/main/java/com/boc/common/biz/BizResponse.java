package com.boc.common.biz;

import java.io.Serializable;

/**
 * 业务操作返回参数
 * @author user
 *
 */
public class BizResponse<T> implements Serializable {

	private static final long serialVersionUID = -3920581623450148208L;

	private boolean success = true;
	private String errMsg;
	private T obj;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setError(String errMsg) {
		this.success = false;
		this.errMsg = errMsg;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
}
