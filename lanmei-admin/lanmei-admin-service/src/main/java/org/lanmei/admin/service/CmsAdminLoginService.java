package org.lanmei.admin.service;

import org.lanmei.admin.dao.model.CmsAdminLogin;
import org.lanmei.enumDefine.AdminStatus;

public interface CmsAdminLoginService {

	AdminStatus  addAdminLoginLog(Integer adminId,CmsAdminLogin adminLogin );	
}
