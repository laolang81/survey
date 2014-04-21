package survey;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.service.impl.LogService;

public class TestService {

	private static LogService cs;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		cs = (LogService) ctx.getBean("logService");
	}
	@Test
	public void save()
	{
		System.out.println("-------->");
		System.out.println(cs.findNearesLogs(1));
		System.out.println("<--------");
		
	}
	
	
	

}
