package com.sniper.survey.form.tag;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class formGroupTag extends SimpleTagSupport {

	 
	
	private String labelName;
	private int labelWidth;
	private String fieldName;
	private int fieldWidth;
	private String fieldType;
	private Collection fieldValue;
	private int offset;
	private String help;
	
	
	
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}



	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}



	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}



	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}



	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}



	public void setFieldValue(Collection fieldValue) {
		this.fieldValue = fieldValue;
	}



	public void setOffset(int offset) {
		this.offset = offset;
	}



	public void setHelp(String help) {
		this.help = help;
	}


	@Override
	public void setJspContext(JspContext pc) {
		super.setJspContext(pc);
	}

	@Override
	public void doTag() throws JspException, IOException {
		
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"form-group\">");
		
		html.append("</div>");
		
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		out.print(html.toString());
	}
	/**
	 * 
	 */
	private String getInput()
	{
		//return fieldName;
		if(this.fieldType == null){
			return null;
		}
		
		switch (this.fieldType) {
		case "input":
			return this.input();
		case "checkbox":
			return this.checkbox();
		case "select":
			return this.select();
		case "textarea":
			return this.textarea();
		default:
			break;
		}
		
		
		return null;
	}


	private String textarea() {
		
		return "<textarea placeholder=\"desc\" class=\"form-control\" id=\"desc\" rows=\"3\" cols=\"\" name=\"desc\"></textarea>";
	}



	private String select() {
		// TODO Auto-generated method stub
		return null;
	}



	private String checkbox() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 *  返回输入框
	 * @return
	 */
	private String input() {
		
		String html = "<input type=\"text\" placeholder=\""+this.fieldName+"\" class=\"form-control\" id=\""+this.fieldName+"\"  name=\""+this.fieldName+"\">";
		return html;
	}

}
