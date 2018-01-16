package org.dubbo.acid.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.dubbo.acid.facade.UserServiceFacade;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boc.common.exception.BizException;
import com.boc.common.exception.SDKException;
import com.boc.common.utils.httpclient.SimpleHttpUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-acid-consumer.xml")
public class Test2 {
	@Resource
	private UserServiceFacade userServiceFacade;
	
	@org.junit.Test
	public void registryUser() {
		System.out.println("请求发送时间："+System.currentTimeMillis());
//		for(int i = 0 ; i < 10 ; i ++) {
			try {
				userServiceFacade.registryUser("张三", 27);
			} catch (Exception e) {
				if(e instanceof BizException) {
					System.out.println("BizException.......................");
				}else if(e instanceof SDKException) {
					System.out.println("SDKException.......................");
				}else {
					System.out.println("RuntimeException.....................");
				}
				e.printStackTrace();
			}
//		}
		System.out.println("请求结束时间："+System.currentTimeMillis());
	}

	@org.junit.Test
	public void insertSubForBatch() {
		System.out.println("-----------");
		try {
			userServiceFacade.insertSubForBatch();
			userServiceFacade.insertSubForBatch();
			userServiceFacade.insertSubForBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("###########");
	}
	private final static String url="http://172.16.2.249:8080/dubbo-acid-web/nio/logListPage.do";
	public static void main(String[] args) {
		for(int i = 0 ;i < 500 ; i ++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						Map<String, String> param = new HashMap<String,String>();
						param.put("rows", String.valueOf(new Random().nextInt(100)+1));
						param.put("page", String.valueOf(new Random().nextInt(100)+1));
						SimpleHttpUtils.httpGet(url, param);
//						System.out.println(result);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		
	}
}
