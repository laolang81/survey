package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Post;

@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<Post> implements
		PostService {

	@Resource(name = "postDao")
	public void setDao(BaseDao<Post> dao) {

		super.setDao(dao);
	}

	/**
	 * 防止懒加载所有的懒加载都必须在service里面处理
	 */
	@Override
	public Post getAllEntity(Integer id) {
		Post post = super.getEntity(id);
		post.getPostValue();
		post.getChannels().size();
		return post;
	}

	/**
	 * 根据栏目id获取新闻
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getCListByChannelID(Integer[] channelId, Integer limit) {

		String hql = "select DISTINCT p from Post as p join p.channels as c  ";
		if (channelId.length != 0) {
			hql += " where c.id in( " + StringUtils.join(channelId, ",") + ")";
		}
		hql += getGroup();
		hql += getOrder();
		List<Post> list = this.findEntityByHQLQuery(hql).setMaxResults(limit)
				.list();
		// 去除重复,视频里面可以我这有错
		// list = new ArrayList<>(new LinkedHashMap(list));
		return list;
	}
}
