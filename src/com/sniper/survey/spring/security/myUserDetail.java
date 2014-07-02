package com.sniper.survey.spring.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;

/**
 * 用户读取用户的信息角色信息，账号是否过期
 * 
 * @author laolang
 * 
 */
public class myUserDetail implements UserDetailsService {

	@Resource
	private AdminUserService adminUserService;
	
	public myUserDetail(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		System.out.println("myUserDetailService:" + username);
		AdminUser adminUser = adminUserService.validateByName(username);
		if (adminUser == null) {
			throw new UsernameNotFoundException(username);
		}

		Collection<GrantedAuthority> authorities = obtionGrantedAuthorities(adminUser);

		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked	= false;
		if(!adminUser.isLocked()){
			accountNonLocked = true;
		}
		
		// 在数据库中获取信息之后赋值给他们,这里演示一下
		User user = new User(adminUser.getName(), adminUser.getPassword(),
				adminUser.isEnables(), accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);

		return user;
	}

	/**
	 * 取得用户的权限
	 * 获取当前用户可以查看的地址列表
	 * @param adminUser
	 * @return
	 */
	private Collection<GrantedAuthority> obtionGrantedAuthorities(
			AdminUser adminUser) {

		Set<GrantedAuthority> authSet = new HashSet<>();
		Set<AdminGroup> groups = adminUser.getAdminGroup();
		
		for (AdminGroup adminGroup : groups) {
			authSet.add(new SimpleGrantedAuthority(adminGroup.getValue()));
		}
		return authSet;
	}

}
