package com.tanghao.redis.lettuce.template;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author： Canthny
 * @Description：
 * @Date： Created in 2019/3/7 11:10
 */
public class LettuceStringTemplate {

    private RedisClient redisClient;

    private GenericObjectPool<StatefulRedisConnection<String,String>> pool;

    public void init(RedisURI redisURI, GenericObjectPoolConfig poolConfig){
        redisClient = RedisClient.create(redisURI);
        this.pool = ConnectionPoolSupport.createGenericObjectPool(() -> redisClient.connect(), null!=poolConfig?poolConfig:new GenericObjectPoolConfig());
    }




}
