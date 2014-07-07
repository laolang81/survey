package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.util.StrutsPage;
import com.sniper.survey.util.ValidateUtil;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements
		AdminUserService {

	public String pageHtml;

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}
	
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
	
	public List<AdminUser> adminList(int listRow, Object... Object) {

		List<AdminUser> meetUsers;

		String hql = "select count(a) from MeetUser a";
		long l = (long) this.uniqueResult(hql, Object);
		int totalNum = new Long(l).intValue();

		StrutsPage page = new StrutsPage(totalNum, listRow);
		pageHtml = page.show();

		String hql2 = "from MeetUser order by id desc";
		meetUsers = this.page(hql2, page.getFristRow(), page.getListRow(),
				Object);

		return meetUsers;
	}

}
