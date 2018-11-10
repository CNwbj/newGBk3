package cn.sz.zl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.zl.dao.IDeptDao;
import cn.sz.zl.pojo.Dept;
import cn.sz.zl.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private IDeptDao dd;

	public List<Dept> queryAllDept() {
		return dd.queryAllDept();
	}

	public Dept queryDeptByDeptno(Integer deptno) {
		if (deptno != null && !"".equals(deptno)) {
			return dd.queryDeptByDeptno(deptno);
		}
		return null;
	}


	public void addDept(Dept dept) {
		
	}


}
