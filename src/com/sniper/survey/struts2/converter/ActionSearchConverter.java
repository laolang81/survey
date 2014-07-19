package com.sniper.survey.struts2.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class ActionSearchConverter extends StrutsTypeConverter {

	public ActionSearchConverter() {
		System.out.println("自定义类型转换器");
	}

	@Override
	public Object convertFromString(Map arg0, String[] values, Class toClass) {
		System.out.println("convertFromString");
		if (values == null || values.length == 0) {
			return null;
		}
		String value = values[0];

		if (toClass == Integer.class) {

			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				//e.printStackTrace();
				return 250;
			}
		}
		if (toClass == Boolean.class) {
			try {
				if(value.equals("1")){
					return true;
				}else{
					return false;
				}
				//return Boolean.parseBoolean(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object values) {
		System.out.println("convertToString");
		if (values instanceof Integer) {
			try {
				return String.valueOf(values);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (values instanceof Boolean) {
			if ((boolean) values) {
				return "1";
			} else {
				return "0";
			}

		}
		return null;
	}

}
