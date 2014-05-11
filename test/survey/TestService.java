package survey;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;





import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

public class TestService {

	private static AdminRightService cs;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		cs = (AdminRightService) ctx.getBean("adminRightService");
		System.out.println("ssssss");
	}
	
	@Test
	public void save()
	{
		
		System.out.println("-------->");
		//System.out.println(cs.getEntity(1));
		//System.out.println(cs.findAllEntitles());
		List<AdminRight> rights = cs.findAllEntitles();
		for(AdminRight r: rights){
			System.out.println(cs.getEntity(r.getId()));
		}
		System.out.println("<--------");
		
	}
	
	
	

}
