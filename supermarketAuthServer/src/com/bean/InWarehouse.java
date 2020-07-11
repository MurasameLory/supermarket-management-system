package com.bean;

import java.io.Serializable;

public class InWarehouse extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String oid;
	private int did;
	private String wareName;
	private String joinTime;
	private float weight;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
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
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
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
	
	public Object[] getObjects() {
		return new Object[] {id, oid, did, wareName, joinTime, weight, remark};
	}	
}
