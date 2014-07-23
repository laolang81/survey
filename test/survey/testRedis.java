package survey;

import org.junit.Test;

import com.sniper.survey.util.RedisUtil;

public class testRedis {

	@Test
	public void test() {
		
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setString("name", "value");
		System.out.println(redisUtil.getString("name"));
	}
}
