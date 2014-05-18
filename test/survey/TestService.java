package survey;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.AdminUser;
import com.sniper.survey.model.Tags;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.AdminUserService;
import com.sniper.survey.service.impl.TagsService;

public class TestService {

	private static AdminRightService cs;
	private static AdminUserService userService;
	private static TagsService tagsService;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		cs = (AdminRightService) ctx.getBean("adminRightService");
		userService = (AdminUserService) ctx.getBean("adminUserService");
		tagsService = (TagsService) ctx.getBean("tagsService");
		
	}
	
	@Test
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
