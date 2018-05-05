package org.lanmei.seckill.state;
/**
 * 秒杀相关的状态
 * @author lgj
 *
 */
public enum SeckillState {
	NULL_OBJECT,             //NULL对象
	
	NEW_SECKILL_SUCCESS,     //创建秒杀活动成功
	NEW_SECKILL_FAIL,		 //创建秒杀活动失败
	
	DELETE_SECKILL_SUCCESS,  //删除秒杀活动成功
	DELETE_SECKILL_FAIL,	 //删除秒杀活动失败
	
	SECKILL_NOT_START,       //秒杀活动未开始
	SECKILL_IN_PROGRESS,     //秒杀活动进行中
	SECKILL_FINISH,          //秒杀活动结束
	
	/*用于seckill_success表 state字段*/
	SECKILL_INVALID,        //秒杀无效
	SECKILL_SUCCESS,        //秒杀成功
	SECKILL_PAID_FINISH,    //秒杀支付完成
	SECKILL_DELIVER,        //秒杀发货
	SECKILL_TAKE_DELIVER,   //秒杀收货
	SECKILL_END,            //秒杀结束
	
	/*执行秒杀时的错误*/
	SECKILL_REQUEST_ERROOR,   //出现内部错误
	SECKILL_REPEAT,         //重复秒杀
	SECKILL_CLOSE,          //秒杀关闭
	SECKILL_INNER_ERROOR    //出现内部错误
}
