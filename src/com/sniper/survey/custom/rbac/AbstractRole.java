package com.sniper.survey.custom.rbac;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRole extends AbstractChildren implements RoleInterface {

	//父及
	protected RoleInterface parent;
	//当前角色名称
	protected String name;
	//权限列表,
	protected Map<String, Boolean> perimissions = new HashMap<>();
	
	
	public String getName() {
		return name;
	}

	@Override
	public void addPermission(String permission) {
		perimissions.put(permission, true);
	}

	@Override
	public boolean hasPermission(String permission) {
		if (perimissions.get(permission) != null) {
			return true;
		}
		return false;
	}

	public RoleInterface addChild(String child) {
		Role roleChild = new Role(child);

		roleChild.setParent(this);
		children.add(roleChild);
		return this;

	}
	
	public RoleInterface addChild(RoleInterface child) {
		child.setParent(this);
		children.add(child);
		return this;

	}

	public RoleInterface getParent() {
		return parent;
	}

	public void setParent(RoleInterface parent) {
		this.parent = parent;
	}

}
