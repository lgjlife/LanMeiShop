package com.lanmei.admin.service;

import com.lanmei.admin.dao.model.CmsAdminLogin;
import com.lanmei.enumDefine.AdminStatus;

public interface CmsAdminLoginService {

	AdminStatus addAdminLoginLog(Integer adminId, CmsAdminLogin adminLogin );
}
