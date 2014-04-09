package com.sniper.survey.custom.authentication;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.ant.FindLeaksTask;
import org.apache.struts2.interceptor.SessionAware;

public class AuthenticationService implements AuthenticationServiceInterface{

	private Map<String, Object> session = new HashMap<>();
	/**
	 * 登录session
	 */
	private String storage = "sniper_auth";

	/**
	 * 登录处理结果
	 */
	private BaseAdapterInterface adapter;

	@Override
	public AuthenticateResultInfoInterface authenticate(
			BaseAdapterInterface adapter) {

		if (adapter != null) {
			this.adapter = adapter;
		}
		if (this.adapter == null) {

		}
		AuthenticateResultInfoInterface resultInfoInterface = this.adapter.authenticate();
		
		if(this.hasIdentity()){
			this.clearIdentity();
		}
		if(resultInfoInterface.isValid()){
			this.session.put(getStorage(), resultInfoInterface.getObj());
		}
		return resultInfoInterface;

	}

	@Override
	public boolean hasIdentity() {
		if (session.get(getStorage()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Object getIdentity() {
		
		if (session.get(getStorage()) != null) {
			return session.get(getStorage());
		}
		return false;
	}

	@Override
	public void clearIdentity() {
		session.put(getStorage(), null);
		session.clear();
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;

	}

	public Map<String, Object> getSession() {
		return session;
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
