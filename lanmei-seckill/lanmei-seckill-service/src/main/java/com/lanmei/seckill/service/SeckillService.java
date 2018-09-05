package com.lanmei.seckill.service;



import com.lanmei.seckill.dao.model.Seckill;
import com.lanmei.seckill.dto.ExecutionDto;
import com.lanmei.seckill.dto.ExposerDto;
import com.lanmei.seckill.exception.RepeatkillException;
import com.lanmei.seckill.exception.SeckillCloseException;
import com.lanmei.seckill.exception.SeckillException;
import com.lanmei.seckill.state.SeckillState;

import java.util.List;
/**
 * 
 * @author lgj
 *
 */
public interface SeckillService {
	/**
	 * 创建一个新的秒杀活动
	 * @param seckill
	 * @return
	 */	
	SeckillState createSeckill(Seckill seckill);
	/**
	 * 根据seckillId删除秒杀活动
	 * @param seckillId
	 * @return
	 */
	
	SeckillState deleteSeckill(Integer seckillId);
	
	/**
	 * 通过SeckillId获取Seckill
	 * @param seckillId
	 * @return
	 */
    Seckill getSeckill(int seckillId);
	/**
	 * 获取位结束的秒杀活动列表
	 * @return
	 */
	
	List<Seckill> getSeckillingList();
	/**
	 * 获取已经结束的秒杀活动列表
	 * @return
	 */
	List<Seckill> getSeckilledList();
	/**
	 * 获取秒杀地址
	 * @return
	 */
	ExposerDto getExposer(Integer seckillId);
	
	ExecutionDto executeSeckill(Integer seckillId ,String md5,Integer currentUserId)
		throws SeckillCloseException,RepeatkillException,SeckillException;
}
