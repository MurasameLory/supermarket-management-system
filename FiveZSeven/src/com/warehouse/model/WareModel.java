package com.warehouse.model;

public class WareModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			, java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false,false,false,false, false, false,false, false, false};
	public WareModel() {
		super(new Object[][] {}, new String[] { "编号", "货品名称","货品描述", "单位","进货价","零售价" ,"会员价"});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}