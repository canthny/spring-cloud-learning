package com.canthny.spring.framework.learning.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author： Canthny
 * @Description：
 * @Date： Created in 2019/3/11 22:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class YamlTest {

    @Test
    public void test() throws IOException {
        Yaml yaml = new Yaml();
        A a2 = yaml.loadAs(new ClassPathResource("test.yml").getInputStream(),A.class);
        A a = new A();
        a.setName("test");
        a.setPort(100);
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setType("standalone");
        TableConfig tableConfig = new TableConfig();
        tableConfig.setKey("id");
        tableConfig.setTableName("test1");
        tableConfig.setValue("id,name");
        List<TableConfig> list = new ArrayList<>();
        list.add(tableConfig);
        TableConfig tableConfig2 = new TableConfig();
        tableConfig2.setKey("id");
        tableConfig2.setTableName("test2");
        tableConfig2.setValue("name");
        list.add(tableConfig2);
        redisConfig.setTables(list);
        a.setRedisConfig(redisConfig);

        yaml.dump(a,new FileWriter("D://test2.yml"));
    }

    class A{
        private String name;

        private int port;

        private RedisConfig redisConfig;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public RedisConfig getRedisConfig() {
            return redisConfig;
        }

        public void setRedisConfig(RedisConfig redisConfig) {
            this.redisConfig = redisConfig;
        }
    }

    class RedisConfig{
        private String type;

        private List<TableConfig> tables;

        public List<TableConfig> getTables() {
            return tables;
        }

        public void setTables(List<TableConfig> tables) {
            this.tables = tables;
        }

        public String getType() {

            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    class TableConfig{
        private String tableName;

        private String key;

        private String value;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
