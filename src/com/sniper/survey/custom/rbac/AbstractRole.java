package com.sniper.survey.custom.rbac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRole extends AbstractChildren implements
		RoleInterface {

	// 递归获取所有的父级
	List<RoleInterface> parents = new ArrayList<>();
	// 父及
	protected RoleInterface parent;
	// 当前角色名称
	protected String name;
	// 权限列表,
	protected Map<String, Boolean> perimissions = new HashMap<>();

	public String getName() {
		return name;
	}

	@Override
	public RoleInterface addPermission(String permission) {
		perimissions.put(permission, true);
		return this;
	}

	@Override
	public boolean hasPermission(String permission) {

		try {
			if (perimissions.get(permission) != false) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 添加角色
	 * 
	 * @param child
	 * @return
	 */
	public RoleInterface addChild(String child) {
		Role roleChild = new Role(child);
		roleChild.setParent(this);
		childrens.add(roleChild);
		return this;

	}

	/**
	 * 添加角色
	 */
	public RoleInterface addChild(RoleInterface child) {

		child.setParent(this);
		
		childrens.add(child);
		
		return this;

	}

	public RoleInterface getParent() {
		return parent;
	}

	public RoleInterface setParent(RoleInterface parent) {
		this.parent = parent;
		return this;
	}

	public List<RoleInterface> getParents(RoleInterface role) {

		if (role.getParent() != null) {
			parents.add(role.getParent());
			getParents(role.getParent());
		}
		return parents;
	}

	public List<RoleInterface> getParents() {
		return parents;
	}

}
