package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminAccess;

@Service("adminAccessService")
public class AdminAccessServiceImpl extends BaseServiceImpl<AdminAccess> implements AdminAccessService {

	@Resource(name = "adminAccessDao")
	public void setDao(BaseDao<AdminAccess> dao) {
		
		super.setDao(dao);
	}
	
	
	
}
