package com.seller.model;

import javax.swing.table.DefaultTableModel;

public class LocalTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	Class[] types = new Class[] { java.lang.Object.class, java.lang.String.class, 
			java.lang.String.class,java.lang.String.class,java.lang.String.class,
			java.lang.String.class,java.lang.String.class,java.lang.String.class,
			java.lang.String.class,java.lang.String.class,java.lang.String.class };
	boolean[] canEdit=new boolean[] {false,false,false,false,false,false,false,false,
			false,false,false};
	public LocalTableModel() {
		super(new Object[][] {},new String[] {"编号", "客户名称", "地址", "联系人", "联系电话", "传真", "邮编", "银行账号", "网址", "EMail", "备注"});
	}
	
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}

}
