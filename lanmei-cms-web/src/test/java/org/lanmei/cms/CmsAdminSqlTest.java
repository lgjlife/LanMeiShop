package org.lanmei.cms;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.lanmei.admin.dao.mapper.CmsAdminMapper;
import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.common.BaseService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.pool.DruidDataSource;


@Transactional
public class CmsAdminSqlTest  extends BaseMapperTest {

	private final static Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");	
	{
		logger.debug("UserServiceImpl create bean ......");
	}
	
	ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-mybatis.xml");
	
	
	
	
	
	@Autowired
	private DruidDataSource dataSource; 
	
	@Test
	public void addAdmin() {
		// TODO Auto-generated method stub
		logger.debug("进行测试 ");
		SqlSession sqlsession = getSqlSession();
		
		CmsAdminMapper adminMapper = sqlsession.getMapper(CmsAdminMapper.class);
		 adminMapper.selectByPrimaryKey(1);
		/*System.out.println("name = " + read.getActualName() );*/
		
		CmsAdmin adminTest = new CmsAdmin();
		
		adminTest.setActualName("asd");
		Integer data =  adminMapper.insertNewAdmin(adminTest);
		
		logger.debug("data = " + data);
		
		
		
	}
	
}