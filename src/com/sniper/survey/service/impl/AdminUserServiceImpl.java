package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.ValidateUtil;
import com.sniper.survey.util.VerifyCode;
import com.sniper.survey.util.VerifyCode.SecurityCodeLevel;

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
	public AdminUser validateByName(String username) {

		String hql = "from AdminUser where name = ?";
		return (AdminUser) this.uniqueResult(hql, username);
	}

	/**
	 * 用户修改添加删除
	 */
	@Override
	public void saveOrUpdateEntiry(AdminUser t) {

		if (t.getPassword().length() != 32) {
			// 获取随机验证码
			String rand = VerifyCode.getSecurityCode(4,
					SecurityCodeLevel.Medium, false);

			String password = t.getPassword();
			password = DataUtil.md5(password) + rand;
			password = DataUtil.md5(password);

			t.setPassword(password);
			t.setRand(rand);
		}else{
			//设置原始密码，放置密码清空
			//AdminUser adminUser = this.validateByName(t.getName());
			//t.setPassword(adminUser.getPassword());
			//adminUser = null;
		}
		super.saveOrUpdateEntiry(t);
	}

}
