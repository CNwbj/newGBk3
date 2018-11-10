package cn.sz.zl.util;

import cn.sz.zl.pojo.C_payment;
import cn.sz.zl.pojo.Customer;
import cn.sz.zl.pojo.Material;
import cn.sz.zl.pojo.ReturnGoods;
import cn.sz.zl.pojo.Sale;
import cn.sz.zl.pojo.State;

public class FHUtil {
	private Sale sale;
	private Customer customer;    //客户表对象
	private Material material;	  //商品表对象
	private State state;		  //仓库状态表对象
	private C_payment payment;	  //支付方式表对象
	private ReturnGoods returngoods;
	
	public ReturnGoods getReturngoods() {
		return returngoods;
	}
	public void setReturngoods(ReturnGoods returngoods) {
		this.returngoods = returngoods;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public C_payment getPayment() {
		return payment;
	}
	public void setPayment(C_payment payment) {
		this.payment = payment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
}
