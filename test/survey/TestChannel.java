package survey;

import java.util.List;

import org.junit.Test;

import com.sniper.survey.model.Channel;
import com.sniper.survey.service.impl.BaseDao;

public class TestChannel {

	private BaseDao<Channel> baseDao;
	
	@Test
	public void getList()
	{
		List<Channel> lists = baseDao.qryInfo("FROM Channel");
		System.out.println(lists);
		
		//System.out.println("ssssssss");
	}
}
