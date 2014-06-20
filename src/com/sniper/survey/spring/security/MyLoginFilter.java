package com.sniper.survey.spring.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

public class MyLoginFilter extends AbstractSecurityInterceptor {

	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return null;
	}

}
