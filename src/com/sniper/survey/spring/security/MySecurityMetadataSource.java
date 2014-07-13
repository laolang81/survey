package com.sniper.survey.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.sniper.survey.model.AdminGroup;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.util.PropertiesUtil;

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

	private static Map<String, Collection<ConfigAttribute>> rightMap = new HashMap<>();

	public void setAdminRightService(AdminRightService adminRightService) {
		this.adminRightService = adminRightService;
	}

	/**
	 * 加载所有资源与权限的关系
	 */
	@SuppressWarnings("unused")
	private void loadResourceDefine() {
		if (rightMap.isEmpty()) {

			rightMap = new HashMap<String, Collection<ConfigAttribute>>();
			// spring 自动缓存,
			List<AdminRight> adminRights = this.adminRightService.springRight();
			// 读取struts2的后缀配置

			PropertiesUtil propertiesUtil = new PropertiesUtil(
					"struts.properties");

			String extension = propertiesUtil
					.getValue("struts.action.extension");

			String[] list = extension.split(",");

			for (AdminRight right : adminRights) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<>();
				for (AdminGroup adminGroup : right.getAdminGroup()) {
					ConfigAttribute configAttribute = new SecurityConfig(
							adminGroup.getValue());
					configAttributes.add(configAttribute);
				}
				// 添加后缀
				
				for (int i = 0; i < list.length; i++) {
					String url = right.getUrl();
					// 找出不宜/结尾的url,加上.后缀
					if (!url.endsWith("/")) {
						url += "." + list[i];
					} else {
						// 由于 / 和/index访问的是一个地址,所以再次加一个链接
						url += "index." + list[i];
					}
					System.out.println(url);
					rightMap.put(url, configAttributes);
				}
				rightMap.put(right.getUrl(), configAttributes);
			}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return new ArrayList<ConfigAttribute>();
	}

	/**
	 * 返回所请求资源所需要的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		FilterInvocation filterInvocation = (FilterInvocation) object;
		HttpServletRequest request = filterInvocation.getHttpRequest();
		String requestUrl = filterInvocation.getRequestUrl();

		if (rightMap.isEmpty()) {
			loadResourceDefine();
		}

		if (requestUrl.indexOf("?") != -1) {
			requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf("?"));
		}

		RequestMatcher requestMatcher = new AntPathRequestMatcher(requestUrl);

		if (requestMatcher.matches(request)) {
			System.out.println(requestUrl);
			if (rightMap.get(requestUrl) != null) {
				System.out.println("通过");
				return rightMap.get(requestUrl);
			}
		}
		// throw new AccessDeniedException("errorNotRight");
		System.out.println("找不到记录");

		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
