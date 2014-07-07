package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.MeetUser;
import com.sniper.survey.util.StrutsPage;

@Service("meetUserService")
public class MeetUserServiceImpl extends BaseServiceImpl<MeetUser> implements
		MeetUserService {

	public String pageHtml;

	@Override
	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	@Override
	@Resource(name = "meetUserDao")
	public void setDao(BaseDao<MeetUser> dao) {
		super.setDao(dao);
	}

	@Override
	public List<MeetUser> adminList(int listRow, Object... Object) {

		List<MeetUser> meetUsers;

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
