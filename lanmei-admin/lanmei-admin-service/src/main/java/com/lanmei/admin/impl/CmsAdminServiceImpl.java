package com.lanmei.admin.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.lanmei.admin.dao.mapper.CmsAdminMapper;
import com.lanmei.admin.dao.model.CmsAdmin;
import com.lanmei.admin.service.CmsAdminService;
import com.lanmei.common.baseservice.BaseService;
import com.lanmei.enumDefine.AdminStatus;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class CmsAdminServiceImpl  extends BaseService implements CmsAdminService {

	private final static Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");	
	{
		logger.debug("UserServiceImpl create bean ......");
	}
	@Autowired
	private CmsAdminMapper adminMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	
	
	
	@Override
	public AdminStatus addAdmin(CmsAdmin admin) {
		// TODO Auto-generated method stub
		
		Integer data =  adminMapper.insertNewAdmin(admin);
		
		/*adminMapper.selectAll();*/
	
		if(data == null) {
			
			logger.error("新增账户写入数据库失败");
			return  AdminStatus.ADMIN_CREATE_FAIL;
		}
		else {
		
			logger.error("新增账户写入数据库成功");
			return AdminStatus.ADMIN_CREATE_SUCCESS;
		}
	}




	@Override
	public CmsAdmin getAdminByJobnum(String jobnum) {
		
		CmsAdmin admin = adminMapper.selectByLoginJobnum(jobnum);
		if(admin == null) {			
			logger.error("账户不存在，账户工号：{}",jobnum);
			
		}
		else {		
			logger.debug("账户存在，账户工号：{}",jobnum);
			
		}
		return admin;
	}



	/**
	 * 分页获取管理员的
	 */
	@Override
	public List<CmsAdmin> getAllAdmin(Integer page) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(page, 5);
		List<CmsAdmin> adminList = adminMapper.selectAll();
		return adminList;
	}



	
	@Override
	public Integer getAdminCount() {
		Integer count = adminMapper.selectCount();
		return count;
	}
	
	
	
	
	
	
}
