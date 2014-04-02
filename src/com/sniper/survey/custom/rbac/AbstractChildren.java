package com.sniper.survey.custom.rbac;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractChildren {

	// 下级角色列表
	protected Integer index = 0;
	protected List<RoleInterface> children = new ArrayList<>();

	public RoleInterface current() {

		return children.get(index);
	}

	public void next() {
		this.index++;
	}

	public Integer key() {
		return this.index;
	}

	public boolean valid() {
		return children.get(index) == null ? false : true;
	}

	public boolean hasChildren() {
		if (this.valid() && (this.current() instanceof RoleInterface)) {
			return true;
		}
		return false;
	}
	public RoleInterface getChildren(){
		return children.get(index);
	}
}
