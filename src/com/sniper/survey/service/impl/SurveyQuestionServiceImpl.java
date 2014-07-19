package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.SurveyQuestion;

@Service("surveyQuestionServie")
public class SurveyQuestionServiceImpl extends BaseServiceImpl<SurveyQuestion>
		implements SurveyQuestionServie {

	@Resource(name = "surveyQuestionDao")
	@Override
	public void setDao(BaseDao<SurveyQuestion> dao) {
		super.setDao(dao);
	}
}
