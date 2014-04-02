package com.sniper.survey.custom.rbac;

public interface RoleInterface {

	public String getName();
	
	public void addPermission(String permission);
	
	public boolean hasPermission(String permission);
	
	public RoleInterface addChild(RoleInterface child);
	
	public void setParent(RoleInterface parent);
	
	public RoleInterface getParent();
}
