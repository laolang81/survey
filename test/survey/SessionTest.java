package survey;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class SessionTest implements SessionAware {

	private Map<String, Object> session;
	
	public SessionTest() {
		System.out.println(this.session);
	}
	
	public void getSession()
	{
		System.out.println(this.session);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	
}
