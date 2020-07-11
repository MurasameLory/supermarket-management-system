package com.purchase.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.Client;
import com.bean.Goods;
import com.bean.Purchase;
import com.common.MyObservable;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;
import com.supplier.bean.Supplier;

public class UpdateStockFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtOrderId;
	private JTextField txtConsignmentDate;
	private JTextField txtMoney;
	private JTextField txtCount;
	private JComboBox<String> cbSName;
	private JComboBox<String> cbBaleName;
	private Purchase p;
	private MyObservable observable = new MyObservable();
	
	public MyObservable getObservable() {
		return observable;
	}

	public UpdateStockFrame() {
		setLocationRelativeTo(null);
		
		setTitle("\u4FEE\u6539\u8BA2\u5355");
		getContentPane().setLayout(null);
		setSize(650,245);
		
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(20, 13, 98, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		lblNewLabel_1.setBounds(326, 13, 98, 18);
		getContentPane().add(lblNewLabel_1);
		
		txtOrderId = new JTextField();
		txtOrderId.setColumns(10);
		txtOrderId.setBounds(444, 10, 168, 24);
		getContentPane().add(txtOrderId);
		
		JLabel label = new JLabel("\u4EA4\u8D27\u65E5\u671F\uFF1A");
		label.setBounds(20, 58, 98, 18);
		getContentPane().add(label);
		
		txtConsignmentDate = new JTextField();
		txtConsignmentDate.setColumns(10);
		txtConsignmentDate.setBounds(123, 58, 168, 24);
		getContentPane().add(txtConsignmentDate);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u540D\uFF1A");
		label_1.setBounds(326, 58, 98, 18);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u91D1\u989D\uFF1A");
		label_2.setBounds(20, 106, 98, 18);
		getContentPane().add(label_2);
		
		txtMoney = new JTextField();
		txtMoney.setColumns(10);
		txtMoney.setBounds(123, 103, 168, 24);
		getContentPane().add(txtMoney);
		
		JLabel label_3 = new JLabel("\u6570\u91CF\uFF1A");
		label_3.setBounds(326, 106, 98, 18);
		getContentPane().add(label_3);
		
		txtCount = new JTextField();
		txtCount.setColumns(10);
		txtCount.setBounds(444, 103, 168, 24);
		getContentPane().add(txtCount);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sName = (String) cbSName.getSelectedItem();
				if(sName==null) sName="";
				String orderId = txtOrderId.getText().trim();
				String consignmentDate = txtConsignmentDate.getText().trim();
				String baleName = (String) cbBaleName.getSelectedItem();
				if(baleName==null) baleName="";
				String money = txtMoney.getText().trim();
				String  count = txtCount.getText();
				if("".equals(sName) || "".equals(orderId) || "".equals(consignmentDate) || "".equals(baleName) || "".equals(money) || "".equals(count)) {
					MyToolKit.showWarning("带星号(*)的信息必须填写完整");
					return;
				}
				try {
					if(p==null) return;
					p.setMoney(Float.parseFloat(money));
					p.setBaleName(baleName);
					p.setConsignmentDate(consignmentDate);
					p.setMount(count);
					p.setOid(orderId);
					p.setsName(sName);
					Client.purchaseDao.update(p, Session.getToken());
					MyToolKit.showMessage("数据更新成功");
					observable.setChanged();
					observable.notifyObservers();
				}catch(NumberFormatException nfe) {
					MyToolKit.showError(nfe);
					return;
				}catch(RemoteException re) {
					MyToolKit.showError(re);
					return;
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}				
			}
		});
		btnUpdate.setBounds(178, 156, 113, 27);
		getContentPane().add(btnUpdate);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(326, 156, 113, 27);
		getContentPane().add(btnExit);
		
		cbSName = new JComboBox<String>();
		cbSName.setBounds(123, 13, 168, 24);
		getContentPane().add(cbSName);
		
		cbBaleName = new JComboBox<String>();
		cbBaleName.setBounds(444, 55, 168, 24);
		getContentPane().add(cbBaleName);
		
		JLabel label_4 = new JLabel("*");
		label_4.setBounds(291, 13, 21, 18);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setBounds(291, 58, 21, 18);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setBounds(291, 106, 21, 18);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setBounds(612, 13, 21, 18);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setBounds(612, 58, 21, 18);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setBounds(612, 106, 21, 18);
		getContentPane().add(label_9);
		
		init();
	}
	
	private void init() {
		try {
			List<Supplier> listSupplier = Client.supplierDao.selectSupplier(Session.getToken());
			DefaultComboBoxModel<String> supplierModel = new DefaultComboBoxModel<String>();
			for(int i=0;i<listSupplier.size();i++) {
				supplierModel.addElement(listSupplier.get(i).getcName());
			}
			supplierModel.setSelectedItem(null);
			cbSName.setModel(supplierModel);
			List<Goods> listGoods = Client.goodsDao.list(Session.getToken());
			DefaultComboBoxModel<String> goodsModel = new DefaultComboBoxModel<String>();
			for(int i=0;i<listGoods.size();i++) {
				goodsModel.addElement(listGoods.get(i).getWareName());
			}
			goodsModel.setSelectedItem(null);
			cbBaleName.setModel(goodsModel);
		}catch(RemoteException re) {
			MyToolKit.showError(re);
			return;
		}catch(TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}
	}
	
	public void setId(int id) {
		try {
			p = Client.purchaseDao.get(id,Session.getToken());
			cbSName.setSelectedItem(p.getsName());
			txtOrderId.setText(p.getOid());
			txtConsignmentDate.setText(p.getConsignmentDate());
			cbBaleName.setSelectedItem(p.getBaleName());
			txtMoney.setText(String.valueOf(p.getMoney()));
			txtCount.setText(p.getMount());
		}catch(RemoteException re) {
			MyToolKit.showError(re);
			return;
		}catch(TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}
	}

}
