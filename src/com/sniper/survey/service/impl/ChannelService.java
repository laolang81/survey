package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.Channel;
import com.sniper.survey.service.BaseService;

public interface ChannelService extends BaseService<Channel> {

	
	public List<Channel> getChannelListById(Integer[] id);
}
