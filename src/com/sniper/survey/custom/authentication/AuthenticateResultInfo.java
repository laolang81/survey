package com.sniper.survey.custom.authentication;

import java.util.ArrayList;
import java.util.List;

import com.sniper.survey.service.BaseService;

public class AuthenticateResultInfo implements AuthenticateResultInfoInterface {

	private ResultInterface code;
	private List<String> message = new ArrayList<>();
	private Object obj;

	public AuthenticateResultInfo() {
		super();
	}

	public AuthenticateResultInfo(ResultInterface resultInterface, List<String> message, Object obj) {
		super();
		this.code = resultInterface;
		this.message = message;
		this.obj = obj;
	}

	public ResultInterface getCode() {
		return code;
	}

	public void setCode(ResultInterface code) {
		this.code = code;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	@Override
	public boolean isValid() {

		return (code.getCode() > 0) ? true : false;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
