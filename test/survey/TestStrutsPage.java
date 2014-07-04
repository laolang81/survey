package survey;

import java.io.File;

import org.junit.Test;


public class TestStrutsPage {

	@Test
	public void testName() throws Exception {
		File file = new File("WebRoot/data");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.isDirectory());
	}

}
