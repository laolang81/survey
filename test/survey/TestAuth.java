package survey;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.util.DataUtil;

public class TestAuth {

	@Resource
	private AdminUserService adminUserService;
	
	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		adminUserService = (AdminUserService) ctx.getBean("adminUserService");
	}
	
	@Test
	public void test() {
		DbTable dbTable = new DbTable(adminUserService, "au_name", "au_password","MD5(CONCAT(?,au_rand)) AND au_status=1");
		dbTable.setCredential(DataUtil.md5("admin"));
		dbTable.setIdentity("admin");
		
		AuthenticationServiceInterface auth = new AuthenticationService();
		AuthenticateResultInfoInterface result =  auth.authenticate(dbTable);
		System.out.println(result.getCode());
		System.out.println(result.getObj().toString());
		
		AdminUser adminUser = (AdminUser) result.getObj();
		System.out.println(adminUser.getName());
		System.out.println(dbTable);
	}

}
