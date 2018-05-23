import com.tanghao.rabbit.learn.springboot.client.SpringBootRabbitCilentStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author： Canthny
 * @Description： 单元测试
 * @Date： Created in 2018/5/23 14:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRabbitCilentStarter.class)
public class SpringBootRabbitClientTest {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange orderSuccessExchange;

    @Autowired
    Queue orderSucMsgSendQueue;

    @Autowired
    Queue orderSucProceedQueue;

    @Test
    public void delete(){
        deleteQueue();
        deleteExchange();
    }

    @Test
    public void deleteQueue(){
        amqpAdmin.deleteQueue(orderSucMsgSendQueue.getName());
        amqpAdmin.deleteQueue(orderSucProceedQueue.getName());
    }

    @Test
    public void deleteExchange(){
        amqpAdmin.deleteExchange(orderSuccessExchange.getName());
    }

    @Test
    public void sendTopicMsg(){
        rabbitTemplate.convertAndSend("order_suc_exchange","order.suc","test send msg by topic exchange");
    }
}
