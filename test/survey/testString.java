package survey;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.sniper.survey.util.DataUtil;

public class testString {

	@Test
	public void test() throws NoSuchAlgorithmException {
		String md5 = DataUtil.md5(DataUtil.md5("admin") + "1234");
		System.out.println(md5);
		
		Object integer = "true";
		String string = "12sss";
		
		System.out.println(Boolean.parseBoolean((String) integer));
		System.out.println(Integer.parseInt(string));
	}

}
