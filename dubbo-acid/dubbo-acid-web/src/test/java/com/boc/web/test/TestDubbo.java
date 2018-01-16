package com.boc.web.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestDubbo {
	private static String url1 = "http://172.16.2.249:8080/dubbo-acid-web/nio/index.do";
	private static String url2 = "http://172.16.2.250:8080/dubbo-acid-web/nio/index.do";
	private static String urls[] = new String[] {url1,url2};
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(150);
		CountDownLatch cdl = new CountDownLatch(1000);
		long start = System.currentTimeMillis();
		for(int i = 0 ; i < 1000 ;i ++) {
			es.submit(new Runnable() {
				
				@Override
				public void run() {
//					System.out.println(new Random().nextInt(2));
					System.out.println(sendGet(urls[new Random().nextInt(2)],"zhangsan_"+new Random().nextInt(100),27));
					cdl.countDown();
				}
			});
		}
		try {
			
			cdl.await();
			es.shutdown();
			es.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println("执行时间=="+(System.currentTimeMillis()-start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String sendGet(String url, String username,int age) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?username=" + username+"&age="+age;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            connection.setConnectTimeout(15);
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
