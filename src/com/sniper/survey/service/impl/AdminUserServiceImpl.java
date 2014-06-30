package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.util.ValidateUtil;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements
		AdminUserService {

	@Resource(name = "adminUserDao")
	public void setDao(BaseDao<AdminUser> dao) {
		super.setDao(dao);
	}

	public List<AdminUser> getList() {
		return null;

	}

	/**
	 * 判断用户名是否注册
	 * 
	 * @param name
	 * @return
	 */
	public boolean isRegisted(String name) {
		String hql = "FROM AdminUser u where u.name = ?";

		List<AdminUser> adminUsers = this.findEntityByHQL(hql, name);
		return ValidateUtil.isValid(adminUsers) ? false : true;
	}
	
	@Override
	public AdminUser findByName(String username) {
		
		String hql = "from AdminUser where name = ?";
		return (AdminUser) this.uniqueResult(hql, username);
	}
	
	

}
