package org.lanmei.seckill.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.lanmei.common.baseservice.BaseService;
import org.lanmei.common.exception.UnloginException;
import org.lanmei.seckill.dao.mapper.SeckillMapper;
import org.lanmei.seckill.dao.mapper.SeckillSuccessMapper;
import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.dao.model.SeckillSuccess;
import org.lanmei.seckill.dto.ExecutionDto;
import org.lanmei.seckill.dto.ExposerDto;
import org.lanmei.seckill.exception.InnerException;
import org.lanmei.seckill.exception.RepeatkillException;
import org.lanmei.seckill.exception.SeckillCloseException;
import org.lanmei.seckill.exception.SeckillException;
import org.lanmei.seckill.service.SeckillService;
import org.lanmei.seckill.state.SeckillState;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 秒杀服务曾接口实现类
 * 
 * @author lgj
 *
 */
@Service
public class SeckillServiceImpl extends BaseService  implements SeckillService {
 
	
	private final static Logger logger = LoggerFactory.getLogger("SeckillServiceImpl.class");	
	{
		logger.debug("SeckillServiceImpl create bean ......");
	}
	
	
	@Autowired
	private SeckillMapper seckillMapper;
	@Autowired
	private SeckillSuccessMapper seckillSuccessMapper;
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	

	
	/* (non-Javadoc)
	 * @see org.lanmei.seckill.service.SeckillService#createSeckill(org.lanmei.seckill.dao.model.Seckill)
	 * @param seckill :  Seckill obj
	 * @return  SeckillState: 
	 *         NEW_SECKILL_FAIL : 创建秒杀活动失败
	 *         NEW_SECKILL_SUCCESS：创建秒杀活动成功
	 */
	@Override 
	public SeckillState createSeckill(Seckill seckill) {
		// TODO Auto-generated method stub
		logger.debug("createSeckill");
		if(seckill == null) {
			return  SeckillState.NULL_OBJECT;
		}

		logger.debug("createSeckill 插入数据库 = " + seckill.getCreateTime());

		Integer num = seckillMapper.insert(seckill);
		logger.debug("num = " + num);
		if(num == 0) {
			logger.debug("新增秒杀活动失败");	
			return SeckillState.NEW_SECKILL_FAIL;
		}
		else if(num == 1) {
			logger.debug("新增秒杀活动成功");	
			return SeckillState.NEW_SECKILL_SUCCESS;
		}
		
		return SeckillState.NEW_SECKILL_SUCCESS;
	}
	/**
	 * 根据seckillId删除秒杀活动
	 * @param seckillId
	 * @return 
	 */
	@Override
	public SeckillState deleteSeckill(Integer seckillId) {
		logger.debug("deleteSeckill");
		int  num = seckillMapper.deleteByPrimaryKey(seckillId);
		if(num == 0) {
			logger.debug("删除秒杀活动失败");	
			return SeckillState.DELETE_SECKILL_FAIL;
		}
		else {
			logger.debug("删除秒杀活动成功");	
			return SeckillState.DELETE_SECKILL_SUCCESS;
		}
	}
	/**
	 * 获取未结束的秒杀活动列表
	 * @return Seckill
	 */
	@Override
	public Seckill getSeckill(int seckillId) {
		
		Seckill seckill = seckillMapper.selectByPrimaryKey(seckillId);
		if(seckill ==  null) {
			logger.debug(" seckill is null");
		}		
		return seckill;
	}
	/**
	 * 获取未结束的秒杀活动列表
	 * @return List<Seckill>
	 */
	@Override
	public List<Seckill> getSeckillingList() {
		
		List<Seckill> seckill = seckillMapper.selectUnFinish(new Date());
		if(seckill ==  null) {
			logger.debug(" seckill is null");
		}		
		return seckill;
	}
	/**
	 *  获取已经结束的秒杀活动列表
	 * @return List<Seckill>
	 */
	@Override
	public List<Seckill> getSeckilledList() {
		List<Seckill> seckill = seckillMapper.selectFinish(new Date());
		if(seckill ==  null) {
			logger.debug(" seckill is null");
		}
		return seckill;
	}
	
	/**
	 * 获取秒杀地址接口
	 * @param  seckillId
	 * @return ExposerDto
	 */
	@Override
	public ExposerDto getExposer(Integer seckillId) {
		
		Seckill seckill = getSeckill(seckillId);
		if(seckill == null) {
			logger.debug("seckill is null");
			return (new ExposerDto(false,seckillId));

		}
		Date currentTime = new Date();
		if((currentTime.getTime() < seckill.getStartTime().getTime())
		 ||(currentTime.getTime() >= seckill.getFinishTime().getTime())){
			logger.debug("seckill is not the time");
			return (new ExposerDto(false,seckillId,
					currentTime.getTime(),
					seckill.getStartTime().getTime(),
					seckill.getFinishTime().getTime()));
		}
	
		String md5 = getMd5(seckillId);	
		return  (new ExposerDto(true,md5,seckillId));
	}
	/*事物管理，失败则回滚*/
   // @Transactional
	@Override
	public ExecutionDto executeSeckill(Integer seckillId ,String md5,Integer currentUserId) 
	       throws SeckillCloseException,RepeatkillException,SeckillException{
		// TODO Auto-generated method stub
		if((seckillId == null)|| (md5 == null) || (md5.equals(getMd5(seckillId)) == false) ){
			
			throw  new  SeckillException("秒杀请求异常");
		}
		
		Date currentTime = new Date();
		try {
			/*进行减库存操作*/
			Integer updateCount = seckillMapper.reduceStock(seckillId,currentTime);
			/*减库存失败*/
			if(updateCount == 0) {
				throw  new  SeckillCloseException("库存不足或者秒杀已经结束");
			}
			
			
			/*查询是否有记录*/
			SeckillSuccess seckillSuccess = seckillSuccessMapper.selectByPrimaryKey(seckillId, currentUserId);
			if(seckillSuccess != null) {
				//有记录
				throw  new  RepeatkillException("重复秒杀");
			}
			else {
				//无记录
				SeckillSuccess seckillSuccess1  =  new SeckillSuccess(seckillId,currentUserId,
						  SeckillState.SECKILL_SUCCESS.toString(),
						  currentTime);
				/*执行减库存成功，向秒杀成功表插入记录*/				
				int insertCount = seckillSuccessMapper.insertRecord(seckillSuccess1);
				if(insertCount == 1){
					/*秒杀操作成功*/
					Seckill seckill = seckillMapper.selectByPrimaryKey(seckillId);
					return new  ExecutionDto(seckillId,SeckillState.SECKILL_SUCCESS,seckill.getSeckillName());
				}
				else {
					throw  new  RepeatkillException("重复秒杀");
				}
			}		
		} 
		catch (SeckillCloseException e) {
			throw e;
		}
		catch (RepeatkillException e) {
			throw e;
		}
		catch (Exception e) {
			//其他异常
			throw  new  InnerException("秒杀出现内部异常 : " + e.getMessage());
		}		
	}
	
	/**
	 * 通过seckillId获取MD5值
	 */
	private String getMd5(Integer seckillId) {
		String random = seckillId + "lanmei" +  "salt";	
		//将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
		String resultMd5 = new Md5Hash(seckillId.toString().getBytes(),random,3).toString();
		return resultMd5;
	}
	
	
	

}
