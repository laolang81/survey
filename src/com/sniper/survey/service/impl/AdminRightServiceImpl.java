package com.sniper.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;






import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.util.DataUtil;
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
		long code = 1l;
		if(r.getId() == null){
			String hql = "from AdminRight r order by r.pos desc,r.code desc";
			List<AdminRight> rights = this.findEntityByHQL(hql);
			if(!ValidateUtil.isValid(rights)){
				pos = 0;
				code = 1l;
			}else{
				//得到上面的right
				AdminRight top = rights.get(0);
				int topPos = top.getPos();
				long topCode = top.getCode();
				//判断权限吗是否达到最大值
				if(topCode >= (1l << 60)){
					pos = topPos + 1;
					code = 1;
				}else{
					pos = topPos;
					code = topCode;
				}
				
			}
			r.setCode(code);
			r.setPos(pos);
		}
		this.saveOrUpdateEntiry(r);
		
	}

	
}
