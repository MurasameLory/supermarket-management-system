package com.bean;

import java.io.Serializable;

public class Goods extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String wareName;
	private String wareBewrite;
	private String spec;
	private float stockPrice;
	private float retallPrice;
	private float assoclatorPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getWareBewrite() {
		return wareBewrite;
	}
	public void setWareBewrite(String wareBewrite) {
		this.wareBewrite = wareBewrite;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public float getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(float stockPrice) {
		this.stockPrice = stockPrice;
	}
	public float getRetallPrice() {
		return retallPrice;
	}
	public void setRetallPrice(float retallPrice) {
		this.retallPrice = retallPrice;
	}
	public float getAssoclatorPrice() {
		return assoclatorPrice;
	}
	public void setAssoclatorPrice(float assoclatorPrice) {
		this.assoclatorPrice = assoclatorPrice;
	}
	@Override
	public Object[] getObjects() {
		return new Object[] {id, wareName, wareBewrite,spec,stockPrice,retallPrice,assoclatorPrice};
	}


}
