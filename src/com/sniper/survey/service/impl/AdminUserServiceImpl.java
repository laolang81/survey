package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminUser;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {

	@Resource(name = "adminUserDao")
	public void setDao(BaseDao<AdminUser> dao) {
		super.setDao(dao);
	}
	
	public List<AdminUser>  getList()
	{
		return null;
		
	}
	
}
