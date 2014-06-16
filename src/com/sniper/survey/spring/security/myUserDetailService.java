package com.sniper.survey.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sniper.survey.model.WebUser;

/**
 * 用户读取用户的信息角色信息，账号是否过期
 * @author laolang
 *
 */
public class myUserDetailService implements UserDetailsService {

	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException , DataAccessException{
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_ADMIN");
		auths.add(auth2);
		
		if(username.equals("robin1")){
			auths = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_ADMIN");
			auths.add(auth1);
		}
		
		WebUser user = new WebUser();
		
		return (UserDetails) user;
	}

}
