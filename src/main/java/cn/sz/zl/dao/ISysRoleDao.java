package cn.sz.zl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.sz.zl.pojo.SysRole;
@Mapper
public interface ISysRoleDao {
	
	public SysRole queryRoleAndPermissionByRoleid(Integer roleid);
	
	public List<SysRole> queryAllRoleWithoutAdmin();
}
