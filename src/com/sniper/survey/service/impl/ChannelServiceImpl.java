package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Channel;

@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<Channel> implements
		ChannelService {

	@Resource(name = "channelDao")
	public void setDao(BaseDao<Channel> dao) {

		super.setDao(dao);
	}

	@Override
	public List<Channel> getChannelListById(Integer[] id) {
		String hql = "from Channel where id in(" + StringUtils.join(id, ",")
				+ ")";

		return this.findEntityByHQL(hql);
	}

}
