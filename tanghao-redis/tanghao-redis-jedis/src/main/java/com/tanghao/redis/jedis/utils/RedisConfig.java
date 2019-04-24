package com.tanghao.redis.jedis.utils;

/**
 * @Author： Canthny
 * @Description： jedis工具类
 * @Date： Created in 2019/4/8 8:52
 */
public class RedisConfig {

    public final static boolean IS_CLUSTER = false;

    public final static String SINGLE_HOST = "192.168.80.128";

    public final static int SINGLE_PORT = 6679;

    public final static String[] CLUSTER_HOSTANDPORT = new String[]{
            "10.20.30.50:6379",
            "192.168.73.229:6379",
            "192.168.73.56:6379",
            "10.20.30.50:6380",
            "192.168.73.229:6380",
            "192.168.73.56:6380"
    };

    public final static int CONNECTION_NUM = 4000;

    public static void main(String[] args) {
        JedisUtil.get("");
    }
}
