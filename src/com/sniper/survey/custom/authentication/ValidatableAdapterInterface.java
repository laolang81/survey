package com.sniper.survey.custom.authentication;

public interface ValidatableAdapterInterface extends BaseAdapterInterface{

	 /**
     * Returns the identity of the account being authenticated, or
     * NULL if none is set.
     *
     * @return mixed
     */
    public String getIdentity();

    /**
     * Sets the identity for binding
     *
     * @param  mixed                       $identity
     * @return ValidatableAdapterInterface
     */
    public void setIdentity(String identity);

    /**
     * Returns the credential of the account being authenticated, or
     * NULL if none is set.
     *
     * @return mixed
     */
    public String getCredential();

    /**
     * Sets the credential for binding
     *
     * @param  mixed                       $credential
     * @return ValidatableAdapterInterface
     */
    public void setCredential(String credential);
}
