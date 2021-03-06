package tanghao.learning.test.spring.framework.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tanghao.learning.test.mapper.TestMapper;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2019/3/5 22:12
 */
@Component
//public class MyOwnListener implements ApplicationListener<MyOwnEvent<String>>{
public class MyOwnListener{

    @Autowired
    TestMapper testMapper;

//    @Override
//    public void onApplicationEvent(MyOwnEvent<String> event) {
//        System.out.println("get my own event:"+event.getT());
//    }

    @EventListener
    public void listenMyOwnEvent(MyOwnEvent<String> event){
        System.out.println("test mapper:"+testMapper.selectCount());
        System.out.println("get my own event:"+event.getT());
    }
}
