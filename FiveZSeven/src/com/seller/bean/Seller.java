package com.seller.bean;

import java.io.Serializable;

import com.bean.GenericBean;

public class Seller extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String sellName;
	private String address;
	private String linkman;
	private String linkPhone;
	private String faxNum;
	private String postNum;
	private String bankNum;
	private String netAddress;
	private String emailAddress;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getNetAddress() {
		return netAddress;
	}
	public void setNetAddress(String netAddress) {
		this.netAddress = netAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id,sellName,address,linkman,linkPhone,faxNum,postNum,bankNum,netAddress,emailAddress,remark};
	}
}
