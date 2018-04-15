package com.battcn.system.pojo.dto;

import java.util.Date;

/**
 * @author Levin
 */
public class RoleOperateDto implements java.io.Serializable {

	private static final long serialVersionUID = -6234661058237652047L;

	private Integer roleId;
	private Integer operateId;
	private Date createdTime;
	private Date lastModifiedTime;
	private String op;
	private Integer menuId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public Date getGmtCreate() {
		return createdTime;
	}

	public void setGmtCreate(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getGmtModified() {
		return lastModifiedTime;
	}

	public void setGmtModified(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
