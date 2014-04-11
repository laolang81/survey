package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.WebUser;

@Service("webUserService")
public class WebUserServiceImpl extends BaseServiceImpl<WebUser> implements WebUserService {

	@Resource(name = "webUserDao")
	public void setDao(BaseDao<WebUser> dao) {
		
		super.setDao(dao);
	}
}
