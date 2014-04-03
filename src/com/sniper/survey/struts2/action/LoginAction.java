package com.sniper.survey.struts2.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sniper.survey.model.AdminUser;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<AdminUser> implements
		ServletRequestAware {

	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;

	public String index() throws IOException {
		String filePath = request.getServletContext().getRealPath("/");
		InputStream in = new FileInputStream(filePath
				+ "WEB-INF/file/shades.ttf");
		System.out.println(in.available());
		return "index";
	}

	public String login() {

		return "login";
	}

	public void logout() {

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;

	}
}