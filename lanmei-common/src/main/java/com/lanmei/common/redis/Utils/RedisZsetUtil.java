package com.lanmei.common.redis.Utils;

import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Set;

/**
 * @program: demo
 * @description: redis zset 操作
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 15:59
 **/
public class RedisZsetUtil<K,V> {

    RedisTemplate<K, V> redisTemplate;
    ZSetOperations<K, V> zSetOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    public RedisZsetUtil(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    /** 
     * @description: Add value to a sorted set at key, or update its score if it already exists.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Boolean add(K key, V var, double score){
        try {
            Boolean flag = zSetOperations.add(key,var,score);
            return  flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** 
     * @description:   Add tuples to a sorted set at key, or update its score if it already exists.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Long add(K key, Set<ZSetOperations.TypedTuple<V>> tuples){
        try {
            Long num  = zSetOperations.add(key,tuples);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description: Remove values from sorted set. Return number of removed elements
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Long remove(K key, Object... var){
        try {
            Long num = zSetOperations.remove(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description: Increment the score of element with value in sorted set by increment.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Double incrementScore(K key, V var, double delta){
        try {
            Double d = zSetOperations.incrementScore(key, var,  delta);
            return d;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description: Determine the index of element with value in a sorted set.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Long rank(K key, Object var){
        try {
            Long num  = zSetOperations.rank(key,var);
            return num ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Determine the index of element with value in a sorted set when scored high to low.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Long reverseRank(K key, Object var){
        try {
            Long num= zSetOperations.reverseRank(key,var);
            return num ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * @description:       @description: Get elements between start and end from sorted set.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    @Nullable
    public Set<V> range(K key,
                        long start,
                        long end){
        try {
            Set<V> set  = zSetOperations.range(key,start,end);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:   Get set of RedisZSetCommands.Tuples between start and end from sorted set.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> rangeWithScores(K key, long start, long end){
        try {
            Set<ZSetOperations.TypedTuple<V>> set= zSetOperations.rangeWithScores(key,start,end);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description: Get elements where score is between min and max from sorted set.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Set<V> rangeByScore(K key, double min, double max){
        try {
            Set<V>  set = zSetOperations.rangeByScore(key,min,max);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:   Get set of RedisZSetCommands.Tuples where score is between min and max from sorted set.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max){
        try {
            Set<ZSetOperations.TypedTuple<V>> set = zSetOperations.rangeByScoreWithScores(key,min,max);
            return set ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:  Get elements in range from start to end where score is between min and max from sorted set.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Set<V> rangeByScore(K key,
                               double min,
                               double max,
                               long offset,
                               long count){
        try {
            Set<V> set  = zSetOperations.rangeByScore(key,min, max,  offset,  count);
            return set ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description:  Get set of RedisZSetCommands.Tuples in range from start to end where score
     *                is between min and max from sorted set.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> rangeByScoreWithScores(K key,
                                                                    double min,
                                                                    double max,
                                                                    long offset,
                                                                    long count){
        try {
            Set<ZSetOperations.TypedTuple<V>> set = zSetOperations.rangeByScoreWithScores(key,min, max,  offset,  count);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 
     * @description: Get elements in range from start to end from sorted set ordered from high to low.
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/28/18 
    */ 
    @Nullable
    public  Set<V> reverseRange(K key, long start, long end){
        try {
           Set<V> set  = zSetOperations.reverseRange(key,start,end);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get set of RedisZSetCommands.Tuples in range from start to end from sorted set ordered from high to low.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> reverseRangeWithScores(K key, long start, long end){
        try {
            Set<ZSetOperations.TypedTuple<V>> set = zSetOperations.reverseRangeWithScores(key,start,end);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get elements where score is between min and max from sorted set ordered from high to low.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<V> reverseRangeByScore(K key, double min, double max){
        try {
            Set<V> set = zSetOperations.reverseRangeByScore(key,min,max);
            return  set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get set of RedisZSetCommands.Tuple where score is between min and max from sorted set ordered from high to low.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public   Set<ZSetOperations.TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max){
        try {
            Set<ZSetOperations.TypedTuple<V>> set = zSetOperations.reverseRangeByScoreWithScores(key,min,max);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get elements in range from start to end where score is
     *               between min and max from sorted set ordered high -> low.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<V> reverseRangeByScore(K key,
                                      double min,
                                      double max,
                                      long offset,
                                      long count){
        try {
            Set<V> set = zSetOperations.reverseRangeByScore(key,min, max,offset,count);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get set of RedisZSetCommands.Tuple in range from start to end
     *               where score is between min and max from sorted set ordered high -> low.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> reverseRangeByScoreWithScores(K key,
                                                                           double min,
                                                                           double max,
                                                                           long offset,
                                                                           long count){
        try {
            Set<ZSetOperations.TypedTuple<V>> set  = zSetOperations.reverseRangeByScoreWithScores(key,min, max,offset,count);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Count number of elements within sorted set with scores between min and max.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long count(K key,
                      double min,
                      double max){
        try {
            Long num = zSetOperations.count(key,min,max);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Returns the number of elements of the sorted set stored with given key.
     * Parameters:
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long size(K key){
        try {
            Long size = zSetOperations.size(key);
            return size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get the size of sorted set with key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long zCard(K key){
        try {
            Long size = zSetOperations.zCard(key);
            return size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get the score of element with value from sorted set with key key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Double score(K key, Object var){
        try {
            Double score = zSetOperations.score(key,var);
            return score;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description:  Remove elements in range between start and end from sorted set with key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long removeRange(K key, long start, long end){
        try {
            Long num = zSetOperations.removeRange(key,start,end);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Remove elements with scores between min and max from sorted set with key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long removeRangeByScore(K key, double min, double max){
        try {
            Long num = zSetOperations.removeRangeByScore(key,min,max);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Union sorted sets at key and otherKeys and store result in destination destKey.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long unionAndStore(K key,
                              K otherKey,
                              K destKey){
        try {
            Long num = zSetOperations.unionAndStore( key, otherKey, destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Union sorted sets at key and otherKeys and store result in destination destKey.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long unionAndStore(K key, Collection<K> otherKeys, K destKey){
        try {
            Long num  = zSetOperations.unionAndStore(key, otherKeys, destKey);
            return num ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Intersect sorted sets at key and otherKey and store result in destination destKey.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long intersectAndStore(K key, K otherKey, K destKey){
        try {
            Long num  = zSetOperations.intersectAndStore(key, otherKey, destKey);
            return num ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Intersect sorted sets at key and otherKeys and store result in destination destKey.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey){
        try {
            Long num = zSetOperations.intersectAndStore(key, otherKeys, destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Iterate over elements in zset at key.
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public Cursor<ZSetOperations.TypedTuple<V>> scan(K key, ScanOptions options){
        try {
            Cursor<ZSetOperations.TypedTuple<V>> cursor = zSetOperations.scan(key,options);
            return cursor ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get all elements with lexicographical ordering from ZSET at key with a value
     * between RedisZSetCommands.Range.getMin() and RedisZSetCommands.Range.getMax().

     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<V> rangeByLex(K key, RedisZSetCommands.Range range){
        try {
            Set<V> set = zSetOperations.rangeByLex(key,range);
            return set ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get all elements n elements, where n = RedisZSetCommands.Limit.getCount(),
     * starting at RedisZSetCommands.Limit.getOffset() with lexicographical ordering from ZSET
     * at key with a value between RedisZSetCommands.Range.getMin() and RedisZSetCommands.Range.getMax().
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<V> rangeByLex(K key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit){
        try {
            Set<V> set = zSetOperations.rangeByLex(key,range,limit);
            return set;
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
    public RedisOperations<K, V> getOperations(){
        try {
            RedisOperations<K, V> operations = zSetOperations.getOperations();
            return operations;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
