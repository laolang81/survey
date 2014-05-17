package com.sniper.survey.custom.authentication;

import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.BaseService;

public class DbTable extends CredentialTreatmentAdapter<AdminUser> {

	public DbTable() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public DbTable(BaseService service, String identityColunm,
			String credentialcolumn, String credentialTreatment) {
		super(service, identityColunm, credentialcolumn, credentialTreatment);
	}
}
