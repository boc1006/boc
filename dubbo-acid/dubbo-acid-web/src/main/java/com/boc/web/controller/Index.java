package com.boc.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dubbo.acid.facade.UserServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boc.common.page.Page;
import com.boc.web.base.BaseContronller;

@Controller
@RequestMapping(value="/nio")
public class Index extends BaseContronller{
	@Resource
	private UserServiceFacade userServiceFacade;
	
	@ResponseBody
	@RequestMapping(value="/index.do")
	public Object index(Model model,HttpServletRequest request) {
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		System.out.println(username);
		System.out.println(age);
		System.out.println("userServiceFacade========"+userServiceFacade);
		userServiceFacade.registryUser(username, Integer.valueOf(age));
		return Thread.currentThread().getName();
	}
	

	@ResponseBody
	@RequestMapping(value="/logListPage.do")
	public Object queryAnalysislistPageData() {
		Page p = getPage(getParamAsDTO());
		return userServiceFacade.queryAnalysislistPageData(p);
	}
	
}
