package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Post;

@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {

	@Resource(name = "postDao")
	public void setDao(BaseDao<Post> dao) {
		
		super.setDao(dao);
	}
}
