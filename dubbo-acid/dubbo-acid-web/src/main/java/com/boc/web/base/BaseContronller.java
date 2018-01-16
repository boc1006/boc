package com.boc.web.base;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.Page;

public class BaseContronller {
	protected DTO getParamAsDTO() {
		DTO dto = new BaseDTO();
		Enumeration<String> paramNames = getRequest().getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String paramValues = this.getRequest().getParameter(paramName);
			dto.put(paramName, paramValues);
		}
		return dto;
	}
	
	protected Page getPage(DTO dto) {
		Page p = new Page();
		p.setPageDTO(dto);
		return p;
	}
	
	/**
	 * 得到request对象
	 *
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	protected HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}
}
