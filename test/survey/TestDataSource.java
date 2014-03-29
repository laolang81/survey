package survey;

import org.junit.Test;

import com.sniper.survey.dao.BaseDao;
import com.sniper.survey.model.Channel;

public class TestDataSource {

	BaseDao<Channel> baseDao;
	
	@Test
	public void getConnection(){
		
		Channel channel = baseDao.getEntity(1);
		System.out.println(channel);
		
	}
	
}
