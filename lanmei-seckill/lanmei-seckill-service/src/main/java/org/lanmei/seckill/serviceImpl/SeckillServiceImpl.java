package org.lanmei.seckill.serviceImpl;

import java.util.List;

import org.lanmei.baseservice.BaseService;
import org.lanmei.seckill.dao.mapper.SeckillMapper;
import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.service.SeckillService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;

@Service
public class SeckillServiceImpl extends BaseService  implements SeckillService {
 
	
	private final static Logger logger = LoggerFactory.getLogger("SeckillServiceImpl.class");	
	{
		logger.debug("SeckillServiceImpl create bean ......");
	}
	
	
	@Autowired
	private SeckillMapper seckillMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	/**
	 * 创建秒杀活动
	 */
	@Override
	public Integer createSeckill(Seckill seckill) {
		// TODO Auto-generated method stub
		logger.debug("createSeckill");
		if(seckill == null) {
			return  0;
		}
		/*int com = seckill.getCreateTime().compareTo(seckill.getFinishTime());
		if((com == -1) || (com == 0)) {
			//秒杀已经结束
			seckill.setState((byte)2);
		}
		else {
			//秒杀未开始
			seckill.setState((byte)0);
		}*/
		logger.debug("createSeckill 插入数据库" + seckill.getCreateTime());
		if(seckillMapper == null ) {
			logger.debug("seckillMapper is null");
		}
		Integer num = seckillMapper.insert(seckill);
		if(num == 0) {
			logger.debug("新增秒杀活动失败");	
		}
		else if(num == 1) {
			logger.debug("新增秒杀活动成功");	
		}
		
		return num;
	}
	/**
	 * 根据seckillId删除秒杀活动
	 * @param seckillId
	 * @return
	 */
	@Override
	public Integer deleteSeckill(Integer seckillId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取位结束的秒杀活动列表
	 * @return
	 */
	@Override
	public List<Seckill> getSeckillingList() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取已经结束的秒杀活动列表
	 * @return
	 */
	@Override
	public List<Seckill> getSeckilledList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
