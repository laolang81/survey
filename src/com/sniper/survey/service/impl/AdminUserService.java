package com.sniper.survey.service.impl;

import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.BaseService;

public interface AdminUserService extends BaseService<AdminUser> {

	public boolean isRegisted(String name);
}
