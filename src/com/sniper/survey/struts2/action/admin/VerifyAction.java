package com.sniper.survey.struts2.action.admin;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sniper.survey.util.Verify;
import com.sniper.survey.util.VerifyCode;

/**
 * 提供图片验证码
 * @author laolang
 *
 */
@Controller
@Scope("prototype")
@Namespace("/")
@Result(name="success",type="stream",params={"contentType","image/jpeg","inputName","imageStream","bufferSize","2048"})
public class VerifyAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static final String VALIDATECODE = 	"sessionVerifyName";
	// Struts2中Map类型的session
	private Map<String, Object> session;
	
	private String d;

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	// 图片流
	private ByteArrayInputStream imageStream;

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Action("verify")
	public String execute() throws Exception {
		// 如果开启Hard模式，可以不区分大小写
		String securityCode = VerifyCode.getSecurityCode(5,
				VerifyCode.SecurityCodeLevel.Hard, false).toLowerCase();
		// 获取默认难度和长度的验证码
		// String securityCode = VerifyCode.getSecurityCode();
		imageStream = Verify.getImageAsInputStream(securityCode);
		// 放入session中
		session.put(VALIDATECODE, securityCode);
		return SUCCESS;
	}

}