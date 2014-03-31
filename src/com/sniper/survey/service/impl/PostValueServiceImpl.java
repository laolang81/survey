package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.PostValue;

@Service("postValue")
public class PostValueServiceImpl extends BaseServiceImpl<PostValue> implements PostValueService{

	@Resource(name = "postValueDao")
	public void setDao(BaseDao<PostValue> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
}
