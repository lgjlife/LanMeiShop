package com.lanmei.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-29 16:48
 **/
@Component
class RedisSender extends  Thread {

    private static final Logger log = LoggerFactory.getLogger("MessageListener");

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void pubMessage(){
        String channel  = "redis-channle";
        String message  = "This a demo message";
        MyMessage message1 = new MyMessage("This a demo message",10001L);
        redisTemplate.convertAndSend(channel,message1);
        log.info("我正在发布一个消息，Channel  是" + channel + "; 消息内容是：" + message1.toString());

        Long index = 0L;

        while(true){

            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            redisTemplate.convertAndSend(channel,(index++));
            log.info("我正在发布一个消息，Channel  是" + channel + "; 消息内容是：" + index);
        }
    }


    public void pubMessage1(){
        String channel  = "redis-channle";
        String message  = "This a demo message";
        MyMessage message1 = new MyMessage("This a demo message",10001L);
        redisTemplate.convertAndSend(channel,message1);
        log.info("我正在发布一个消息，Channel  是" + channel + "; 消息内容是：" + message1.toString());

        Long index = 0L;

        while(true){

            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            message1.setId(index++);
            redisTemplate.convertAndSend(channel,message1);
            log.info("我正在发布一个消息，Channel  是" + channel + "; 消息内容是：" + message1.toString());
        }
    }



    public void pubMessageMore(){


        this.start();
    }

    @Override
    public void run() {
        super.run();
        Long index = 0L;
        String channel  = "redis-channle";
        String message  =  "";

        while(true){
            log.info("我是发布者，专门发布消息....");
            message  = index++ + "";
           // redisTemplate.convertAndSend(channel,message);
            log.info("我正在发布一个消息，Channel  是" + channel + "; 消息内容是：" + message);

            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
