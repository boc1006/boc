package com.boc.nio05;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {
	private static Selector selector = null;

	public static void main(String[] args) throws Exception {
		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false);
		SocketAddress address = new InetSocketAddress(8888);
		server.socket().bind(address);
		selector = Selector.open();
		SelectionKey keys = server.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println(keys.interestOps());
		while (true) {
			int readyChannels = selector.select();//阻塞到至少有一个通道在你注册的事件上就绪了
//			int readyChannels2 = selector.select(1000);//和select()一样，除了最长会阻塞timeout毫秒(参数)。
//			int readyChannels3 = selector.selectNow();//不会阻塞，不管什么通道就绪都立刻返回
			//select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。如果调用select()方法，
			//因为有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。
			//如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了。
			if (readyChannels == 0)
				continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if (key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (key.isConnectable()) {
					// a connection was established with a remote server.
				} else if (key.isReadable()) {
					// a channel is ready for reading
				} else if (key.isWritable()) {
					// a channel is ready for writing
				}
				keyIterator.remove();
			}
		}
	}
}
