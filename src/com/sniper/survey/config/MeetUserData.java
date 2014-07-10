package com.sniper.survey.config;

import java.util.HashMap;
import java.util.Map;

public class MeetUserData {

	public static Map<Integer, String> getSex() {
		
		Map<Integer, String> list = new HashMap<>();
		list.put(0,"男");
		list.put(1,"女");
		return list;
	}
	
}
