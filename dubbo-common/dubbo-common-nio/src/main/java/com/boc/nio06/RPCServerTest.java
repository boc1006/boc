package com.boc.nio06;

public class RPCServerTest {
	public static void main(String[] args) {
		RPCServer server = RPCServer.getInstance();
		server.addClass(new SayHelloBean());
		server.start();
	}
}
