package com.tanghao.redis.jedis.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @Author： Canthny
 * @Description： jedis单点工具类
 * @Date： Created in 2019/4/8 9:46
 */
public class JedisUtil {

    private static JedisPool jedisPool;

    /**
     * 初始化jedis连接
     */
    static {
        try {
            if(!RedisConfig.IS_CLUSTER){
                init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void init() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(RedisConfig.CONNECTION_NUM);
        jedisPool= new JedisPool(genericObjectPoolConfig, RedisConfig.SINGLE_HOST, RedisConfig.SINGLE_PORT);
    }

    /**
     * 获取资源
     * @return
     * @throws JedisException
     */
    public static Jedis getResource() throws JedisException {
        if(null==jedisPool){
            throw new IllegalArgumentException("redis type is cluster not standalone, check RedisConfig please");
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }

    /**
     * 归还资源
     * @param jedis
     * @param
     */
    public static void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     * 释放资源
     * @param jedis
     * @param
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    public static String get(String key){
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时 以秒为单位
     * @return
     */
    public static String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }
}
