package com.sniper.survey.custom.authentication;

import org.hibernate.Query;

public class CallbackCheckAdapter extends AbstractAdapter {

	/**
	 * 查询用户的列表
	 */
	@Override
	protected Query authenticateCreateSelect() {
		String tname = getModel().getClass().getSimpleName();
		String hql = "SELECT auth FROM " + tname + " auth WHERE "
				+ getIdentityColunm() + "= ? ";
		Query query = getService().findEntityByHQLQuery(hql, getIdentity());
		return query;
	}

	@Override
	protected AuthenticateResultInfoInterface authenticateValidateResult(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

}
