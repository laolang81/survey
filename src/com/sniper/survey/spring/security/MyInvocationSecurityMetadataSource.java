package com.sniper.survey.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 载入资源
 * @author laolang
 *
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	
	
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	public MyInvocationSecurityMetadataSource() {
		loadResourceDeine();
	}
	/**
	 * 
	 */
	private void loadResourceDeine() {
		
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		//创建角色
		ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");
		atts.add(ca);
		resourceMap.put("/index", atts);
		resourceMap.put("/i.jsp", atts);
		
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) object;
		HttpServletRequest request = filterInvocation.getHttpRequest();
				
		Iterator<String> ite = resourceMap.keySet().iterator();
		
		if(ite.hasNext()){
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			if(requestMatcher.matches(request)){
				return resourceMap.get(resURL);
			}
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
