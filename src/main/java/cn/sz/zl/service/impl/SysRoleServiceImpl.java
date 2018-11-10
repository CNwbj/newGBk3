package cn.sz.zl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.zl.dao.ISysRoleDao;
import cn.sz.zl.pojo.SysRole;
import cn.sz.zl.service.ISysRoleService;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private ISysRoleDao srd;

	public SysRole queryRoleAndPermissionByRoleid(Integer roleid) {
		if (roleid != null && !"".equals(roleid)) {
			return srd.queryRoleAndPermissionByRoleid(roleid);
		}
		return null;
	}

	public List<SysRole> queryAllRoleWithoutAdmin() {
		return srd.queryAllRoleWithoutAdmin();
	}
}
