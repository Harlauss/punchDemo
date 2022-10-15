package com.harlaus.config;


import com.harlaus.common.properties.JedisProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Autowired
    private JedisProperty jedisProperty;

    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(jedisProperty.getMaxIdle());
        config.setMaxWaitMillis(jedisProperty.getMaxWait());
        JedisPool pool = new JedisPool(config,jedisProperty.getHost(),jedisProperty.getPort(),jedisProperty.getTimeOut(),jedisProperty.getPassword());
        return pool;
    }

}
