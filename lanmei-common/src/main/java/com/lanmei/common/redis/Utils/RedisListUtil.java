package com.lanmei.common.redis.Utils;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 06:35
 **/
public class RedisListUtil<K, V> {

    RedisTemplate<K, V> redisTemplate;
    ListOperations<K, V> listOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    public RedisListUtil(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.listOperations = redisTemplate.opsForList();
    }

    /**
     * @description:  获取　start - end 范围内的ｖalue
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public List<V> range(K key, long start, long end){
        try {
            List<V> list = listOperations.range(key,start,end);
            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }

    /** 
     * @description:  Trim list at key to elements between start and end,去掉字符串前后的空格
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public boolean trim(K key, long start, long end){

        try {
            listOperations.range(key,start,end);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return  false;
        }
    }

    /**
     * @description: Get the size of list stored at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    @Nullable
    public Long size(K key){
        try {
            Long size = listOperations.size(key);
            return size;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Prepend value to key
     * @param:
     * @return: 在 push 操作后的 list 长度
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long leftPush(K key, V var){
        try {
            Long data = listOperations.leftPush(key,var);
            return data;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Prepend values to key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long leftPushAll(K key, V... var){
        try {
            Long length = listOperations.leftPushAll(key,var);
            return length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Prepend values to key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long leftPushAll(K key, Collection<V> var){
        try {
            Long length = listOperations.leftPushAll(key,var);
            return length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Prepend values to key only if the list exists.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long leftPushIfPresent(K key, V var){
        try {
            Long length = listOperations.leftPushIfPresent(key,var);
            return length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description:  Prepend values to key before value.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long leftPush(K key,V pivot, V value){
        try {
            Long length = listOperations.leftPush(key,pivot,value);
            return length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Append value to key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long rightPush(K key, V var){
        try {
            Long length = listOperations.rightPush(key,var);
            return  length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Append values to key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long rightPushAll(K key, V... var){
        try {
            Long length = listOperations.rightPushAll(key,var);
            return  length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Append values to key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long rightPushAll(K key, Collection<V> var){
        try {
            Long length = listOperations.rightPushAll(key,var);
            return  length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Append values to key only if the list exists.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long rightPushIfPresent(K key, V var){
        try {
            Long length = listOperations.rightPushIfPresent(key,var);
            return  length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Append values to key before value.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long rightPush(K key,  V pivot, V value){
        try {
            Long length = listOperations.rightPush(key,pivot,value);
            return  length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Set the value list element at index.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public boolean set(K key, long index, V value){
        try {
            listOperations.set(key,index,value);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    /**
     * @description: Removes the first count occurrences of value from the list stored at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long remove(K key, long count, Object value){
        try {
            Long length = listOperations.remove(key,count,value);
            return  length;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }

    /**
     * @description: Get element at index form list at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V index(K key, long var){
        try {
            V value = listOperations.index(key,var);
            return value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }

    /**
     * @description: Removes and returns first element in list stored at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V leftPop(K key){
        try {
            V value = listOperations.leftPop(key);
            return  value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Removes and returns first element from lists stored at key .
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V leftPop(K key, long time, TimeUnit unit){
        try {
            V value = listOperations.leftPop(key,time,unit);
            return  value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Removes and returns last element in list stored at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V rightPop(K key){
        try {
            V value = listOperations.rightPop(key);
            return  value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Removes and returns last element from lists stored at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V rightPop(K key, long time, TimeUnit unit){
        try {
            V value = listOperations.rightPop(key,time,unit);
            return  value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Remove the last element from list at sourceKey, append it to destinationKey and return its value.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V rightPopAndLeftPush(K sourceKey, K destinationKey){
        try {
            V value = listOperations.rightPopAndLeftPush(sourceKey,destinationKey);
            return  value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description: Remove the last element from list at srcKey, append it to dstKey and return its value.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public V rightPopAndLeftPush(K sourceKey, K destinationKey, long times, TimeUnit unit){
        try {
            V value = listOperations.rightPopAndLeftPush(sourceKey,destinationKey,times,unit);
            return  value;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }
    /**
     * @description:
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public RedisOperations<K, V> getOperations(){
        try {
            RedisOperations<K, V> redisOperations = listOperations.getOperations();
            return redisOperations;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  ex;
        }
    }


}
