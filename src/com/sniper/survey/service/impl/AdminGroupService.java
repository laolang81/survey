package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.service.BaseService;

public interface AdminGroupService extends BaseService<AdminGroup> {

	
	public List<AdminGroup> getGroupSelectList(String where);
	
	public List<AdminGroup> getGroupList(Integer[] id);
}
