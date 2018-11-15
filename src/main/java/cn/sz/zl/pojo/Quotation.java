package cn.sz.zl.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Quotation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer quotationid;//报价单编号
	private Integer  material_id;//资材编号
	private Material material;//资材对象
	private Integer c_paymentid;//支付方式编号
	private C_payment cpayment;//支付方式对象
	@DateTimeFormat(pattern="yyyy-MM-dd")//字符串转换为日期格式的类型
	private Date quotation_date;//订单开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private  Date ofr_vail;//有效期至
	private Integer c_id;	//客户编号
	private Customer customer;//客户对象	
	private Integer stateid12;//未提交
	private State state0;	
	private Integer stateid13;//提交
	private State state1;//状态对象
	private Integer stateid7;//审批
	private State state2;
	private Integer stateid6;
	private State state7;
	private Integer paycount;//求购数量
	private Integer stateid22;//驳回
	private State state3;
	private Integer stateid23;//完结
	private State state4;
	private Integer stateid14;//成交
	private State state5;
	private Integer stateid15;//未成交
	private State state6;	
	private Integer roleid2;//业务员
	private SysRole sysrole2;//角色对象
	private Integer roleid7;//营销助理
	private SysRole sysrole7;//角色对象
	private Integer roleid8;//营销经理
	private SysRole sysrole8;//角色对象	
	private double q_price;//商议价钱
	private String remarks;
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getQuotationid() {
		return quotationid;
	}
	public void setQuotationid(Integer quotationid) {
		this.quotationid = quotationid;
	}
	public Integer getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Integer getC_paymentid() {
		return c_paymentid;
	}
	public void setC_paymentid(Integer c_paymentid) {
		this.c_paymentid = c_paymentid;
	}
	public C_payment getCpayment() {
		return cpayment;
	}
	public void setCpayment(C_payment cpayment) {
		this.cpayment = cpayment;
	}
	public Date getQuotation_date() {
		return quotation_date;
	}
	public void setQuotation_date(Date quotation_date) {
		this.quotation_date = quotation_date;
	}
	public Date getOfr_vail() {
		return ofr_vail;
	}
	public void setOfr_vail(Date ofr_vail) {
		this.ofr_vail = ofr_vail;
	}
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Integer getPaycount() {
		return paycount;
	}
	public void setPaycount(Integer paycount) {
		this.paycount = paycount;
	}
	
	public double getQ_price() {
		return q_price;
	}
	public void setQ_price(double q_price) {
		this.q_price = q_price;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getStateid12() {
		return stateid12;
	}
	public void setStateid12(Integer stateid12) {
		this.stateid12 = stateid12;
	}
	public State getState0() {
		return state0;
	}
	public void setState0(State state0) {
		this.state0 = state0;
	}
	public Integer getStateid13() {
		return stateid13;
	}
	public void setStateid13(Integer stateid13) {
		this.stateid13 = stateid13;
	}
	public State getState1() {
		return state1;
	}
	public void setState1(State state1) {
		this.state1 = state1;
	}
	public Integer getStateid7() {
		return stateid7;
	}
	public void setStateid7(Integer stateid7) {
		this.stateid7 = stateid7;
	}
	public State getState2() {
		return state2;
	}
	public void setState2(State state2) {
		this.state2 = state2;
	}
	public Integer getStateid6() {
		return stateid6;
	}
	public void setStateid6(Integer stateid6) {
		this.stateid6 = stateid6;
	}
	public State getState7() {
		return state7;
	}
	public void setState7(State state7) {
		this.state7 = state7;
	}
	public Integer getStateid22() {
		return stateid22;
	}
	public void setStateid22(Integer stateid22) {
		this.stateid22 = stateid22;
	}
	public State getState3() {
		return state3;
	}
	public void setState3(State state3) {
		this.state3 = state3;
	}
	public Integer getStateid23() {
		return stateid23;
	}
	public void setStateid23(Integer stateid23) {
		this.stateid23 = stateid23;
	}
	public State getState4() {
		return state4;
	}
	public void setState4(State state4) {
		this.state4 = state4;
	}
	public Integer getStateid14() {
		return stateid14;
	}
	public void setStateid14(Integer stateid14) {
		this.stateid14 = stateid14;
	}
	public State getState5() {
		return state5;
	}
	public void setState5(State state5) {
		this.state5 = state5;
	}
	public Integer getStateid15() {
		return stateid15;
	}
	public void setStateid15(Integer stateid15) {
		this.stateid15 = stateid15;
	}
	public State getState6() {
		return state6;
	}
	public void setState6(State state6) {
		this.state6 = state6;
	}
	public Integer getRoleid2() {
		return roleid2;
	}
	public void setRoleid2(Integer roleid2) {
		this.roleid2 = roleid2;
	}
	public SysRole getSysrole2() {
		return sysrole2;
	}
	public void setSysrole2(SysRole sysrole2) {
		this.sysrole2 = sysrole2;
	}
	public Integer getRoleid7() {
		return roleid7;
	}
	public void setRoleid7(Integer roleid7) {
		this.roleid7 = roleid7;
	}
	public SysRole getSysrole7() {
		return sysrole7;
	}
	public void setSysrole7(SysRole sysrole7) {
		this.sysrole7 = sysrole7;
	}
	public Integer getRoleid8() {
		return roleid8;
	}
	public void setRoleid8(Integer roleid8) {
		this.roleid8 = roleid8;
	}
	public SysRole getSysrole8() {
		return sysrole8;
	}
	public void setSysrole8(SysRole sysrole8) {
		this.sysrole8 = sysrole8;
	}
		

}
