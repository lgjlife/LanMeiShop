package org.lanmei.admin.service;

import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.enumDefine.AdminStatus;

public interface CmsAdminService {

	AdminStatus  addAdmin(CmsAdmin admin);
}
