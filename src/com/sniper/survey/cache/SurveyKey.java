package com.sniper.survey.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.sniper.survey.util.StringUtil;

public class SurveyKey implements KeyGenerator {

	@Override
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		
		String className = arg0.getClass().getSimpleName();
		String mName = arg1.getName();
		String params = StringUtil.arr2Str(arg2);
		String key = className + "." + mName + "(" + params + ")";
		System.out.println(key);
		return key;
	}

}
