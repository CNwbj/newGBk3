package cn.sz.zl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable{
	 private Integer saleid;			//订单id
	 private Integer c_paymentid;       //支付方式
	 private Integer c_id;				//关联客户表
	 private Integer material_id;		//关联商品表
	 private Double pay;				//客户实际付款
	 private Date need_date;			//客户需要日期
	 private Date last_date;			//最后编制日期
	 private Date first_date;			//开始编制日期
	 private Date submit_date;			//订单提交日期
	 private Date create_date;			//订单生成日期
	 private String column1;
	 private String column2;
	 private String remarks;
	 private String sendaddr;			
	 private Integer goodscount;		//客户该买数量
	 private Integer stateid;			//状态表   6
	 private String column3;
	 private String column4;
	 private State state1;
	 private State state2;
	 private State state3;
	 private State state4;
	 
	public State getState1() {
		return state1;
	}

	public void setState1(State state1) {
		this.state1 = state1;
	}

	public State getState2() {
		return state2;
	}

	public void setState2(State state2) {
		this.state2 = state2;
	}

	public State getState3() {
		return state3;
	}

	public void setState3(State state3) {
		this.state3 = state3;
	}

	public State getState4() {
		return state4;
	}

	public void setState4(State state4) {
		this.state4 = state4;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	public Integer getGoodscount() {
		return goodscount;
	}

	public void setGoodscount(Integer goodscount) {
		this.goodscount = goodscount;
	}

	public Integer getSaleid() {
		return saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}

	public Integer getC_paymentid() {
		return c_paymentid;
	}

	public void setC_paymentid(Integer c_paymentid) {
		this.c_paymentid = c_paymentid;
	}

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public Integer getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}


	public Date getNeed_date() {
		return need_date;
	}

	public void setNeed_date(Date need_date) {
		this.need_date = need_date;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public Date getFirst_date() {
		return first_date;
	}

	public void setFirst_date(Date first_date) {
		this.first_date = first_date;
	}

	public Date getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getSendaddr() {
		return sendaddr;
	}

	public void setSendaddr(String sendaddr) {
		this.sendaddr = sendaddr;
	}

}
