package com.boc.nio05;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * scatter/gather用于描述从Channel中读取或者写入到Channel的操作。
 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
 * 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。
 * scatter / gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。
 * <p>@Title: ScatterAndGatherTest.java 
 * <p>@Package com.boc.nio05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月11日 下午9:50:47 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class ScatterAndGatherTest {
	public static void main(String[] args) throws Exception{
		RandomAccessFile aFile = new RandomAccessFile("E://2.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		//long pos = inChannel.position();
		//inChannel.position(pos +123);//从clannel指定位置读/写数据
		ByteBuffer header = ByteBuffer.allocate(100);
		ByteBuffer body   = ByteBuffer.allocate(100);
		header.put("城".getBytes());
		header.flip();
		body.put("博采众长".getBytes());
		body.flip();
		ByteBuffer[] bufferArray = { header, body };
		
		inChannel.write(bufferArray);
//		inChannel.read(bufferArray);
		inChannel.close();
		aFile.close();
	}
}
