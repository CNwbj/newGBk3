package cn.sz.zl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.sz.zl.pojo.Permission;
@Mapper
public interface IPermissionDao {
	
	public List<Permission> queryPermissionByRoleid(Integer roleid);
	
	public List<Permission> queryPermissionByloginname(String loginname);
	
	public List<Permission> queryAllPermission();
	
	public List<String> queryAllURL();
}
