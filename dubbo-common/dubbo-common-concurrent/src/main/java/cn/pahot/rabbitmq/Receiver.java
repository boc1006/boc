package cn.pahot.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 推荐模式
 * 推模式将消息提前推送给消费者，消费者必须设置一个缓冲区缓存这些消息。好处很明显，消费者总是有一堆在内存中待处理的消息，所以效率高。缺点是缓冲区可能会溢出
 * 如果消费者断开,则已推送的消息全会丢失
 * <p>@Title: Receiver.java 
 * <p>@Package cn.pahot.rabbitmq 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月22日 下午4:02:37 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class Receiver {
	private final static String QUEUE_NAME = "hello";
	  
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.220");
        factory.setUsername("admin");
        factory.setPassword("amwsoq");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.printf(" [    %2$s<===](%1$s) %3$s\n", "Receiver", QUEUE_NAME, message);
                
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
