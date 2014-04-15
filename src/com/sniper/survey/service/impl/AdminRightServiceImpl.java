package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.util.ValidateUtil;

@Service("adminRightService")
public class AdminRightServiceImpl extends BaseServiceImpl<AdminRight> implements
		AdminRightService {

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
		if(r.getId() == null){
			
			String hql = "select max(a.pos),max(a.code) from AdminRight a "
					+ " where a.pos = (select max(aa.pos) from AdminRight aa )";
			
			Object[] arr = (Object[]) this.uniqueResult(hql); 
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];
			if(topPos == null){
				pos = 0;
				code = 1L;
			}else{
				//权限吗检测
				if(topCode >= (1L << 60)){
					pos = topPos + 1;
					code = 1L;
				}else{
					pos = topPos;
					code = topCode << 1;
				}
			}
			
			r.setCode(code);
			r.setPos(pos);
		}
		this.saveOrUpdateEntiry(r);
		
	}

	
}
