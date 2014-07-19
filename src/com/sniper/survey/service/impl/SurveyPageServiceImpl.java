package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.SurveyPage;

@Service("surveyPageService")
public class SurveyPageServiceImpl extends BaseServiceImpl<SurveyPage>
		implements SurveyPageService {

	@Resource(name = "surveyPageDao")
	@Override
	public void setDao(BaseDao<SurveyPage> dao) {
		super.setDao(dao);
	}

}
