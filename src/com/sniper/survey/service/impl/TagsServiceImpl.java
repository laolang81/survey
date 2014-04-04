package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Tags;

@Service("tagsService")
public class TagsServiceImpl extends BaseServiceImpl<Tags> implements TagsService {

	@Resource(name = "tagsDao")
	public void setDao(BaseDao<Tags> dao) {
		
		super.setDao(dao);
	}
}
