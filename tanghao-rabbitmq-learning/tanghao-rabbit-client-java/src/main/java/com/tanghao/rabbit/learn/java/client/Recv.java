package com.tanghao.rabbit.learn.java.client;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author： Canthny
 * @Description： 一个消费者示例包含了PUSH和PULL两种方式
 * @Date： Created in 2018/5/22 14:41
 */
public class Recv {

    public static void main(String[] argv) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp://tanghao:123456@localhost:5672");
            Connection conn = factory.newConnection();
            final Channel channel = conn.createChannel();

            /** 第一次启动消费者连接时没有queue，会报错如下：
             *  Caused by: com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no queue 'th_direct_Q1' in vhost '/', class-id=60, method-id=20)
             *  这个时候无法自动恢复，就算Send那里declear了队列，消费者也不会接收到消息，进程假死的状态？
             *  所以消费者这里需要先declear所订阅的队列，类似于spring中bean初始化一样<rabbit:queue name="th_direct_Q1" durable="false" exclusive="true"/>
             */
            channel.queueDelete("th_direct_Q1");
            //TODO 这就相当于send和recv都declare了队列，有没有什么影响，或者看看spring是怎么处理的
            channel.queueDeclare("th_direct_Q1",true,false,false,null);

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
        channel.basicConsume("th_direct_Q1",false,"testConsumer1",
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
                         *  //TODO 这样如果server端没有收到ack确认就可能存在重复的消费，需要在消费端进行控制
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
