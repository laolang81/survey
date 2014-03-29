package survey;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.model.Channel;
import com.sniper.survey.service.impl.ChannelService;

public class TestDataSource {

	private static ChannelService cs;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		cs = (ChannelService) ctx.getBean("channelService");
	}

	@Test
	public void getConnection() {

		Channel channel = new Channel();
		
		//System.out.println(cs.getEntity(1));
	}

}
