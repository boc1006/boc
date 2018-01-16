package com.boc.dao.acid;

import java.util.List;

import org.dubbo.acid.entity.AnalysisAnonymousEntity;
import org.dubbo.acid.entity.MessageSubEntity;
import org.dubbo.acid.entity.UserEntity;
import org.dubbo.acid.entity.UserLogsEntity;

import com.boc.common.page.Page;

public interface UserDao {
	void registryUser(UserEntity user);
	
	void insertUserLogs(UserLogsEntity userLogs);

	int hasThisUser(String username);

	void insertSubForBatch(List<MessageSubEntity> list);

	List<AnalysisAnonymousEntity> queryAnalysislistPageData(Page page);
}
