package com.bean;

import java.io.Serializable;

public class Duty extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String headshipName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeadshipName() {
		return headshipName;
	}
	public void setHeadshipName(String headshipName) {
		this.headshipName = headshipName;
	}
	
	public String toString() {
		return headshipName;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id, headshipName};
	}

}
