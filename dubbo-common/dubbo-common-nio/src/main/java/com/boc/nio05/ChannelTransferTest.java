package com.boc.nio05;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 在Java NIO中，如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel（译者注：channel中文常译作通道）传输到另外一个channel。
 * <p>@Title: ChannelTransferTest.java 
 * <p>@Package com.boc.nio05 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年10月11日 下午10:08:54 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class ChannelTransferTest {
	public static void main(String[] args) throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("E://1.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("E://2.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		/*
		 * 将数据从源通道传输到FileChannel中
		 * 方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数。
		 * 此外要注意，在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
		 */
		toChannel.transferFrom(fromChannel, position, count);
		/*
		 * 将数据从FileChannel传输到其他的channel中
		 */
		toChannel.transferTo(position, count, fromChannel);
	}
}
