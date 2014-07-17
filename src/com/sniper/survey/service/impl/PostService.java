package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.Post;
import com.sniper.survey.service.BaseService;

public interface PostService extends BaseService<Post> {

	public Post getAllEntity(Integer id);
	
	public List<Post> getCListByChannelID(Integer[] channelId, Integer limit);
}
