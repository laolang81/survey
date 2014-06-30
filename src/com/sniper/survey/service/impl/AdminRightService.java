package com.sniper.survey.service.impl;

import java.util.List;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.BaseService;

/**
 * 权限right
 * 
 * @author sniper
 * 
 */
public interface AdminRightService extends BaseService<AdminRight> {

	public void addRgiht();

	public void saveOrUpdate(AdminRight r);

	public void appendRightByURL(String url);
	
	public int getMaxRightPos();
	
	public List<AdminRight> springRight();
	
}
