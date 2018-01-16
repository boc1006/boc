package com.boc.common.page;

import java.io.Serializable;

import com.boc.common.biz.BizResponse;

/**
 * 分页返回结果集
 * @author user
 *
 * @param <T>
 */
public class BizPageResponse<T> extends BizResponse<T> implements Serializable {
	private static final long serialVersionUID = -4408151778025812996L;
	private Page page;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
