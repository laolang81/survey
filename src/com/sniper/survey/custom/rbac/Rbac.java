package com.sniper.survey.custom.rbac;

public class Rbac extends AbstractChildren {

	protected boolean createMissingRoles = false;

	public boolean isCreateMissingRoles() {
		return createMissingRoles;
	}

	public Rbac setCreateMissingRoles(boolean createMissingRoles) {
		this.createMissingRoles = createMissingRoles;
		return this;
	}

	public Rbac addRole(String childName) {

		RoleInterface child = new Role(childName);
		children.add(child);
		return this;
	}

	public Rbac addRole(String childName, String parent) {

		RoleInterface child = new Role(childName);

		if (parent != null) {
			if (isCreateMissingRoles() && !this.hasRole(parent)) {
				this.getRole(parent).addChild(child);
			}
		}
		children.add(child);
		return this;
	}

	public Rbac addRole(String childName, String[] parent) {

		RoleInterface child = new Role(childName);

		if (parent != null && parent.length > 0) {
			for (int i = 0; i < parent.length; i++) {
				if (isCreateMissingRoles() && !this.hasRole(parent[i])) {
					this.getRole(parent[i]).addChild(child);
				}
			}
		}
		children.add(child);
		return this;
	}

	public Rbac addRole(RoleInterface child) {
		children.add(child);
		return this;
	}

	public Rbac addRole(RoleInterface child, String parent) {

		if (parent != null) {
			if (isCreateMissingRoles() && !this.hasRole(parent)) {
				this.getRole(parent).addChild(child);
			}
		}
		children.add(child);
		return this;
	}

	public Rbac addRole(RoleInterface child, String[] parent) {

		if (parent != null && parent.length > 0) {
			for (int i = 0; i < parent.length; i++) {
				if (isCreateMissingRoles() && !this.hasRole(parent[i])) {
					this.getRole(parent[i]).addChild(child);
				}
			}
		}
		children.add(child);
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

		for (RoleInterface role : children) {
			if (role.getName().equalsIgnoreCase(roleName)) {
				return role;
			}
		}
		return null;

	}

	public boolean isGranted(String roleName, String permission) {
		if (getRole(roleName).hasPermission(permission)) {
			return true;
		}
		return false;
	}

	public boolean isGranted(RoleInterface role, String permission) {
		if (role.hasPermission(permission)) {
			return true;
		}
		return false;
	}

}
