package com.lanmei.common.redis.Utils;

import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: demo
 * @description: redis hash  操作
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 15:57
 **/
public class RedisHashUtil<H, HK, HV>{

    RedisTemplate<H, HV> redisTemplate;
    HashOperations<H, HK, HV> hashOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    public RedisHashUtil(RedisTemplate<H, HV> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    /**
     * @description: Delete given hash hashKeys.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public Long delete(H key, Object... var){
        try {
            Long num  = hashOperations.delete(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } 
    }
    /**
     * @description: Determine if given hash hashKey exists.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Boolean hasKey(H key, Object var){
        try {
            Boolean flag = hashOperations.hasKey(key,var);
            return  flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get value for given hashKey from hash at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public HV get(H key, Object var){
        try {
            HV value  = hashOperations.get(key,var);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get values for given hashKeys from hash at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public List<HV> multiGet(H key, Collection<HK> var){
        try {
            List<HV> list = hashOperations.multiGet(key,var);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Increment value of a hash hashKey by the given delta.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Long increment(H key, HK hashKey, long delta){
        try {
            Long num  = hashOperations.increment(key,hashKey,delta);
            return  num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Increment value of a hash hashKey by the given delta.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Double increment(H key, HK hashKey, double delta){
        try {
            Double num = hashOperations.increment(key,hashKey,delta);
            return  num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get key set (fields) of hash at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Set<HK> keys(H key){
        try {
            Set<HK> set = hashOperations.keys(key);
            return  set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get size of hash at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Long size(H key){
        try {
            Long size  = hashOperations.size(key);
            return  size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Set multiple hash fields to multiple values using data provided in var.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public void putAll(H key, Map<? extends HK, ? extends HV> var){
        try {
              hashOperations.putAll(key,var);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @description: Set the value of a hash hashKey.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public void put(H key, HK hashKey, HV value){
        try {
             hashOperations.put(key,hashKey,value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @description: Set the value of a hash hashKey only if hashKey does not exist.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Boolean putIfAbsent(H key, HK hashKey, HV value){
        try {
            Boolean flag = hashOperations.putIfAbsent(key,hashKey,value);
            return flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get entry set (values) of hash at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public List<HV> values(H key){
        try {
            List<HV> list  = hashOperations.values(key);
            return  list;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get entire hash stored at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Map<HK, HV> entries(H key){
        try {
            Map<HK, HV>  map  = hashOperations.entries(key);
            return  map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Use a Cursor to iterate over entries in hash at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options){
        try {
            Cursor<Map.Entry<HK, HV>> cursor  = hashOperations.scan(key,options);
            return  cursor;

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
     * @date: 8/28/18
     */
    public RedisOperations<H, ?> getOperations(){
        return hashOperations.getOperations();
    }



}
