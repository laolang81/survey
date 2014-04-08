package com.sniper.survey.custom.authentication;

import java.util.List;

import com.sniper.survey.service.BaseService;

public class AuthenticateResultInfo implements AuthenticateResultInfoInterface {

	private Result code;
	private List<String> message;
	private BaseService service;

	public AuthenticateResultInfo() {
		super();
	}

	public AuthenticateResultInfo(Result code, List<String> message,
			BaseService service) {
		super();
		this.code = code;
		this.message = message;
		this.service = service;
	}

	public Result getCode() {
		return code;
	}

	public void setCode(Result code) {
		this.code = code;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public BaseService getService() {
		return service;
	}

	public void setService(BaseService service) {
		this.service = service;
	}

	@Override
	public boolean isValid() {

		return (code.getCode() > 0) ? true : false;
	}

}
