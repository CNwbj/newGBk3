package cn.sz.zl.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.sz.zl.pojo.SysUser;
import cn.sz.zl.util.SysUserSearchCondition;

@Mapper
public interface ISysUserDao {
	
	public SysUser checkLogIn(SysUser user);
	
	public SysUser queryUserByLoginname(String loginname);
	
	public List<SysUser> queryAllUser(SysUserSearchCondition usersc);
	
	public Integer countAllUser(SysUserSearchCondition usersc);
	
	public Integer queryRoleidByUserid(Integer userid);
	
	public Integer checkLoginnameRepeat(String loginname);
	
	public void addSysUser(SysUser user);
	
	public void updateSysUser(SysUser user);
	
	public void deleteSysUser(Integer userid);
	
	public SysUser queryUserByUserid(Integer userid);
	
	public void updateUserRole(HashMap<String, Integer> urmap);
}
