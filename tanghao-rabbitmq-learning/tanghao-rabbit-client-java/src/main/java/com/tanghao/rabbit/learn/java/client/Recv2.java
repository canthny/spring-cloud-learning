package com.tanghao.rabbit.learn.java.client;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author： Canthny
 * @Description： 另一个消费者，用来测试第一个消费者没有返回ack的情况
 * @Date： Created in 2018/5/22 14:41
 */
public class Recv2 {

    public static void main(String[] argv) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp://tanghao:123456@localhost:5672");
            Connection conn = factory.newConnection();
            final Channel channel = conn.createChannel();

            basicConsumerPush(channel);//push方式消费

//            basicGetPull(channel);//pull方式消费

            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费者PUSH API
     * @param channel
     * @throws IOException
     */
    private static void basicConsumerPush(Channel channel) throws IOException {
        channel.basicConsume("th_direct_Q1",false,"testConsumer2",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                               Envelope envelope,
                                               AMQP.BasicProperties properties,
                                               byte[] body)
                            throws IOException
                    {
                        String routingKey = envelope.getRoutingKey();
                        String contentType = properties.getContentType();
                        long deliveryTag = envelope.getDeliveryTag();
                        // (process the message components here ...)
                        System.out.println(new String(body));
                        /** 当send里的basicPublish的MessageProperties.PERSISTENT_TEXT_PLAIN消息持久化打开
                         *  消费者需要返回ack确认后才会server端才会删除消息，socket超时或者消费者进程被杀死，server端会重新发送给其他消费者
                         *  若杀死该消费者进程server端会立即发送给其他消费者，若无则等待消费者重启后重新发送
                         *  若socket超时也会发送给其他消费者，具体机制不详，继续执行当前消费者则可能会报两个错：
                         *      java.net.SocketException: Connection reset by peer: socket write error
                         *      com.rabbitmq.client.AlreadyClosedException: connection is already closed due to connection error; cause: java.net.SocketException: Connection reset
                         *  这样如果server端没有收到ack确认就可能存在重复的消费，需要在消费端进行控制
                         * */
                        channel.basicAck(deliveryTag, false);
                    }
                });
    }

    /**
     * 消费者PULL API
     * @param channel
     * @throws IOException
     * @throws InterruptedException
     */
    private static void basicGetPull(Channel channel) throws IOException, InterruptedException {
        while(true){
            GetResponse response = channel.basicGet("th_direct_Q1", false);
            if (response == null) {
                // No message retrieved.
                Thread.sleep(1000L);
            } else {
                AMQP.BasicProperties props = response.getProps();
                byte[] body = response.getBody();
                long deliveryTag = response.getEnvelope().getDeliveryTag();
                System.out.println(new String(body));
                // (process the message components here ...)
                channel.basicAck(deliveryTag, false); // acknowledge receipt of the message
            }
        }
    }
}
