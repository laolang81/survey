package survey;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.model.Log;
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
		Log log = new Log();
		log.setName("sssssss");
		log.setParams("sssssssssssss");
		log.setResult("sssssssss");
		log.setResultMsg("ssssssss");
		cs.saveEntiry(log);
		
		
	}
	
	
	

}
