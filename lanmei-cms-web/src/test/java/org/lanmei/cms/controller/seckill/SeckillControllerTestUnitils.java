package org.lanmei.cms.controller.seckill;

import java.util.Date;

import org.junit.Test;
import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.serviceImpl.SeckillServiceImpl;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
//@TransactionConfiguration( transactionManager = "transactionManager",defaultRollback = true)
//@Transactional
/*@RunWith(SpringJUnit4ClassRunner.class)//此处调用Spring单元测试类
@WebAppConfiguration  //调用javaWEB的组件，比如自动注入
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml","classpath:springmvc/spring-mvc.xml"})//加载spring容器
*/
public class SeckillControllerTestUnitils  extends UnitilsTestNG {

	
	@SpringApplicationContext({"spring/spring-context.xml","springmvc/spring-mvc.xml"})
	private ApplicationContext applicationContext;
	
	@SpringBean("seckillServiceImpl")
	private SeckillServiceImpl seckillServiceImpl;
	
	
	@Test
	public void toTestcreateSeckill() {
		System.out.println("toTestcreateSeckill");
		Seckill seckill = new Seckill("手机秒杀", 100, new Date(), new Date(), new Date());
		System.out.println("seckill = " + seckill.getCreateTime());
		SeckillServiceImpl seckillService = new SeckillServiceImpl();
		seckillService.createSeckill(seckill);
		
	}

}
