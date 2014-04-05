package com.sniper.survey.service.impl;

import java.util.List;

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

	@Override
	public List<AdminGroup> getGroupSelectList() {
		String hql = "SELECT new AdminGroup(g.value, g.name) FROM AdminGroup g";
		List<AdminGroup> adminGroups = this.findEntityByHQL(hql);
		return adminGroups;
	}
	
	
	
}
