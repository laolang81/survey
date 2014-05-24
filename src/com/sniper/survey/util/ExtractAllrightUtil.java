package com.sniper.survey.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sniper.survey.service.impl.AdminRightService;

public class ExtractAllrightUtil {

	public static void main(String[] args) throws URISyntaxException {

		ServletContext context2 = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(context2);
		
		AdminRightService service = (AdminRightService) ac.getBean("adminRightService");
		
		ClassLoader classLoader = ExtractAllrightUtil.class.getClassLoader();
		String name = "com/sniper/survey/struts2/adminAction";
		URL url = classLoader.getResource(name);
		// System.out.println(url.toString());
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "";
		for (File f : files) {
			fname = f.getName();
			if (fname.endsWith(".class") && !fname.equals("BaseAction.class")) {
				processAction(fname, service);
			}
		}
	}

	/**
	 * 处理action,捕获所有的url地址,形成权限
	 * 
	 * @param fname
	 */
	private static void processAction(String fname, AdminRightService service) {
		//
		String pkgName = "com.sniper.survey.struts2.adminAction";
		// 获取类名
		String simpleClassName = fname.substring(0, fname.indexOf(".class"));
		String urlName = simpleClassName.substring(0,
				simpleClassName.indexOf("Action"));
		String className = pkgName + "." + simpleClassName;
		// 得到具体类
		try {
			Class clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class retType = null;
			String mName = null;
			Class[] paramType = null;
			String url = null;
			for (Method m : methods) {
				// 获取值类型
				retType = m.getReturnType();
				// 获取方法名称
				mName = m.getName();
				
				// 参数类型
				paramType = m.getParameterTypes();
				// getModifiers方法描述付,比如共有私有静态等
				if (retType == String.class && !ValidateUtil.isValid(paramType)
						&& Modifier.isPublic(m.getModifiers())) {
					
					System.out.println(mName);
					
					if (mName.equals("execute")) {
						url = "/" + DataUtil.toLowerCaseFirstOne(urlName);
					} else {
						url = "/" + DataUtil.toLowerCaseFirstOne(urlName)
								+ DataUtil.toUpperCaseFirstOne(mName);
					}
					//System.out.println(url);
					service.appendRightByURL(url);

				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
