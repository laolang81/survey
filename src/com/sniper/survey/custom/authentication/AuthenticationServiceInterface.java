package com.sniper.survey.custom.authentication;

public interface AuthenticationServiceInterface {
	/**
	 * Authenticates and provides an authentication result
	 * 
	 * @return Result
	 */
	public void authenticate();

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
	public boolean getIdentity();

	/**
	 * Clears the identity
	 * 
	 * @return void
	 */
	public void clearIdentity();
}
