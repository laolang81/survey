package com.sniper.survey.util;


public class StringUtil {

	public static String arr2Str(Object[] col){
		String str = "";
		
		if(ValidateUtil.isValid(col)){
			for(int i = 0; i < col.length; i++){
				str =  str + "," + col[i].toString();
			}
		}
		return str.substring(1);
	}
}
