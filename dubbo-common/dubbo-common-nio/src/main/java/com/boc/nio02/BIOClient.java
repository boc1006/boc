package com.boc.nio02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class BIOClient {
	public static void main(String[] args) throws Exception{
		Socket client = new Socket("localhost",8888);
		OutputStream os = client.getOutputStream();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		while(true) {
			String str = br.readLine();
			if(str.equals("quit")) {
				break;
			}
			os.write(str.getBytes());
			os.flush();
			
			InputStream is = client.getInputStream();
			byte buff[] = new byte[1024];
			int len = 0;
			if((len = is.read(buff)) > 0) {
				System.out.println("客户端收到消息-->"+new String (buff,0,len));
			}
		}
		
		client.close();
	}
}
