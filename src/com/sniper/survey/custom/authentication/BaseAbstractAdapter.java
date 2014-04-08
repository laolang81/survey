package com.sniper.survey.custom.authentication;

public abstract class BaseAbstractAdapter implements ValidatableAdapterInterface {

	private String credential;

	private String identity;

	@Override
	public String getIdentity() {
		return identity;
	}

	@Override
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String getCredential() {
		return this.credential;
	}

	@Override
	public void setCredential(String credential) {
		this.credential = credential;
	}

}
