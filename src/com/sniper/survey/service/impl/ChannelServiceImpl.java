package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Channel;

@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<Channel> implements ChannelService {

	@Resource(name = "channelDao")
	public void setDao(BaseDao<Channel> dao) {
		
		super.setDao(dao);
	}

	
}
