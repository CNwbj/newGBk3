package cn.sz.zl.service;

import java.util.List;

import cn.sz.zl.pojo.SysRole;

public interface ISysRoleService {
public SysRole queryRoleAndPermissionByRoleid(Integer roleid);
	
	public List<SysRole> queryAllRoleWithoutAdmin();
}
