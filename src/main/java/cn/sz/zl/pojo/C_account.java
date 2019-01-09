package cn.sz.zl.pojo;

import java.io.Serializable;
/**
 * 客户绑定的银行卡类型实体类
 * @author dwx
 *
 */
public class C_account implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer c_accountid ;
	private String c_account_type;
	private String c_account_num;
	public Integer getC_accountid() {
		return c_accountid;
	}
	public void setC_accountid(Integer c_accountid) {
		this.c_accountid = c_accountid;
	}
	public String getC_account_type() {
		return c_account_type;
	}
	public void setC_account_type(String c_account_type) {
		this.c_account_type = c_account_type;
	}
	public String getC_account_num() {
		return c_account_num;
	}
	public void setC_account_num(String c_account_num) {
		this.c_account_num = c_account_num;
	}
	public void setC_account_num(String c_account_num) {
		this.c_account_num = c_account_num;
	}
	
}
