package com.battcn.system.pojo.dto;

import lombok.Data;

/**
 * @author Levin
 */
@Data
public class ShiroPermission implements java.io.Serializable{

	private static final long serialVersionUID = 2282773948895767820L;

	private String menuName;
	private String path;
	private String perms;

}
