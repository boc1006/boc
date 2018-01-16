package com.boc.nio05;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
	final static int size = 8;

	public static void main(String[] args) {
		try {
			RandomAccessFile aFile = new RandomAccessFile("E://1.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(5);
			int bytesRead;
			while ((bytesRead = inChannel.read(buf)) != -1) {//循环从channel中读取数据到buffer
				System.out.println("\nread " + bytesRead);
				buf.flip();

				while (buf.hasRemaining()) {
					int p = buf.limit() - buf.position();
					byte b[] = new byte[p > size ? size : p];//循环从buffer中读取指定的长度到byte[]中，并打印出来
					buf.get(b);
					// System.out.println((char) buf.get());
					System.out.print(new String(b));
				}
				buf.clear();//清空buffer数据
				
				// bytesRead = inChannel.read(buf);
			}
			aFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
