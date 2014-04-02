package com.sniper.survey.custom.rbac;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractChildren {

	// 角色列表
	protected Integer index = 0;
	protected static List<RoleInterface> childrens = new ArrayList<>();

	public RoleInterface current() {
		return childrens.get(index) != null ? childrens.get(index) : null;
	}

	public void next() {
		this.index++;
	}

	public Integer key() {
		return this.index;
	}

	public boolean valid() {
		return childrens.get(index) == null ? false : true;
	}

	public boolean hasChildrens() {
		if (this.valid() && (this.current() instanceof RoleInterface)) {
			return true;
		}
		return false;
	}

	public RoleInterface getchildren() {
		return childrens.get(index);
	}

	public List<RoleInterface> getChildrens() {
		return childrens;
	}

	public void addChildren(RoleInterface role) {
		childrens.add(role);
	}

}
