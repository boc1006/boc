package cn.pahot.rabbitmq;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

/**
 * 拉模式
 * 在消费者需要时才去消息中间件拉取消息，这段网络开销会明显增加消息延迟，降低系统吞吐量。
 * <p>@Title: ReceiverByGet.java 
 * <p>@Package cn.pahot.rabbitmq 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月22日 下午4:04:06 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class ReceiverByGet {
	private final static String QUEUE_NAME = "hello";
	  
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.220");
        factory.setUsername("admin");
        factory.setPassword("amwsoq");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  
        while (true) {
            GetResponse resp = channel.basicGet(QUEUE_NAME, true);
            if (resp == null) {
                System.out.println("Get Nothing!");
                TimeUnit.MILLISECONDS.sleep(1000);
            } else {
                String message = new String(resp.getBody(), "UTF-8");
                System.out.printf(" [    %2$s<===](%1$s) %3$s\n", "Receiver", QUEUE_NAME, message);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
    }
}
