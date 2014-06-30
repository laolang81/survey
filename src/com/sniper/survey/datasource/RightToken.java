package com.sniper.survey.datasource;

import com.sniper.survey.model.AdminRight;

/**
 * 令牌
 * 
 * @author laolang
 * 
 */
public class RightToken {

	private static ThreadLocal<RightToken> local = new ThreadLocal<>();

	private AdminRight right;

	public AdminRight getRight() {
		return right;
	}

	public void setRight(AdminRight right) {
		this.right = right;
	}

	/**
	 * 将指定的令牌对象绑定到当前线程
	 * 
	 * @param token
	 */
	public static void bindToken(RightToken token) {
		local.set(token);
	}

	/**
	 * 解除当前线程绑定的令牌
	 */
	public static void unbindToken() {
		local.remove();
	}

	/**
	 * 从当前线程获取绑定的令牌
	 * 
	 * @return
	 */

	public static RightToken getCurrentToken() {
		return local.get();
	}
}
