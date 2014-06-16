package com.sniper.survey.spring.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 访问决策其，决定那个用户具有的角色，是否足够权限访问
 * http://wenku.baidu.com/link?url=ROOwPaHHFpU0PFEl7Tu-Xjihr-U9MMKez75b2YCyb8XCjp0_aAZjR5e8
 * _5DYonrKcTnZaUpvIK0gO6vFRhhjy2a9OnZAhhh2HVHmDCQuL_K
 * @author laolang
 *
 */
public class myAccessDecisionManagerBean implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		
		if(configAttributes == null){
			return;
		}
		//object is url
		System.out.println(object.toString());
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while(ite.hasNext()){
			ConfigAttribute ca = ite.next();
			SecurityConfig config = (SecurityConfig) ca;
			String needRole = config.getAttribute();
			for(GrantedAuthority ga:authentication.getAuthorities()){
				if(needRole.equals(ga.getAuthority())){
					//ga is user's role
					return;
				}
			}
		}
		
		throw new AccessDeniedException("no right");
		
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
