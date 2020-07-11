package com.bean;

import java.io.Serializable;

public class Contact extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int hid;
	private String contact;
	private String officePhone;
	private String fax;
	private String email;
	private String faddress;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id, hid, contact, officePhone, fax, email, faddress};
	}	

}
