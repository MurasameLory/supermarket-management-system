package com.bean;

import java.io.Serializable;

public class Department extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String dName;
	private String principal;
	private String bewrite;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getBewrite() {
		return bewrite;
	}
	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}
	
	public String toString() {
		return dName;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id, dName, principal, bewrite};
	}

}
