package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.WebUser;

@Service("webUserService")
public class WebUserServiceImpl extends BaseServiceImpl<WebUser> implements
		WebUserService {

	@Resource(name = "webUserDao")
	public void setDao(BaseDao<WebUser> dao) {
		super.setDao(dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WebUser> getUserList(int length, int start) {
		String hql = "from WebUser c";
		System.out.println(length);
		System.out.println(start);
		List<WebUser> users = findEntityByHQLQuery(hql).setFirstResult(start)
				.setMaxResults(length).list();

		return users;
	}

}
