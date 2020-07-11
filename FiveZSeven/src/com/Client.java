package com;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.UIManager;

import com.dao.BasicMessageDao;
import com.dao.ContactDao;
import com.dao.DepartmentDao;
import com.dao.DutyDao;
import com.dao.GoodsDao;
import com.dao.InWarehouseDao;
import com.dao.OutWarehouseDao;
import com.dao.PurchaseDao;
import com.dao.SellerDao;
import com.dao.WarehouseDao;
import com.main.view.MainFrame;
import com.supplier.dao.SupplierDao;
import com.user.view.LoginFrame;

public class Client {

	static String serverAddress = "localhost";
	static int appServerPort = 12346;
	
	public static MainFrame mainFrame = null;
	
	public static BasicMessageDao basicMessageDao;
	public static ContactDao contactDao;
	public static DepartmentDao departmentDao;
	public static DutyDao dutyDao;
	public static GoodsDao goodsDao;
	public static InWarehouseDao inWarehouseDao;
	public static OutWarehouseDao outWarehouseDao;
	public static PurchaseDao purchaseDao;
	public static SellerDao sellerDao;
	public static SupplierDao supplierDao;
	public static WarehouseDao warehouseDao;
	
	static {
		try {
			basicMessageDao = (BasicMessageDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/basicMessageDao");
			contactDao = (ContactDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/contactDao");
			departmentDao = (DepartmentDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/departmentDao");
			dutyDao = (DutyDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/dutyDao");
			goodsDao = (GoodsDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/goodsDao");
			inWarehouseDao = (InWarehouseDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/inWarehouseDao");
			outWarehouseDao = (OutWarehouseDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/outWarehouseDao");
			purchaseDao = (PurchaseDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/purchaseDao");
			sellerDao = (SellerDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/sellerDao");
			supplierDao = (SupplierDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/supplierDao");
			warehouseDao = (WarehouseDao)Naming.lookup("rmi://"+serverAddress+":"+appServerPort+"/warehouseDao");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
