package tanghao.log.trace.sleuth.elk.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @Author： Canthny
 * @Description： zipkin服务器启动类
 * @Date： Created in 2018/6/1 10:17
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerStarter.class);
    }
}
