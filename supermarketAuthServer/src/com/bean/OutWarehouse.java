package com.bean;

import java.io.Serializable;

public class OutWarehouse extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int did;
	private String wareName;
	private String outDate;
	private float weight;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id, did, wareName, outDate, weight, remark };
	}
	
}
