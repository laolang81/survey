package com.sniper.survey.custom.authentication;

public enum  Result implements ResultInterface{

	FAILURE(0),
	FAILURE_IDENTITY_NOT_FOUND(-1),
	FAILURE_IDENTITY_AMBIGUOUS(-2),
	FAILURE_CREDENTIAL_INVALID(-3),
	FAILURE_UNCATEGORIZED(-4),
	SUCCESS(1);
	
	private final Integer RESULT_VALUE;
	
	private Result(Integer value) {
		this.RESULT_VALUE = value;
	}

	@Override
	public Integer getCode() {
		
		switch (this) {
			case FAILURE:return 0;
			case FAILURE_IDENTITY_NOT_FOUND:return -1;
			case FAILURE_IDENTITY_AMBIGUOUS:return -2;
			case FAILURE_CREDENTIAL_INVALID:return -3;
			case FAILURE_UNCATEGORIZED:return -4;
			case SUCCESS:return 1;
		}
		return 0;
	}
	
	
}