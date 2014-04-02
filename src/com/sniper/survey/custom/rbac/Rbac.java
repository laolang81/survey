package com.sniper.survey.custom.rbac;

import java.util.List;

/**
 * 角色功能主要实现
 * 
 * @author sniper
 * 
 */
public class Rbac extends AbstractChildren {

	protected boolean createMissingRoles = false;

	public boolean isCreateMissingRoles() {
		return createMissingRoles;
	}

	public Rbac setCreateMissingRoles(boolean createMissingRoles) {
		this.createMissingRoles = createMissingRoles;
		return this;
	}

	/**
	 * 添加角色
	 * 
	 * @param childName
	 * @return
	 */
	public Rbac addRole(String childName) {
		if (childName != null) {
			RoleInterface child = new Role(childName);
			childrens.add(child);
		}
		return this;
	}

	/**
	 * 添加
	 * 
	 * @param childName
	 * @param parent
	 * @return
	 */
	public Rbac addRole(String childName, String parent) {

		RoleInterface child = new Role(childName);

		if (parent != null) {
			if (isCreateMissingRoles() && !this.hasRole(parent)) {
				this.addRole(parent);
			}
			this.getRole(parent).addChild(child);
		}
		childrens.add(child);
		return this;
	}

	public Rbac addRole(String childName, String[] parent) {

		RoleInterface child = new Role(childName);

		if (parent != null && parent.length > 0) {
			for (int i = 0; i < parent.length; i++) {
				if (isCreateMissingRoles() && !this.hasRole(parent[i])) {
					this.addRole(parent[i]);
				}
				this.getRole(parent[i]).addChild(child);
			}
		}
		childrens.add(child);
		return this;
	}

	public Rbac addRole(RoleInterface child) {
		childrens.add(child);
		return this;
	}

	public Rbac addRole(RoleInterface child, String parent) {

		if (parent != null) {
			if (isCreateMissingRoles() && !this.hasRole(parent)) {
				this.addRole(parent);
			}
			this.getRole(parent).addChild(child);
		}
		childrens.add(child);
		return this;
	}

	public Rbac addRole(RoleInterface child, String[] parent) {

		if (parent != null && parent.length > 0) {
			for (int i = 0; i < parent.length; i++) {
				if (isCreateMissingRoles() && !this.hasRole(parent[i])) {
					this.addRole(parent[i]);
				}
				this.getRole(parent[i]).addChild(child);
			}
		}
		childrens.add(child);
		return this;
	}

	public boolean hasRole(String roleName) {

		try {
			this.getRole(roleName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public RoleInterface getRole(String roleName) {
		for (RoleInterface role : childrens) {
			if (role.getName().equalsIgnoreCase(roleName)) {
				return role;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param roleName
	 * @param permission
	 * @return
	 */
	public boolean isGranted(String roleName, String permission) {
		RoleInterface role = getRole(roleName);
		try {
			if (role.hasPermission(permission)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(role);
			System.out.println("-------");
		}
		try {
			// 获取父角色
			role.getParents(role);
			List<RoleInterface> parents = role.getParents();
			for (RoleInterface parent : parents) {
				if (parent.hasPermission(permission)) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 检查权限
	 * 
	 * @param role
	 * @param permission
	 * @return
	 */
	public boolean isGranted(RoleInterface role, String permission) {

		if (role.hasPermission(permission)) {
			return true;
		}
		try {
			// 获取父角色
			role.getParents(role);
			List<RoleInterface> parents = role.getParents();
			for (RoleInterface parent : parents) {
				if (parent.hasPermission(permission)) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	/**
	 * 
	 * @param role
	 * @param permission
	 * @param asserts
	 * @return
	 */
	public boolean isGranted(RoleInterface role, String permission,
			boolean asserts) {

		return role.hasPermission(permission);
	}
	
	public void showChildren()
	{
		for (RoleInterface role : childrens) {
			System.out.println(role);
			System.out.println(role.getName());		
		}
		System.out.println("------------------");
	}

}