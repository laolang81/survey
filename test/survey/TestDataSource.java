package survey;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDataSource {

	@Test
	public void getConnection(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		SessionFactory ds = (SessionFactory) ctx.getBean("sessionFactory");
		System.out.println(ds);
	}
	
}
