package com.boc.acid.biz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dubbo.acid.entity.AnalysisAnonymousEntity;
import org.dubbo.acid.entity.MessageSubEntity;
import org.dubbo.acid.entity.UserEntity;
import org.dubbo.acid.entity.UserLogsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boc.common.core.lock.CuratorLock;
import com.boc.common.exception.BizException;
import com.boc.common.exception.SDKException;
import com.boc.common.page.BizPageResponse;
import com.boc.common.page.Page;
import com.boc.dao.acid.UserDao;

@Service
public class UserBiz {
	@Resource
	private UserDao userdao;

//	@CuratorLock(id = 0, keepMills = 0, toWait = false)
	@Transactional
	public void registryUser(String username, int age) {
		UserLogsEntity logs = new UserLogsEntity();
		logs.setUserid(System.currentTimeMillis());
		logs.setUsername(getHostName() + "_" + username);
		logs.setAge(age);
		saveLog(logs);
		int count = userdao.hasThisUser(username);
		if (count == 0) {
			UserEntity user = new UserEntity();
			user.setUserid(System.currentTimeMillis());
			user.setUsername(username);
			user.setAge(age);
			userdao.registryUser(user);
		}
		throw BizException.INSTANCE.newInstance("测试事务传播异常....");
	}
	
	@Transactional
	public void saveLog(UserLogsEntity logs){
		userdao.insertUserLogs(logs);
	}

	public void insertSubForBatch() throws Exception {
		List<MessageSubEntity> list = new ArrayList<MessageSubEntity>();
		for (int i = 0; i < 10; i++) {
			MessageSubEntity sub = new MessageSubEntity();
			sub.setContents("json 内容：" + i);
			list.add(sub);
		}
		userdao.insertSubForBatch(list);
		for (MessageSubEntity sub : list) {
			System.out.println("id=" + sub.getId() + "\tcontents=" + sub.getContents());
		}
	}

	public BizPageResponse<List<AnalysisAnonymousEntity>> queryAnalysislistPageData(Page page) throws SDKException {
		BizPageResponse bizPage = new BizPageResponse();
		List<AnalysisAnonymousEntity> list = userdao.queryAnalysislistPageData(page);
		bizPage.setPage(page);
		bizPage.setObj(list);
		bizPage.setSuccess(true);
		return bizPage;
	}

	public static String getHostNameForLiunx() {
		try {
			return (InetAddress.getLocalHost()).getHostName();
		} catch (UnknownHostException uhe) {
			String host = uhe.getMessage(); // host = "hostname: hostname"
			if (host != null) {
				int colon = host.indexOf(':');
				if (colon > 0) {
					return host.substring(0, colon);
				}
			}
			return "UnknownHost";
		}
	}

	public static String getHostName() {
		if (System.getenv("COMPUTERNAME") != null) {
			return System.getenv("COMPUTERNAME");
		} else {
			return getHostNameForLiunx();
		}
	}
}
