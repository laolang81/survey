package com.sniper.survey.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sniper.survey.model.Channel;

@Service("TestServices")
public class TestServicesImpl implements TestServices {

	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao ;

	@SuppressWarnings("unchecked")
	public List<Channel> qryKcsp() {
		return baseDao.qryInfo("from Down");
	}

	@SuppressWarnings("unchecked")
	public String del(String result) {
		try {
			Channel down = (Channel) baseDao.qryInfo("from Channel where id=?",
					new Object[] { result }).get(0);
			baseDao.Delete(down);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@SuppressWarnings("unchecked")
	public void add(Channel down) {
		Channel d = new Channel();	
		d.setStime(new Date());
		baseDao.add(d);
	}

	@SuppressWarnings("unchecked")
	public void upd(Channel down) {
		Channel down2 = (Channel) baseDao.qryInfo("from Channel where id=?",
				new Object[] { down.getId() });
		down2.setName(down.getName());
		baseDao.upd(down2);
	}
}
