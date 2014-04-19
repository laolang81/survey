/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Submit;

import com.opensymphony.xwork2.util.ValueStack;
import com.sniper.survey.util.ValidateUtil;

/**
 * @see Submit
 */
public class SubmitTag extends AbstractClosingTag {

	private static final long serialVersionUID = 2179281109958301343L;

	protected String action;
	protected String method;
	protected String align;
	protected String type;
	protected String src;

	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new Submit(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();

		Submit submit = ((Submit) component);
		submit.setAction(action);
		submit.setMethod(method);
		submit.setAlign(align);
		submit.setType(type);
		submit.setSrc(src);
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public int doEndTag() throws JspException {
		
		return hasRight()?super.doEndTag():SKIP_BODY;
		
	}

	@Override
	public int doStartTag() throws JspException {
		
		return hasRight()?super.doStartTag():SKIP_BODY;
	}
	/**
	 * 检查权限
	 * @return
	 */
	private boolean hasRight()
	{
		String ns = getFromNameSpace();
		String actionName = getFromAction();
		return (ValidateUtil.hasRight(ns, actionName, (HttpServletRequest)pageContext.getRequest(), null));
		
	}
	/**
	 * 获取当前表单的namespace
	 * 
	 * @return
	 */

	private String getFromNameSpace() {

		// 获取上级标签
		Tag ptag = this.getParent();
		while (ptag != null) {
			if (ptag instanceof FormTag) {
				return ((FormTag) ptag).namespace;
			} else {
				ptag = ptag.getParent();
			}
		}
		return null;
	}

	/**
	 * 获取所在表单的action name
	 * 
	 * @return
	 */
	private String getFromAction() {
		// 检查本身action是否有效
		if (ValidateUtil.isValid(action)) {
			return action;
		}
		// 获取上级标签,获取父级的action
		Tag ptag = this.getParent();
		while (ptag != null) {
			if (ptag instanceof FormTag) {
				return ((FormTag) ptag).action;
			} else {
				ptag = ptag.getParent();
			}
		}
		return null;
	}
	
	

}