package com.boc.nio02;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOSocket {
	private Selector selector;//多路复用选择器
	
	public static void main(String[] args) throws Exception {
		NIOSocket nio = new NIOSocket();
		nio.initServer(8888);
		nio.listenSelector();
	}
	public void initServer(int port) throws Exception{
		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false);//非阻塞
		server.socket().bind(new InetSocketAddress(port));
		
		this.selector = Selector.open();
		server.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("服务器已启动...");
	}
	
	public void listenSelector() throws Exception {
		//轮询监听selector
		while(true) {
			//获取一个多路复用选择器
			this.selector.select();//等待客户端的连接,会阻塞
//			this.selector.select(1000);//如果等待1000毫秒没有客户连接，则会往下面执行
//			this.selector.selectNow();//立即往下面执行,不进行阻塞
//			this.selector.wakeup();//唤醒当前selector
			Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
			while(iter.hasNext()) {
				SelectionKey key = iter.next();
				iter.remove();
				//处理请求
				handle(key);
			}
		}
	}

	/**
	 * 处理客户端请求
	 * @param key
	 */
	private void handle(SelectionKey key) throws Exception{
		if(key.isAcceptable()) {//如果当前请求为连接请求
			//处理客户端连接事件
			ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
			SocketChannel socketChannel = serverChannel.accept();//不会阻塞，与bio相反
			socketChannel.configureBlocking(false);
			//接收客户端发送过来的信息,需要给通道设置“读”权限
			socketChannel.register(this.selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);//设置为可读状态
			return;
		}
		
		if(key.isReadable()) {
			SocketChannel socketChannel = (SocketChannel)key.channel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int readData = socketChannel.read(buffer);//此时不会阻塞,这是跟BIO最本质的区别,BIO在读取客户数据的时候,会进行阻塞
			if(readData > 0 ) {
				buffer.flip();
				String info = new String(buffer.array(),0,readData,"gbk").trim();
				System.out.println("服务器端收到数据："+info);
				key.attach(info);
//				socketChannel.register(this.selector, SelectionKey.OP_WRITE, info);//设置为可写状态
			}
			buffer.clear();
//			socketChannel.close();
//			key.cancel();
		}
		
		if(key.isWritable()) {
			SocketChannel socketChannel = (SocketChannel)key.channel();
			String info = (String) key.attachment();//获取从读状态传过来的数据
			socketChannel.write(ByteBuffer.wrap(("hello,you name is "+info).getBytes()));
//			key.cancel();
//			socketChannel.close();
			System.out.println("客户端已关闭...");
		}
		
//		if(key.isConnectable()) {
//			
//		}
	}
}
