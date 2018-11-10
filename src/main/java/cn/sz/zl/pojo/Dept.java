package cn.sz.zl.pojo;

import java.io.Serializable;

public class Dept implements Serializable {
	private Integer deptno;
	private String dname;
	private Integer is_dele;

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getIs_dele() {
		return is_dele;
	}

	public void setIs_dele(Integer is_dele) {
		this.is_dele = is_dele;
	}
	
	
}
