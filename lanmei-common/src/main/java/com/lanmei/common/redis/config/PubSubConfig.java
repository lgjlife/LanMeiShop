package com.lanmei.common.redis.config;


import com.lanmei.common.redis.RedisReceiver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @program: demo
 * @description:  发布订阅配置类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-29 16:33
 **/

//@Configuration
public class PubSubConfig {


    @Bean
    public RedisMessageListenerContainer RedisMessageListenerContainerBean
             (@Qualifier("JedisConnectionFactory") RedisConnectionFactory  connectionFactory,
              MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter,new ChannelTopic("redis-channle"));

        return  container;
    }

    @Bean
    public MessageListenerAdapter messageListener(RedisReceiver redisReceiver){
        MessageListenerAdapter listener
                = new MessageListenerAdapter(redisReceiver);
        listener.setSerializer(new GenericJackson2JsonRedisSerializer());
        listener.setSerializer(new StringRedisSerializer());
        return  listener;
    }


}
