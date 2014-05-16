package survey;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;







import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.AdminUserService;

public class TestService {

	private static AdminRightService cs;
	private static AdminUserService userService;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		cs = (AdminRightService) ctx.getBean("adminRightService");
		userService = (AdminUserService) ctx.getBean("adminUserService");
		
	}
	
	@Test
	public void save()
	{
		
		System.out.println("-------->");
		//System.out.println(cs.getEntity(1));
		//System.out.println(cs.findAllEntitles());
		List<AdminRight> rights = cs.findAllEntitles();
		for(AdminRight r: rights){
			//System.out.println(cs.getEntity(r.getId()));
		}
		System.out.println("<--------");
		
		AdminUser user = userService.getEntity(1);
		/*String name = user.getName();
		System.out.println("name->");
		System.out.println(name);
		System.out.println("getAdminGroup->");
		System.out.println(user.getAdminGroup());
		user.calucateRightNum();*/
		
		int maxPos =  cs.getMaxRightPos();
		user.setRightSum(new long[maxPos + 1]);
		// 初始化权限的综合
		user.calucateRightSum();
		System.out.println("getRightNum->");
		System.out.println(user.getRightSum());
		
		//System.out.println(userService);
		
	}
	
	
	

}
