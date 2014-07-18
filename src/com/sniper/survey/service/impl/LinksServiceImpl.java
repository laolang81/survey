package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Links;

@Service("linksService")
public class LinksServiceImpl extends BaseServiceImpl<Links> implements
		LinksService {

	@Resource(name = "linksDao")
	@Override
	public void setDao(BaseDao<Links> dao) {
		super.setDao(dao);
	}

}
