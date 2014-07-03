package survey;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.sniper.survey.util.StrutsPage;

public class TestStrutsPage {

	@Test
	public void test() {

		ActionContext ctx = ActionContext.getContext();

		//HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

		//System.out.println(request);
		
		 StrutsPage page = new StrutsPage(100,20);
		// page.setRequest(request);
		 String string = page.show(); System.out.println(string);
		 
	}

}
