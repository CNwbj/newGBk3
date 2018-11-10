package cn.sz.zl.pojo;

import java.io.Serializable;

public class Shipment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer shipment_id;//������id
	private Integer material_id ;//����id
	private Integer sale_id ;//����id
	private Integer state_id ;//����״̬
	//��������
	/*private Sale sale;
	//״̬���� 
	private State state;*/
	//�ɹ�����
	private Purchase purchase;
	
	private Integer userid ;//�����
	private Material material;
	private Integer purchase_id ;//�ɹ�id �óɱ���
	private String shipment_nul ;//��������ֶ�1
	private String shipment_null ;//��������ֶ�2
	public Integer getShipment_id() {
		return shipment_id;
	}
	public void setShipment_id(Integer shipment_id) {
		this.shipment_id = shipment_id;
	}
	public Integer getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}
	public Integer getSale_id() {
		return sale_id;
	}
	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(Integer purchase_id) {
		this.purchase_id = purchase_id;
	}
	public String getShipment_nul() {
		return shipment_nul;
	}
	public void setShipment_nul(String shipment_nul) {
		this.shipment_nul = shipment_nul;
	}
	public String getShipment_null() {
		return shipment_null;
	}
	public void setShipment_null(String shipment_null) {
		this.shipment_null = shipment_null;
	}
	/*public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}*/
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	

}
