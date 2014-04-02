package com.sniper.survey.custom.rbac;

import java.util.List;

public interface RoleInterface {

	public String getName();

	public RoleInterface addPermission(String permission);

	public boolean hasPermission(String permission);

	public RoleInterface addChild(RoleInterface child);

	public RoleInterface setParent(RoleInterface parent);

	public RoleInterface getParent();

	public List<RoleInterface> getParents();

	List<RoleInterface> getParents(RoleInterface role);
}
