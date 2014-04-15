package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.WebUser;
import com.sniper.survey.service.BaseService;

public interface WebUserService extends BaseService<WebUser> {

	public List<WebUser> getUserList(int length, int start);
}
