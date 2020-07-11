package com.common;


import java.rmi.RemoteException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.bean.GenericBean;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;
import com.dao.GenericDao;

public class MyTableModel<T extends GenericBean, P> extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private GenericDao<T,P> dao;
	
	private Class[] types;
	private boolean[] canEdit;
	
	public GenericDao<T, P> getDao() {
		return dao;
	}

	public void setDao(GenericDao<T, P> dao) {
		this.dao = dao;
	}

	public void setTypes(Class[] types) {
		this.types = types;
	}

	public void setCanEdit(boolean[] canEdit) {
		this.canEdit = canEdit;
	}
	
	public MyTableModel() {		
	}
	
	public MyTableModel(GenericDao<T,P> dao) {
		this.dao = dao;
	}

	public MyTableModel(Object[] columnNames) {
		super(new Object[][] {}, columnNames);
	}
	
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
	
	public void remove(int row, int column) {
		try {
			dao.delete((P)getValueAt(row,column), Session.getToken());
			removeRow(row);
		} catch (RemoteException re) {
			MyToolKit.showError(re);
			return;
		} catch (TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}		
	}
	
	public void fillList() {
		try {
			List<T> list = dao.list(Session.getToken());
			setRowCount(0);
			T t;
			for(int i=0; i<list.size(); i++) {
				t  = list.get(i);
				addRow(t.getObjects());
			}
		} catch (RemoteException re) {
			MyToolKit.showError(re);
			return;
		} catch (TokenUnvalidException e) {
			MyToolKit.login();
			return;
		}
	}
	
	public void fillSearch(T o) {
		try {
			List<T> list = dao.search(o, Session.getToken());
			setRowCount(0);
			T t;
			for(int i=0; i<list.size(); i++) {
				t  = list.get(i);
				addRow(t.getObjects());
			}
		} catch (RemoteException re) {
			MyToolKit.showError(re);
			return;
		} catch (TokenUnvalidException e) {
			MyToolKit.login();
			return;
		}
	}

}
