package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminGroup;

@Service("adminGroupService")
public class AdminGroupServiceImpl extends BaseServiceImpl<AdminGroup> implements AdminGroupService {

	@Resource(name = "adminGroupDao")
	public void setDao(BaseDao<AdminGroup> dao) {
		
		super.setDao(dao);
	}
	
	
	
}
