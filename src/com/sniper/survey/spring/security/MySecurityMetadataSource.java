package com.sniper.survey.spring.security;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 * 
 * @author sparta 11/3/29
 */
public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	public MySecurityMetadataSource() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object arg0)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
