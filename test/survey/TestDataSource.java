package survey;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDataSource {

	@Test
	public void getConnection(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		System.out.println(ds);
	}
	
}
