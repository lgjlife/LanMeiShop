package com.lanmei.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description: 发布订阅－－－订阅类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-29 17:04
 **/
@Service
public class RedisReceiver implements MessageListener {


    private static final Logger log = LoggerFactory.getLogger("MessageListener");
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    RedisSerializer<MyMessage> redisSerializer;
    RedisSerializer<String> stringgSerializer;
    //@Override
    public void receiveMessage(Message message, byte[] bytes) {
        redisSerializer = (RedisSerializer<MyMessage>)redisTemplate.getValueSerializer();
        MyMessage message1 = redisSerializer.deserialize(message.getBody());

        log.info("The message channel is " + message.getChannel());
        log.info("The message data is " + message1.toString());
    }


    public void receiveMessage(String message) {
        //这里是收到通道的消息之后执行的方法
        log.info("我接收到一个消息：The message data is " + message);

       /* try {
            log.info("执行反序列化........");
            redisSerializer = (RedisSerializer<MyMessage>) redisTemplate.getValueSerializer();
            MyMessage message1 = redisSerializer.deserialize(message.getBytes());
            if(message1 == null){
                log.info("message1 is null");
            }
            //log.info("The message channel is " + message.getChannel());
            log.info("反序列化结果 " + message1.toString());
        }
        catch (Exception ex ){
            ex.printStackTrace();
        }*/

    }

    public void receiveMessage1(Message message) {
        //这里是收到通道的消息之后执行的方法
        log.info("我是消费者，专门接收消息....");
        //log.info("我接收到一个消息：The message data is " + message);

       /* try {
            log.info("执行反序列化........");
            redisSerializer = (RedisSerializer<MyMessage>) redisTemplate.getValueSerializer();
            MyMessage message1 = redisSerializer.deserialize(message.getBytes());
            if(message1 == null){
                log.info("message1 is null");
            }
            //log.info("The message channel is " + message.getChannel());
            log.info("反序列化结果 " + message1.toString());
        }
        catch (Exception ex ){
            ex.printStackTrace();
        }*/

    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("我是消费者，专门接收消息....");

        try {
            log.info("执行反序列化........");
            redisSerializer = (RedisSerializer) redisTemplate.getValueSerializer();
            stringgSerializer = redisTemplate.getStringSerializer();
            MyMessage message1 = redisSerializer.deserialize(message.getBody());

            String channel = stringgSerializer.deserialize(message.getChannel());
            if(message1 == null){
                log.info("message1 is null");
            }

            else{
                log.info("反序列化结果 " + message1.toString());
                log.info("The message channel is " + channel);
            }



        }
        catch (Exception ex ){
            ex.printStackTrace();
        }

    }
}
