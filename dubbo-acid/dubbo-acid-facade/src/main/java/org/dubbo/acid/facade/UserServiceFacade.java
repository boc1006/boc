package org.dubbo.acid.facade;

import java.util.List;

import org.dubbo.acid.entity.AnalysisAnonymousEntity;

import com.boc.common.exception.BizException;
import com.boc.common.exception.SDKException;
import com.boc.common.page.BizPageResponse;
import com.boc.common.page.Page;

public interface UserServiceFacade {

	void registryUser(String username, int age) throws BizException;

	void insertSubForBatch() throws Exception;

	BizPageResponse<List<AnalysisAnonymousEntity>> queryAnalysislistPageData(Page page) throws SDKException;
}
