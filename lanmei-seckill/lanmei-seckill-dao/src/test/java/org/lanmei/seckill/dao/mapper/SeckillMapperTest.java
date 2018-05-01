package org.lanmei.seckill.dao.mapper;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanmei.seckill.dao.model.Seckill;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;


@RunWith(UnitilsJUnit4TestClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class SeckillMapperTest {

	private SeckillMapper seckillMapper;
	private static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUpBeforeClass() {
	   ctx = new ClassPathXmlApplicationContext("classpath*:spring-mybatis-unitils.xml");
	}
 
    @Before
    public void setUp() {
    	seckillMapper = (SeckillMapper) ctx.getBean("seckillMapper");
    }
	
	  
	

	@Test
    @DataSet(value = {"test.xls"}) 
	public void testInsert() {
		System.out.println("testInsert.....");
		Seckill seckill = new Seckill("手机", 2511, new Date(), new Date(), new Date());
		Integer ret = seckillMapper.insert(seckill);
		System.out.println("ret = " + ret);
		
	}

	/*@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}
*/
}
