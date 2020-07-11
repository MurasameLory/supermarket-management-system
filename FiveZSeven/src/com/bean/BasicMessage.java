package com.bean;

import java.io.Serializable;

public class BasicMessage extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int age;
	private String sex;
	private int dept;
	private int headship;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public int getHeadship() {
		return headship;
	}
	public void setHeadship(int headship) {
		this.headship = headship;
	}
	public String toString() {
		return name;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id,name,age,sex,dept,headship};
	}

}
