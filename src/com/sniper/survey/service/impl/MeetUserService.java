package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.MeetUser;
import com.sniper.survey.service.BaseService;

public interface MeetUserService extends BaseService<MeetUser> {

	public String getPageHtml();

	public List<MeetUser> adminList(int listRow, Object... Object);
}
