package survey;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JFreechar.class, TestAuth.class, TestDataSource.class,
		TestPoi.class, TestQuartz.class, TestRbac.class, TestService.class,
		TestSession.class, TestSpringSecurity.class, testString.class })
public class AllTests {

	
}
