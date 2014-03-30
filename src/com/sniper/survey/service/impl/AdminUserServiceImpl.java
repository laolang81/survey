package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.model.Channel;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {

	@Resource(name = "adminUserDao")
	public void setDao(BaseDao<AdminUser> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
}
