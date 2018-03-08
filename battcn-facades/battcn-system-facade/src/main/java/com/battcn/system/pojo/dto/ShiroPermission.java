package com.battcn.system.pojo.dto;

/**
 * @author Levin
 */
public class ShiroPermission {

	private String menuName;
	private String path;
	private String perms;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

}
