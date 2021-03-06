package com.boc.nio06;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class RPCServer {
	private static RPCServer instance = new RPCServer();
	private HashMap<String, Object> str_obj = new HashMap<>();
	private Selector selector;
	private ServerSocketChannel ssc;

	private RPCServer() {
		try {
			ssc = ServerSocketChannel.open();
			InetSocketAddress address = new InetSocketAddress(3003);
			ssc.configureBlocking(false);
			ssc.bind(address);
			selector = Selector.open();
			ssc.register(selector, SelectionKey.OP_ACCEPT);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static RPCServer getInstance() {
		return instance;
	}

	public RPCServer addClass(Object obj) {
		String name = obj.getClass().getInterfaces()[0].getSimpleName();
		str_obj.put(name, obj);
		return this;
	}

	// 开发服务等待连接
	public void start() {
		System.out.println("-----服务器已经启动了------");
		ByteBuffer buff = ByteBuffer.allocate(1024);
		try {
			while (selector.select() > 0) {
				for (SelectionKey sk : selector.selectedKeys()) {
					selector.selectedKeys().remove(sk);
					if (sk.isAcceptable()) {
						// 调用accept方法接受连接，产生服务器端对应的SocketChannel
						SocketChannel sc = ssc.accept();
						// 设置采用非阻塞模式
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
						// 将sk对应的Channel设置成准备接受其他请求
						sk.interestOps(SelectionKey.OP_ACCEPT);
					} else if (sk.isReadable()) {
						// 获取该SelectionKey对应的Channel，该Channel中有可读的数据
						SocketChannel sc = (SocketChannel) sk.channel();
						try {
							// 执行方法
							remoteHandMethod(buff, sk, sc);
						} catch (Exception e) {
							// 从Selector中删除指定的SelectionKey
							sk.cancel();
							if (sk.channel() != null) {
								sk.channel().close();
							}
//							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void remoteHandMethod(ByteBuffer buff, SelectionKey sk, SocketChannel sc) throws IOException,
			NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		buff.clear();
		int read = sc.read(buff);
		int postion = buff.position();// 这里获取它真正的大小
		byte[] data = buff.array();
		String message = new String(data, 0, postion);// class/方法名(参数类型:参数,参数类型:参数)
		message = message.trim();
		buff.clear();
		String[] clazzData = message.split("/");
		String className = clazzData[0];
		String methodName = clazzData[1].substring(0, clazzData[1].indexOf("("));
		String temp = clazzData[1].substring(clazzData[1].indexOf("(") + 1, clazzData[1].indexOf(")"));
		// 上一步的格式如下 1 ""，"string:nice"
		String[] typeValues = decodeParamsTypeAndValue(temp);

		Object object = str_obj.get(className);
		Class clazz = object.getClass();
		Object result = null;
		if (typeValues == null) {
			Method method = clazz.getDeclaredMethod(methodName, null);
			result = method.invoke(object, null);
		} else {
			Class[] types = new Class[typeValues.length];
			Object[] values = new Object[typeValues.length];
			for (int i = 0; i < typeValues.length; i++) {
				String[] tv = typeValues[i].split(":");
				String type = tv[0];
				String value = tv[1];
				types[i] = Class.forName(type);
				if (type.contains("Integer") || type.contains("int"))
					values[i] = Integer.parseInt(value);
				else if (type.contains("Float") || type.contains("float"))
					values[i] = Float.parseFloat(value);
				else if (type.contains("Double") || type.contains("double"))
					values[i] = Double.parseDouble(value);
				else if (type.contains("Long") || type.contains("long"))
					values[i] = Long.parseLong(value);
				else
					values[i] = value;
			}
			Method method = clazz.getDeclaredMethod(methodName, types);
			result = method.invoke(object, values);
		}
		if (result == null) {
			result = "Void:null";
		} else {
			result = result.getClass().getSimpleName() + ":" + result;
		}
		// 发送结果回去
		sc.write(ByteBuffer.wrap(result.toString().getBytes()));
		sk.interestOps(SelectionKey.OP_READ);
	}

	// 它返回的格式是 参数类型：参数值
	private String[] decodeParamsTypeAndValue(String params) {
		if (params == null || params.equals(""))
			return null;
		if (params.indexOf(",") < 0)
			return new String[] { params };
		return params.split(",");

	}
}
