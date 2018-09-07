package com.lanmei.common.redis.Utils;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: Strings 相关命令工具
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 02:40
 **/
public class RedisStringsUtil<K,V> {

    RedisTemplate<K, V> redisTemplate;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    public RedisStringsUtil(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }


    /**
     * @description:  set 设置
     * @param:  　　key　键
     * 　　　　　　　value 值
     * @return:  　true  成功
     * 　　　　　　　false　失败
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public boolean set(K key,V value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean delete(K key){

       return redisTemplate.delete(key);
    }


    /**
     * @description:  set 设置 ,并且设置过期时间
     * @param:  　　key　键
     * 　　　　　　　value 值
     * @return:  　true  成功
     * 　　　　　　　false　失败
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public boolean set(K key,V value,long var3, TimeUnit var5) {
        try {
            redisTemplate.opsForValue().set(key, value, var3,  var5);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @description: 同时设置多个key
     * @param:       var : the map of the data
     * @return:      true : set success ; false : set fail
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public boolean multiSet(Map<? extends K, ? extends V> var){
        try {
            redisTemplate.opsForValue().multiSet(var);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 同时设置多个key,,只有不存在任何一个键时，所有的键才能设置成功
     * @param:       var : the map of the data
     * @return:      true : set success ; false : set fail
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public  boolean multiSetIfAbsent(Map<? extends K, ? extends V> var){
        try {
            redisTemplate.opsForValue().multiSetIfAbsent(var);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /** 
     * @description:  通过key 获取　ｖalue
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public  V get(K key){
        try {
            V data =   redisTemplate.opsForValue().get(key);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /** 
     * @description: 获取当前key的ｖalue值，并设这新的值
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public   V getAndSet(K key, V var){
        try {
            V data =   redisTemplate.opsForValue().getAndSet(key,var);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /** 
     * @description: 获取所有keu对应的ｖalue
     * @param:       key : key　map集合
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
   public List<V> multiGet(Collection<K> keys){
       try {
           List<V> values =   redisTemplate.opsForValue().multiGet(keys);
           return values;
       } catch (Exception e) {
           e.printStackTrace();
           throw e;
       }
    }

    /** 
     * @description:  增加ｖal值
     * 对存储在指定key的数值执行原子的加操作。
     *
     * 如果指定的key不存在，那么在执行incr操作之前，会先将它的值设定为0。
     *
     * 如果指定的key中存储的值不是字符串类型（fix：）或者存储的字符串类型不能表示为一个整数，
     *
     * 那么执行这个命令时服务器会返回一个错误(eq:(error) ERR value is not an integer or out of range)。
     *
     * 这个操作仅限于64位的有符号整型数据
     * @param:
     * @return:  新值
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public Long increment(K key, long var){
        try {
            Long data =   redisTemplate.opsForValue().increment(key,var);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public Double increment(K key, double var){
        try {
            Double data =   redisTemplate.opsForValue().increment(key,var);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * @description: 如果 key 已经存在，并且值为字符串，那么这个命令会把 value 追加到原来值（value）的结尾。
     *               如果 key 不存在，那么它将首先创建一个空字符串的key，再执行追加操作
     * @param:
     * @return:      新的字符串长度
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public  Integer append(K key, String var){
        try {
            Integer data =   redisTemplate.opsForValue().append(key,var);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /** 
     * @description:   获取对应key start -end位置的子字符串
     * 　　　　　　　　　key:name, value :  "zxcvb"
     *                  get("name",0,2) --> "zxc"
     * @param:
     * @return:     子字符串
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public  String get(K key, long start, long end){
        try {
            String data =   redisTemplate.opsForValue().get(key,start,end);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }



    /** 
     * @description:  获取指定key的ｖalue的字符长度
     * @param:  
     * @return:     ｖalue长度
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public Long size(K key){
        try {
            Long data =   redisTemplate.opsForValue().size(key);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /** 
     * @description:  设置或者清空key的value(字符串)在offset处的bit值
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public
    Boolean setBit(K key, long offset, boolean value){
        try {
            Boolean data =   redisTemplate.opsForValue().setBit(key,offset,value);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /** 
     * @description:   获取offset位置的bit
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    public
    Boolean getBit(K key, long offset){
        try {
            Boolean data =   redisTemplate.opsForValue().getBit(key,offset);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
            RedisOperations<K, V> data =   redisTemplate.opsForValue().getOperations();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }




}
