package com.bean;

import java.io.Serializable;

public class Warehouse extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String toString() {
		return id+"-"+name;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id,name,remark};
	}

}
