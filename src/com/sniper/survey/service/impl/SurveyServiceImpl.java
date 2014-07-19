package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Survey;

@Service("surveyService")
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements
		SurveyService {

	@Resource(name = "surveyDao")
	@Override
	public void setDao(BaseDao<Survey> dao) {
		super.setDao(dao);
	}

}
