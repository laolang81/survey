package com.sniper.survey.datasource;

import com.sniper.survey.model.Survey;

/**
 * 令牌
 * @author laolang
 *
 */
public class SniperSurveyToken {

	private static ThreadLocal<SniperSurveyToken> local = new ThreadLocal<SniperSurveyToken>();
	
	private Survey survey;

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	/**
	 * 将指定的令牌对象绑定到当前线程
	 * @param token
	 */
	public static void bindToken(SniperSurveyToken token){
		local.set(token);
	}
	/**
	 * 解除当前线程绑定的令牌
	 */
	public static void unbindToken(){
		local.remove();
	}
	/**
	 * 从当前线程获取绑定的令牌
	 * @return
	 */

	public static SniperSurveyToken getCurrentToken()
	{
		return local.get();
	}
}
