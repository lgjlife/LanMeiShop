package org.lanmei.seckill.service;



import java.util.List;

import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.dto.ExecutionDto;
import org.lanmei.seckill.dto.ExposerDto;
import org.lanmei.seckill.exception.RepeatkillException;
import org.lanmei.seckill.exception.SeckillCloseException;
import org.lanmei.seckill.exception.SeckillException;
import org.lanmei.seckill.state.SeckillState;
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
