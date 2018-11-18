package com.hytc.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWriter {

    /**
     * Redis连接吃对象
     */
    private JedisPool jedisPool;


    /**
     * 构造函数
     *
     * @param jedisPoolConfig
     */
    public JedisPoolWriter(JedisPoolConfig jedisPoolConfig, final String host, final int port) {
        try {
            jedisPool = new JedisPool(jedisPoolConfig, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取链接池对象
     *
     * @return JedisPool
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 设置链接池对象
     *
     * @param jedisPool
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
