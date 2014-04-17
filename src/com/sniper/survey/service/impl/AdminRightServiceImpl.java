package com.sniper.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminRight;

@Service("adminRightService")
public class AdminRightServiceImpl extends BaseServiceImpl<AdminRight>
		implements AdminRightService {

	@Resource(name = "adminRightDao")
	public void setDao(BaseDao<AdminRight> dao) {
		super.setDao(dao);
	}

	@Override
	public void addRgiht() {
		// TODO Auto-generated method stub

	}

	/**
	 * 保存更新操作
	 */
	@Override
	public void saveOrUpdate(AdminRight r) {
		int pos = 0;
		long code = 1L;
		if (r.getId() == null) {

			String hql = "select max(a.pos),max(a.code) from AdminRight a "
					+ " where a.pos = (select max(aa.pos) from AdminRight aa )";

			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];
			if (topPos == null) {
				pos = 0;
				code = 1L;
			} else {
				// 权限吗检测
				if (topCode >= (1L << 60)) {
					pos = topPos + 1;
					code = 1L;
				} else {
					pos = topPos;
					code = topCode << 1;
				}
			}

			r.setCode(code);
			r.setPos(pos);
		}
		this.saveOrUpdateEntiry(r);

	}
	/**
	 * 添加 url
	 */
	@Override
	public void appendRightByURL(String url) {

		String hql = "select count(*) from AdminRight r where r.url = ?";
		Long long1 = (Long) this.uniqueResult(hql, url);
		if (long1 == 0) {
			AdminRight right = new AdminRight();
			right.setUrl(url);
			this.saveOrUpdate(right);
		}

	}

}
