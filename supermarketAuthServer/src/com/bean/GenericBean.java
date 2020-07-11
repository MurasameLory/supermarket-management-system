package com.bean;

import java.io.Serializable;

public abstract class GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract Object[] getObjects(); 
	
}
