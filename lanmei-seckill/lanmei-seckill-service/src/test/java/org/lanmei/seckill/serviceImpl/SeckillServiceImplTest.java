package com.lanmei.seckill.serviceImpl;

import com.lanmei.seckill.dao.model.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring/applicationContext-mybatis.xml")
public class SeckillServiceImplTest {

	
	com.lanmei.seckill.serviceImpl.SeckillServiceImpl seckillService = new com.lanmei.seckill.serviceImpl.SeckillServiceImpl();
	
	@Test
	public void testCreateSeckill() {
		System.out.println("testCreateSeckill");
		Seckill seckill = new Seckill("手机抢购", 1000, new Date(), new Date(), new Date());
		seckillService.createSeckill(seckill);
	}
	@Test
	public void testDeleteSeckill() {
		
	}

	@Test
	public void testGetSeckillingList() {
		
	}

	@Test
	public void testGetSeckilledList() {
	
	}

}
