package cn.sz.zl.service;

import java.util.List;

import cn.sz.zl.pojo.Permission;

public interface IPermissionService {
public List<Permission> queryPermissionByRoleid(Integer roleid);
	
	public List<Permission> queryPermissionByLoginname(String loginname);
	
	public List<Permission> queryAllPermission();
	
	public List<String> queryAllURL();
}
