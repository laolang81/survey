package survey;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.custom.authentication.AuthenticateResultInfoInterface;
import com.sniper.survey.custom.authentication.AuthenticationService;
import com.sniper.survey.custom.authentication.AuthenticationServiceInterface;
import com.sniper.survey.custom.authentication.DbTable;
import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.model.Tags;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.service.impl.TagsService;
import com.sniper.survey.util.DataUtil;

public class TestService {

	private static AdminRightService cs;
	private static AdminUserService userService;
	private static TagsService tagsService;
	private static AdminRightService rightService;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		cs = (AdminRightService) ctx.getBean("adminRightService");
		userService = (AdminUserService) ctx.getBean("adminUserService");
		tagsService = (TagsService) ctx.getBean("tagsService");
		rightService = (AdminRightService) ctx.getBean("adminRightService");
		
	}
	
	public void test()
	{
		String hql = "SELECT u, (case when password = md5(concat(?,rand)) and status=1 then 1 else 0 end) as u.auth FROM AdminUser as u WHERE name= ? ";
		System.out.println(userService.findEntityByHQL(hql, "admin", "admin"));
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void login() {
		
		System.out.println(TestService.class.getSimpleName());
		// 用户验证
		DbTable dbTable = new DbTable(userService, "au_name",
				"au_password", "md5(concat(?,au_rand)) and au_status=1");
		dbTable.setCredential(DataUtil.md5("admin"));
		dbTable.setIdentity("admin");

		// 调用用户验证,及其用户管理,需要注入session,否则无法完用户session的保存工作
		AuthenticationServiceInterface auth = new AuthenticationService();

		AuthenticateResultInfoInterface loginResult = auth
				.authenticate(dbTable);
		
		
		Map mapUser =  (Map) loginResult.getObj();
		System.out.println(mapUser);
		// 上面无法获取用户组信息，只能重新获取一次
		Integer id =  (Integer) mapUser.get("au_id");
		System.out.println(id);
		AdminUser user	 =  userService.getEntity(id);
		//获取最大权限位
		int maxPos =  rightService.getMaxRightPos();
		user.setRightSum(new long[maxPos + 1]);
		//计算权限综合
		user.calucateRightSum();
		System.out.println(user);

	}
	
	
	public void insert() {
		
		Session session = tagsService.getOpenSession();
		Transaction transaction = session.beginTransaction();
				
		for (int i = 0; i < 50000; i++) {
			Tags tags = new Tags();
			tags.setName("1111");
			tags.setUid(1);
			tags.setOrder(new Date().getTime());
			tags.setCtime(new Date());
			session.save(tags);
			if(i % 30 == 0){
				session.flush();
				session.clear();
			}
		}
		transaction.commit();		
		session.close();
	}
	
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
