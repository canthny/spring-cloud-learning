package com.tanghao.redis.lettuce.pool;


import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisSetCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author： Canthny
 * @Description：
 * @Date： Created in 2019/3/7 10:51
 */
public class LettuceConnectPool {

    private RedisClient redisClient;

    private GenericObjectPool<StatefulRedisConnection<String,String>> pool;

    public void init(RedisURI redisURI,GenericObjectPoolConfig poolConfig){
        redisClient = RedisClient.create(redisURI);
        this.pool = ConnectionPoolSupport.createGenericObjectPool(() -> redisClient.connect(), null!=poolConfig?poolConfig:new GenericObjectPoolConfig());
    }

    public StatefulRedisConnection<String, Object> getConnection(){
        try {
            RedisCommands<String,String> connection = pool.borrowObject().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
