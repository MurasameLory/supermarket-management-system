package com.user.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L; //虚拟化版本的唯一标识符
	//对应数据库表的字段名
	private int id;  //定义映射主键的属性
	private String userName; //定义映射用户名的属性
	private String passWord; //定义映射密码的属性
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}	 

}
