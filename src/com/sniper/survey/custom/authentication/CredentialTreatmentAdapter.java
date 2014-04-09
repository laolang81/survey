package com.sniper.survey.custom.authentication;

import java.beans.IntrospectionException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import com.sniper.survey.service.BaseService;
import com.sniper.survey.util.BeanToMapUtil;

@SuppressWarnings("rawtypes")
public class CredentialTreatmentAdapter<T> extends AbstractAdapter<T> {

	private String credentialTreatment = null;

	public CredentialTreatmentAdapter() {
		super();
	}

	/**
	 * 构造器
	 * 用户参数赋值
	 * @param service
	 * @param identityColunm
	 * @param credentialcolumn
	 */
	public CredentialTreatmentAdapter(BaseService service,
			String identityColunm, String credentialcolumn,
			String credentialTreatment) {
		super(service, identityColunm, credentialcolumn);
		this.credentialTreatment = credentialTreatment;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected AuthenticateResultInfoInterface authenticateValidateResult(Map m) {
		
		
		
		if (m == null) {			
			this.authenticateResultInfo
					.setCode(Result.FAILURE_CREDENTIAL_INVALID);
			this.authenticateResultInfo.getMessage().add(
					"Supplied credential is invalid.");
			return authenticateCreateAuthResult();
		}
	
		
		/*T t = null;
		try {
			t = (T) BeanToMapUtil.convertMap(clazz, m, "au_");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("------------->");
		System.out.println(m);
		System.out.println(t);
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			
			AccessibleObject.setAccessible(fields, true);
			//System.out.println(fields);
			for(Field f: fields){
				System.out.println("===");
				System.out.println(f.getName());
				System.out.println(f.get(t));
				String fieldName = f.getName();
				if(fieldName.equals("auth")){
					int auth = f.getInt(t);
					System.out.println(auth);
				}
				System.out.println("===");
				
			}
			//System.out.println(t.getClass().getDeclaredField("auth"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("<-------------");*/
		//setModel(m);
		//密码正确
		//System.out.println(m.get("auth"));
		//System.out.println(m.get("auth").getClass().getName());
		BigInteger authbig =  (BigInteger) m.get("auth");
		Integer auth = authbig.intValue();
		
		if(auth != null && auth == 1){
			this.authenticateResultInfo.setObj(m);
			this.authenticateResultInfo.setCode(Result.SUCCESS);
			this.authenticateResultInfo.getMessage().add(
					"Authentication successful.");
		}else{
			
			this.authenticateResultInfo.setCode(Result.FAILURE_UNCATEGORIZED);
			this.authenticateResultInfo.getMessage().add(
					"Authentication faild.");
		}
		
		
		return authenticateCreateAuthResult();
	}

	@Override
	protected SQLQuery authenticateCreateSelect() {

		if (this.credentialTreatment == null
				|| (this.credentialTreatment.indexOf("?") == -1)) {
			this.credentialTreatment = "?";
		}

		String sqlExpr = "(CASE WHEN " + getCredentialcolumn() + " = " + this.credentialTreatment
				+ " THEN 1 ELSE 0 END) AS auth";


		String hql = "SELECT *, " + sqlExpr + " FROM "
				+ getTableName(clazz) + " as u WHERE "
				+ getIdentityColunm() + "= ? ";
		
		SQLQuery query = (SQLQuery) getService().findEntityBySQLQuery(hql)
				//.setResultTransformer(new AliasToBeanResultTransformer (clazz))
				//.addEntity("u", clazz)	
				//.addScalar("auth", IntegerType.INSTANCE)
				.setString(0, getCredential())
				.setString(1, getIdentity());
				
				//.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		/*System.out.println("1==>" + getCredentialcolumn());
		System.out.println("2==>" + getCredential());
		System.out.println("3==>" + getIdentity());
		List<Map> maps = query.list();
		
		for(Map m: maps){
			System.out.println("4=>>>");
			System.out.println(m);
		}
		
		
		hql =  "SELECT *, (CASE WHEN "+getCredentialcolumn()+" = \"" + getCredential()
				+ "\" THEN 1 ELSE 0 END) AS auth FROM "
				+ getTableName(clazz) + " as u WHERE "
				+ getIdentityColunm() + "=  \"" + getIdentity() + "\"";
		System.out.println(hql);*/
		
		return query;

	}
}
