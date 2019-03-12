package com.canthny.spring.framework.learning.test;

import java.util.List;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2019/3/11 23:13
 */
public class A {

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

    public class RedisConfig{
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

    public class TableConfig{
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
