package com.sniper.survey.custom.authentication;

import java.util.List;

public interface AuthenticateResultInfoInterface {

	boolean isValid();

	void setCode(ResultInterface failure);

	ResultInterface getCode();

	void setMessage(List<String> message);

	List<String> getMessage();

	void setObj(Object obj);

	Object getObj();
}
