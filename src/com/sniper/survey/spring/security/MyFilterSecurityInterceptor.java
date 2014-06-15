package com.sniper.survey.spring.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 *  过滤用户请求
 * @author laolang
 *
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	// 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，  
    // 其他的两个组件，已经在AbstractSecurityInterceptor定义  
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
		invoke(filterInvocation);
		
	}

	private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
		
		// object为FilterInvocation对象  
        // super.beforeInvocation(fi);//源码  
        // 1.获取请求资源的权限  
         //执行 Collection<ConfigAttribute> attributes =   
                        //securityMetadataSource.getAttributes(fi);  
        // 2.是否拥有权限  
        // this.accessDecisionManager.decide(authenticated, fi, attributes);  
        // this.accessDecisionManager.decide(authenticated, fi, attributes);  
		InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
		try {
			filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			super.afterInvocation(token, null);
		}
	}
	
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
