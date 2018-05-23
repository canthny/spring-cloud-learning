package com.tanghao.rabbit.learn.java.client;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static void main(String[] argv) throws java.io.IOException
    {
        ConnectionFactory factory = new ConnectionFactory();
        try {
//            factory.setUsername("tanghao");
//            factory.setPassword("123456");
//            factory.setHost("localhost");
//            factory.setPort(5672);

            factory.setUri("amqp://tanghao:123456@localhost:5672");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = connection.createChannel();

        setAckConfirmListener(channel);

//        default_exchange_demo(channel);

        direct_exchange_demo(channel);

//        fanout_exchange_demo(channel);

//        topic_exchange_demo(channel);

//        try {
//            channel.close();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        connection.close();
    }

    /**
     * 收到消费者消费消息成功的ack后的回调函数
     * //TODO 两个队列两个消费者订阅同一个消息的时候会回调两次？
     * @param channel
     * @throws IOException
     */
    private static void setAckConfirmListener(Channel channel) throws IOException {
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("[handleNack] :" + deliveryTag + "," + multiple);

            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("[handleAck] :" + deliveryTag + "," + multiple);
            }
        });
    }

    /**
     * Default Exchange
     * 默认一个direct exchange
     * route Key 即为 queue_name
     * */
    private static void default_exchange_demo(Channel channel) throws IOException {
        channel.queueDeclare("default_queue", false, false, false, null);
        String message = "this msg do nothing";
        channel.basicPublish("", "default_queue", null, message.getBytes());
    }

    /**
     * Direct Exchange
     * 声明exchange
     * 声明queue
     * 绑定queue，指定一个route key=K
     * message中包含K，则被发往指定queue
     * //两个queue可以通过同一个路由关键字绑定同一个exchange，一个开户成功的消息可能被
     * //同一个队列可以通过多个绑定键和exchange进行绑定？可以比方说一个日志队列绑定三种route_key如：info，debug，error
     * //Direct exchanges are often used to distribute tasks between multiple workers (instances of the same application) in a round robin manner.
     * //When doing so, it is important to understand that, in AMQP 0-9-1, messages are load balanced between consumers and not between queues.
     * */
    private static void direct_exchange_demo(Channel channel) throws IOException {
        /** durable重启后仍然存在，autoDelete当exchange不再使用时系统会自动删除 */
        channel.exchangeDeclare("direct_exchange","direct",true,false,null);
        /** //TODO exclusive专用的(仅限于此连接)？*/
        channel.queueDeclare("th_direct_Q1",true,false,false,null);
        channel.queueBind("th_direct_Q1","direct_exchange","Key1");
        channel.basicPublish("direct_exchange","Key1", MessageProperties.PERSISTENT_TEXT_PLAIN,"this msg routekey is Key1".getBytes());
    }

    /**
     * Fanout Exchange
     * N个queue绑定到exchange上，不需要route key，广播方式进行路由
     * 所有绑定的queue都会收到同样的消息
     * 场景：大型网游，积分排行榜更新或者世界消息；
     */
    private static void fanout_exchange_demo(Channel channel) throws IOException {
        channel.exchangeDeclare("fanout_exchange","fanout",true,false,null);
        channel.queueDeclare("th_exchange_Q1",true,false,false,null);
        channel.queueBind("th_exchange_Q1","fanout_exchange",null);
        channel.basicPublish("fanout_exchange",null,null,"this msg routekey is null".getBytes());
    }

    /**
     * Topic Exchange
     * route key 支持使用通配符，#表示0或多个字符，*表示一个字符
     * 如：route key为"tanghao.*.test.#"
     * 当消息的route key为"tanghao.1.test.0315"的时候匹配成功
     */
    private static void topic_exchange_demo(Channel channel) throws IOException {
        channel.exchangeDeclare("topic_exchange","topic",true,false,null);
        channel.queueDeclare("th_topic_Q1",true,false,false,null);
        channel.queueBind("th_topic_Q1","topic_exchange","tanghao.*.test.#");
        channel.basicPublish("topic_exchange","tanghao.1.test.0315",null,"this msg routekey is tanghao.1.test.0315".getBytes());//收到
        channel.basicPublish("topic_exchange","tanghao.123.test.0315",null,"this msg routekey is tanghao.123.test.0315".getBytes());//收不到
        channel.basicPublish("topic_exchange","tanghao.1.test",null,"this msg routekey is tanghao.1.test".getBytes());//收到
    }
}
