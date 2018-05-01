package org.lanmei.seckill.service;



import java.util.List;

import org.lanmei.seckill.dao.model.Seckill;
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
	Integer createSeckill(Seckill seckill);
	/**
	 * 根据seckillId删除秒杀活动
	 * @param seckillId
	 * @return
	 */
	Integer deleteSeckill(Integer seckillId);
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
}
