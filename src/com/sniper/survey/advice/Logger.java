package com.sniper.survey.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.model.Log;
import com.sniper.survey.service.impl.LogService;
import com.sniper.survey.util.StringUtil;
/**
 * 日志记录
 * pojos + xml 开发环绕通知 logger
 * @author laolang
 *
 */
public class Logger {

	@Resource
	private LogService logService;
	/**
	 * 处理连接点
	 * @param point
	 * @return
	 */
	public Object record(ProceedingJoinPoint point) {

		Log log = new Log();
		try {
			// 设置操作人
			
			ActionContext context = ActionContext.getContext();
			// 设置操作者
			if (context != null) {
				Map<String, Object> session = context.getSession();
				if (session != null) {
					AdminUser user = (AdminUser) session.get("user");
					if (user != null) {
						log.setUser("" + user.getId() + ":" + user.getEmail());
					}
				}
			}
			// 操作名称
			String mname = point.getSignature().getName();
			log.setName(mname);
			// 操作参数
			Object[] params = point.getArgs();
			
			log.setParams(StringUtil.arr2Str(params));
			// 调用操作目标
			Object ret = point.proceed();
			log.setResult("success");
			// 设置结果消息
			if (ret != null) {
				log.setResultMsg(ret.toString());
			}
			//返回结果
			return ret;
		} catch (Throwable e) {
			// 保存错误信息
			log.setResult("failure");
			log.setResultMsg(e.getMessage());
		} finally {
			System.out.println("----------->");
			System.out.println(log.getName());
			System.out.println(log.getParams());
			System.out.println(log.getResult());
			System.out.println(log.getResultMsg());
			System.out.println(log.getUser());
			System.out.println(log.getTime());
			System.out.println("<-----------");
			logService.saveEntiry(log);
		}
		return null;

	}
}
