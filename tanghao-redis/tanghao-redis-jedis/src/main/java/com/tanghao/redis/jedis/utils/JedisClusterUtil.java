package com.tanghao.redis.jedis.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author： Canthny
 * @Description： jedis工具类
 * @Date： Created in 2019/4/8 8:52
 */
public class JedisClusterUtil {

    private static JedisCluster jedisCluster = null;

    /**
     * 初始化jedis连接
     */
    static {
        try {
            if(RedisConfig.IS_CLUSTER){
                init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized static void init(){
        Set<HostAndPort> nodes = new HashSet<>();
        for(String hostAndPort : RedisConfig.CLUSTER_HOSTANDPORT) {
            nodes.add(new HostAndPort(hostAndPort.split(":")[0].trim(), Integer.valueOf(hostAndPort.split(":")[1].trim())));
        }
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(RedisConfig.CONNECTION_NUM);
        jedisCluster = new JedisCluster(nodes, genericObjectPoolConfig);
    }

    public static String get(String key){
        if(null==jedisCluster){
            throw new IllegalArgumentException("redis type is not cluster, check RedisConfig please");
        }
        return jedisCluster.get(key);
    }

    public static String set(String key, String value){
        if(null==jedisCluster){
            throw new IllegalArgumentException("redis type is not cluster, check RedisConfig please");
        }
        return jedisCluster.set(key, value);
    }

}
