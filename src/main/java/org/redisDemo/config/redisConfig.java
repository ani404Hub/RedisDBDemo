package org.redisDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class redisConfig {
    @Bean
    public JedisConnectionFactory connFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("localhost");
        config.setPort(6379);                               //Redis is available on this port, to integrate
        return new JedisConnectionFactory(config);
    }
    @Bean
    @Primary                                                //Annotate this bean as primary, else other redis template with be fetched
    public RedisTemplate<String, Object> redTempl() {
            RedisTemplate<String, Object> templ = new RedisTemplate<>();
            templ.setConnectionFactory(connFactory());
            templ.setKeySerializer(new StringRedisSerializer());
            templ.setKeySerializer(new JdkSerializationRedisSerializer());
            templ.setHashKeySerializer(new StringRedisSerializer());
            templ.setHashKeySerializer(new JdkSerializationRedisSerializer());
            templ.setEnableTransactionSupport(true);
            templ.afterPropertiesSet();
            return templ;
    }
}
