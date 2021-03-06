package com.boc.netty02;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.util.CharsetUtil;

public class ClientLogicHandler extends StringDecoder {
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		System.out.println("######ClientReadDecoder");

		byte[] buffer = ((ChannelBuffer) msg).array();
		byte last = buffer[buffer.length - 1];
		// 46 is '.'
		if (last == 46)
			return new String(buffer, CharsetUtil.UTF_8);
		return null;
	}
}
