package com.bean;

import java.io.Serializable;

public class Purchase extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String sName;
	private String oid;
	private String consignmentDate;
	private String baleName;
	private String mount;
	private float money;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getConsignmentDate() {
		return consignmentDate;
	}
	public void setConsignmentDate(String consignmentDate) {
		this.consignmentDate = consignmentDate;
	}
	public String getBaleName() {
		return baleName;
	}
	public void setBaleName(String baleName) {
		this.baleName = baleName;
	}
	public String getMount() {
		return mount;
	}
	public void setMount(String mount) {
		this.mount = mount;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id,sName,oid,consignmentDate,baleName,mount,money};
	}
	
	public Object[] getObjects(boolean isInWarehouse) {
		return new Object[] {isInWarehouse, id,sName,oid,consignmentDate,baleName,mount,money};
	}
}
