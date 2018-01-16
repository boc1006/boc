package com.boc.nio02;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOSocket {
	public static void main(String[] args) throws Exception {
		//使用telnet测试 ctrl + ] 可批量输出
		ServerSocket server = new ServerSocket(7777);
		System.out.println("服务器已启动...");
		ExecutorService es = Executors.newCachedThreadPool();
		while(true) {
			//获取客户端套节字
			Socket socket = server.accept();//等待客户端的连接,会阻塞!
			System.out.println("有新的客户端连接上来了...");
			es.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						InputStream is = socket.getInputStream();
						byte b [] =new byte[1024];
						while(true) {
							int data = is.read(b);//等待客户端的输入,会进行阻塞!
							if(data != -1) {
								System.out.println(new String(b,0,data,"gbk"));
							}else {
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
									
				}
			});
			
		}
	}
}
