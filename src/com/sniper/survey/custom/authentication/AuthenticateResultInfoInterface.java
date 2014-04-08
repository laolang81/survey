package com.sniper.survey.custom.authentication;

import java.util.List;

import com.sniper.survey.service.BaseService;

public interface AuthenticateResultInfoInterface {

	boolean isValid();

	void setCode(Result failure);

	Result getCode();

	void setMessage(List<String> message);

	List<String> getMessage();

	void setService(BaseService service);

	BaseService getService();
}
