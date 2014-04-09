package com.sniper.survey.custom.authentication;

import org.apache.struts2.interceptor.SessionAware;

public interface AuthenticationServiceInterface extends SessionAware {
	/**
	 * Authenticates and provides an authentication result
	 * 
	 * @return Result
	 */
	public AuthenticateResultInfoInterface authenticate(BaseAdapterInterface adapter);

	/**
	 * Returns true if and only if an identity is available
	 * 
	 * @return bool
	 */
	public boolean hasIdentity();

	/**
	 * Returns the authenticated identity or null if no identity is available
	 * 
	 * @return mixed|null
	 */
	public Object getIdentity();

	/**
	 * Clears the identity
	 * 
	 * @return void
	 */
	public void clearIdentity();
}
