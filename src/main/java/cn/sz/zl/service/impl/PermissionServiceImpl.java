package cn.sz.zl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.zl.dao.IPermissionDao;
import cn.sz.zl.pojo.Permission;
import cn.sz.zl.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private IPermissionDao pd;

	public List<Permission> queryPermissionByRoleid(Integer roleid) {
		if (roleid != null && !"".equals(roleid)) {
			return pd.queryPermissionByRoleid(roleid);
		}
		return null;
	}

	public List<Permission> queryPermissionByLoginname(String loginname) {
		if (loginname != null && !"".equals(loginname)) {
			return pd.queryPermissionByloginname(loginname);
		}
		return null;
	}

	public List<Permission> queryAllPermission() {
		return pd.queryAllPermission();
	}

	public List<String> queryAllURL() {
		return pd.queryAllURL();
	}

}
