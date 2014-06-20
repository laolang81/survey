package com.sniper.survey.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

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

	@Resource
	private AdminRightService adminRightService;
	
	private static Map<String, Collection<ConfigAttribute>> rightMap = null;
	
	public MySecurityMetadataSource() {
	}

	public MySecurityMetadataSource(AdminRightService adminRightService) {
		this.adminRightService = adminRightService;
		//loadResourceDefine();
	}
	/**
	 * 加载所有资源与权限的关系  
	 */
	private void loadResourceDefine() {
		if (rightMap == null) {
			
			rightMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<AdminRight> adminRights = this.adminRightService
					.findAllEntitles();
			
			for (AdminRight right : adminRights) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<>();
				ConfigAttribute configAttribute = new SecurityConfig(
						right.getUrl());
				configAttributes.add(configAttribute);
				rightMap.put(right.getUrl(), configAttributes);
			}
		}

		Set<Entry<String, Collection<ConfigAttribute>>> rightSet = rightMap
				.entrySet();
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = rightSet
				.iterator();

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	/**
	 * 返回所请求资源所需要的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String requestUrl = filterInvocation.getRequestUrl(); 
        System.out.println("requestUrl is " + requestUrl);  
        if(rightMap == null) {  
            loadResourceDefine();  
        }  
        return rightMap.get(requestUrl);
        
        
		/*FilterInvocation filterInvocation = (FilterInvocation) object;
		HttpServletRequest request = filterInvocation.getHttpRequest();

		Iterator<String> ite = rightMap.keySet().iterator();

		if (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			if (requestMatcher.matches(request)) {
				return rightMap.get(resURL);
			}
		}

		return null;*/
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
