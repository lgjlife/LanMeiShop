package com.lanmei.common.redis.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: HyperLogLog工具类
 * 　　　　　　　　HyperLogLog相当于其值是唯一的,可用来统计在线人数
 *              pfadd  key  a b c ;key[a,b,c]
 *              pfcount key --> size is 3;
 *
 *              pfadd  key  a  --> key[a,b,c]
 *              pfcount key --> size is 3;
 *
 *              pfadd  key  d  --> key[a,b,c,d]
 *              pfcount key --> size is 4;
 *
 *
 *
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-30 03:41
 **/
@Component
public class RedisHyperLogLogUtil<K, V> {


    RedisTemplate<K, V> redisTemplate;

    HyperLogLogOperations<K, V> hyperLogLogOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 8/30/18 
     */

    public RedisHyperLogLogUtil(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
        hyperLogLogOperations =  this.redisTemplate.opsForHyperLogLog();
    }


    /** 
     * @description: 向key 添加varlue
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/30/18 
    */ 
    public Long add(K key, V... var){
        try {
            Long num =  hyperLogLogOperations.add(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:  查看key的size
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/30/18 
    */ 
    public  Long size(K... key){
        try {
            Long size =  hyperLogLogOperations.size(key);
            return size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:  合并
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/30/18 
    */ 
    public Long union(K destkey, K... sourcekey){
        try {
            Long num =  hyperLogLogOperations.union(destkey,sourcekey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:　
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/30/18 
    */ 
    public boolean delete(K key){
        try {
              hyperLogLogOperations.delete(key);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    


}
