package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.MeetUser;

@Service("meetUserService")
public class MeetUserServiceImpl extends BaseServiceImpl<MeetUser> implements
		MeetUserService {

	@Override
	@Resource(name = "meetUserDao")
	public void setDao(BaseDao<MeetUser> dao) {
		super.setDao(dao);
	}

}
