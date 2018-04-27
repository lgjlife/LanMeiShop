package org.lanmei.admin.service;

import java.util.List;

import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.enumDefine.AdminStatus;

public interface CmsAdminService {

	AdminStatus  addAdmin(CmsAdmin admin);
	
	CmsAdmin getAdminByJobnum(String jobnum);
	/**
	 * 根据页数分页获取管理员的数据
	 * @param page 第几页
	 * @return
	 */
	List<CmsAdmin> getAllAdmin(Integer page);
	

	/**
	 * 获取所有管理员的数量
	 * @return
	 */
	Integer getAdminCount();
}
