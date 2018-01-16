package com.boc.netty02;

import java.util.concurrent.Executor;

import org.jboss.netty.handler.execution.ExecutionHandler;

public class ServerExecutionHandler extends ExecutionHandler {
	public ServerExecutionHandler(Executor executor) {
		super(executor);
	}
}
