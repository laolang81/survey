package survey;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.sniper.survey.util.App;

public class testString {

	@Test
	public void test() throws NoSuchAlgorithmException {
		String md5 = App.sniperMd5("sniper@8169k92");
		System.out.println(md5);
	}

}
