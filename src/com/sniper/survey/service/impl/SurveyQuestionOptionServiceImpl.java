package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.SurveyQuestionOption;

@Service("surveyQuestionOptionService")
public class SurveyQuestionOptionServiceImpl extends
		BaseServiceImpl<SurveyQuestionOption> implements
		SurveyQuestionOptionService {

	@Override
	@Resource(name = "surveyQuestionOptionDao")
	public void setDao(BaseDao<SurveyQuestionOption> dao) {
		super.setDao(dao);
	}

}
