package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminResource;

@Service("adminResourceService")
public class AdminResourceServiceImpl extends BaseServiceImpl<AdminResource> implements AdminResourceService {

	@Resource(name = "adminResourceDao")
	public void setDao(BaseDao<AdminResource> dao) {
		
		super.setDao(dao);
	}
	
	
	
}
