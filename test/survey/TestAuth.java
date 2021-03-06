package survey;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.model.WebUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.service.impl.WebUserService;
import com.sniper.survey.util.DataUtil;
import com.sniper.survey.util.VerifyCode;

public class TestAuth {

	@Resource
	private WebUserService webUserService;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		webUserService = (WebUserService) ctx.getBean("webUserService");
	}

	
	
	
	@Test
	public void testInsert(){
		//插入数据1000条作为测试
		 
		
		 String code;
		 for(int i = 0; i < 1000; i++){
			 code = VerifyCode.getSecurityCode();		
			 if(webUserService.findEntityByHQL("from WebUser w where nickName=?", code).size()>0){
				 continue;
			 }
			 WebUser user = new WebUser();
			 user.setNickName(code);
			 user.setCtime(new Date());
			 user.setEmail(code + "@126.com");
			 user.setPassword(DataUtil.md5(code));
			
			 webUserService.saveEntiry(user);
			 
		 }
		 
	}
	
	public void test() {

		/*System.out.println(DataUtil.md5("admin"));
		String sql = "SELECT *, (CASE WHEN au_password = ? THEN 1 ELSE 0 END) AS auth FROM mc_admin_user AS u WHERE au_name=  ?";
		List<Map> maps = adminUserService.findEntityBySQLQuery(sql)
				.setString(0, "21232F297A57A5A743894A0E4A801FC3")
				.setString(1, "admin")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		for (Map m : maps) {
			System.out.println(m);
			String name = m.getClass().getSimpleName();
			System.out.println(name);

		}*/

		 /*DbTable dbTable = new DbTable(adminUserService, "au_name",
		 "au_password","MD5(CONCAT(?,au_rand)) AND au_status=1");
		 
		 System.out.println(DataUtil.md5("21232F297A57A5A743894A0E4A801FC31456").toLowerCase());
		DbTable dbTable = new DbTable(adminUserService, "au_name",
				"au_password", null);
		 System.out.println(DataUtil.md5("admin").toLowerCase());
		dbTable.setCredential(DataUtil.md5("admin"));
		dbTable.setIdentity("admin");

		AuthenticationServiceInterface auth = new AuthenticationService();
		AuthenticateResultInfoInterface result = auth.authenticate(dbTable);
		System.out.println(result.getCode());
		System.out.println(result.getObj().toString());
		
		AdminUser adminUser = (AdminUser) result.getObj();
		System.out.println(adminUser.getName());
		System.out.println(adminUser.getNickName());
		System.out.println(dbTable);*/
	}

}
