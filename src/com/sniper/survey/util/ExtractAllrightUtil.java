package com.sniper.survey.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;

public class ExtractAllrightUtil {

	public static void main(String[] args) throws URISyntaxException {

		ClassLoader classLoader = ExtractAllrightUtil.class.getClassLoader();
		String name = "com/sniper/survey/struts2/action";
		URL url = classLoader.getResource(name);
		// System.out.println(url.toString());
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "";
		for (File f : files) {
			fname = f.getName();
			if (fname.endsWith(".class") && !fname.equals("BaseAction.class")) {
				processAction(fname);
			}
		}
	}

	/**
	 * 处理action,捕获所有的url地址,形成权限
	 * 
	 * @param fname
	 */
	private static void processAction(String fname) {
		//
		String pkgName = "com.sniper.survey.struts2.action";
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
					if (mName.equals("execute")) {
						url = "/" + DataUtil.toLowerCaseFirstOne(urlName);
					} else {
						url = "/" + DataUtil.toLowerCaseFirstOne(urlName)
								+ DataUtil.toUpperCaseFirstOne(mName);
					}
					System.out.println(url);

				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
