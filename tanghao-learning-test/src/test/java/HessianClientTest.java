import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tanghao.learning.test.DemoApplication;
import tanghao.learning.test.testHessian.TestHessianService;

/**
 * @Author： Canthny
 * @Description： 测试是否可以调用老架构hessian接口
 * @Date： Created in 2018/6/7 15:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class HessianClientTest {

    @Autowired
    TestHessianService testHessianService;

    @Test
    public void testHessianCall(){
        try {
            String  response =  testHessianService.test("test hessian");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
