package com.boc.nio05;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * ServerSocketChannel可以设置成非阻塞模式。在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。
 * 因此，需要检查返回的SocketChannel是否是null.
 * <p>@Title: SocketChannelTest.java 
 * <p>@Package com.boc.nio05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月12日 上午10:44:18 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SocketChannelTest {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		serverSocketChannel.configureBlocking(false);

		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();

			if (socketChannel != null) {
				// do something with socketChannel...
			}
		}
	}
}
