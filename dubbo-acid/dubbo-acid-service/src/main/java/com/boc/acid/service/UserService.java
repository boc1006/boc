package com.boc.acid.service;

import java.util.List;

import javax.annotation.Resource;

import org.dubbo.acid.entity.AnalysisAnonymousEntity;
import org.dubbo.acid.facade.UserServiceFacade;
import org.springframework.stereotype.Service;

import com.boc.acid.biz.UserBiz;
import com.boc.common.exception.BizException;
import com.boc.common.exception.SDKException;
import com.boc.common.page.BizPageResponse;
import com.boc.common.page.Page;


@Service("userServiceFacade")
public class UserService implements UserServiceFacade{

	@Resource
	private UserBiz userBiz;
	
	@Override
	public void registryUser(String username, int age) throws BizException {
		userBiz.registryUser(username, age);
	}

	@Override
	public void insertSubForBatch() throws Exception {
		userBiz.insertSubForBatch();
	}

	@Override
	public BizPageResponse<List<AnalysisAnonymousEntity>> queryAnalysislistPageData(Page page) throws SDKException {
		return userBiz.queryAnalysislistPageData(page);
	}
}
