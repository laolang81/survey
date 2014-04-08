package com.sniper.survey.custom.authentication;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class AuthenticationService implements AuthenticationServiceInterface,
		SessionAware {

	private Map<String, Object> session = new HashMap<>();
	/**
	 * 登录session
	 */
	private String storage;
	
	/**
	 * 登录处理结果
	 */
	private ValidatableAdapterInterface adapter;

	@Override
	public void authenticate() {

	}

	@Override
	public boolean hasIdentity() {
		return false;
	}

	@Override
	public boolean getIdentity() {
		return false;
	}

	@Override
	public void clearIdentity() {
		
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

}
