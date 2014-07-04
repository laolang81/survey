package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Files;
//15264112711
@Service("filesService")
public class FilesServiceImpl extends BaseServiceImpl<Files> implements
		FilesService {

	@Override
	@Resource(name = "filesDao")
	public void setDao(BaseDao<Files> dao) {
		super.setDao(dao);
	}
}
