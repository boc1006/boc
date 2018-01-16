package cn.pahot.rabbitmq;

public class Test {
	public static void main(String[] args) throws Exception {
        NewExchangeReceiver receiver = new NewExchangeReceiver("hello2", "hello2", true);
        receiver.work();
        NewExchangeSender sender = new NewExchangeSender("hello2", "hello2", true);
        sender.work();
        
    }
}
