package org.lanmei.seckill.serviceImpl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring/applicationContext-mybatis.xml")
public class SeckillServiceImplTest {

	
	SeckillServiceImpl seckillService = new SeckillServiceImpl();
	
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
