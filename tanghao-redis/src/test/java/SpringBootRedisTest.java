import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tanghao.redis.SpringBootRedisStarter;
import redis.clients.jedis.Jedis;

/**
 * @Author： Canthny
 * @Description： redis测试类
 * @Date： Created in 2018/7/18 11:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRedisStarter.class)
public class SpringBootRedisTest {

    private static String SERIAL_NUMBER = "pay_order_no_generate_seq:";

    @Autowired
    Jedis jedis;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public String getSeq(){
//        redisTemplate.
//        String key = SERIAL_NUMBER+"20180718";
//        long seq = jedis.incr(key);
//        return "";
    }
}
