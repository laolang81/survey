package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.LinkGroups;

@Service("linkGroupsService")
public class LinkGroupsServiceImpl extends BaseServiceImpl<LinkGroups>
		implements LinkGroupsService {

	@Resource(name = "linkGroupsDao")
	@Override
	public void setDao(BaseDao<LinkGroups> dao) {
		super.setDao(dao);
	}
}
