package com.sniper.survey.custom.authentication;

import java.util.Map;

public class AuthenticationService implements AuthenticationServiceInterface {

	/**
	 * session 来源于struts2的注入
	 */
	private Map<String, Object> sessionMap;
	/**
	 * 登录session 储存用户session的名称,这个必须和userAware用的变量一样,和登录拦截其的用户获取名称一样
	 */
	private String storage = "user";

	/**
	 * 登录处理结果
	 */
	private BaseAdapterInterface adapter;

	public AuthenticationService() {
		super();

	}

	public AuthenticationService(Map<String, Object> sessionMap) {
		super();
		this.sessionMap = sessionMap;
	}

	@Override
	public AuthenticateResultInfoInterface authenticate(
			BaseAdapterInterface adapter) {

		if (adapter != null) {
			this.adapter = adapter;
		}
		AuthenticateResultInfoInterface resultInfoInterface = null;
		try {
			resultInfoInterface = this.adapter.authenticate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 由于session无法获取取消
		if (this.hasIdentity()) {
			this.clearIdentity();
		}
		if (resultInfoInterface.isValid()) {
			getSession().put(getStorage(), resultInfoInterface.getObj());
		}
		return resultInfoInterface;
	}

	@Override
	public boolean hasIdentity() {

		if (getSession().get(getStorage()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Object getIdentity() {

		if (getSession().get(getStorage()) != null) {
			return getSession().get(getStorage());
		}
		return false;
	}

	@Override
	public void clearIdentity() {
		getSession().put(getStorage(), null);
		getSession().clear();
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;

	}

	public Map<String, Object> getSession() {
		if (sessionMap == null) {
			throw new NullPointerException(
					"The session is empty, please call setSession (session) assignment");
		}
		return sessionMap;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public BaseAdapterInterface getAdapter() {
		return adapter;
	}

	public void setAdapter(BaseAdapterInterface adapter) {
		this.adapter = adapter;
	}

}
