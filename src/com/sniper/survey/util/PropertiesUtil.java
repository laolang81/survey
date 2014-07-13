package com.sniper.survey.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	private Properties properties = null;

	private String propertiesPath;

	public PropertiesUtil(String propertiesPath) {
		super();
		this.propertiesPath = propertiesPath;
		read();
	}

	private void read() {
		InputStream inputStream = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream(this.propertiesPath);
		properties = new Properties();
		try {
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		String value = properties.getProperty(key);
		return value;
	}

	public Map<String, String> getAllValue() {
		Map<String, String> list = new HashMap<>();
		@SuppressWarnings("rawtypes")
		Enumeration en = properties.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String value = properties.getProperty(key);
			list.put(key, value);
		}

		return list;

	}

}
