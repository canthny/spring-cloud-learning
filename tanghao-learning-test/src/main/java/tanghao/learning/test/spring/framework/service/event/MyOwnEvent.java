package tanghao.learning.test.spring.framework.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2019/3/5 22:13
 */
public class MyOwnEvent<T> extends ApplicationEvent {

    private T t;

    public MyOwnEvent(Object myOwnEvent,T t){
        super(myOwnEvent);
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
