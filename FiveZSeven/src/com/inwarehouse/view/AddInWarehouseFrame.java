package com.inwarehouse.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.Client;
import com.bean.InWarehouse;
import com.bean.Purchase;
import com.bean.Warehouse;
import com.common.MyFrame;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class AddInWarehouseFrame extends MyFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtWName;
	private JFormattedTextField txtJoinTime;
	private JTextField txtWeight;
	DefaultComboBoxModel<String> orderModel = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<Warehouse> houseModel = new DefaultComboBoxModel<Warehouse>();

	public AddInWarehouseFrame() {
		try {
			List<Purchase> orders = Client.purchaseDao.list(Session.getToken());
			Purchase t;
			for (int i = 0; i < orders.size(); i++) {
				t = orders.get(i);
				orderModel.addElement(t.getOid());
			}
			orderModel.setSelectedItem(null);

			List<Warehouse> house = Client.warehouseDao.list(Session.getToken());
			Warehouse w;
			for (int i = 0; i < house.size(); i++) {
				w = house.get(i);
				houseModel.addElement(w);
			}
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (TokenUnvalidException e2) {
			e2.printStackTrace();
		}
		setTitle("\u6DFB\u52A0\u5165\u5E93\u8BB0\u5F55");
		//setPreferredSize(new Dimension(800, 500));
		setSize(615, 390);
		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		lblNewLabel.setBounds(14, 37, 100, 25);
		getContentPane().add(lblNewLabel);

		JComboBox<String> cbOid = new JComboBox<String>();
		cbOid.setEditable(true);
		cbOid.setBounds(117, 37, 143, 24);
		cbOid.setModel(orderModel);
		getContentPane().add(cbOid);

		JLabel label = new JLabel("\u4ED3\u5E93\u7F16\u53F7\uFF1A");
		label.setBounds(308, 37, 100, 25);
		getContentPane().add(label);

		JComboBox<Warehouse> cbDid = new JComboBox<Warehouse>();
		cbDid.setBounds(411, 37, 166, 24);
		cbDid.setModel(houseModel);
		getContentPane().add(cbDid);

		JLabel label_1 = new JLabel("\u8D27\u54C1\u540D\u79F0\uFF1A");
		label_1.setBounds(14, 81, 100, 25);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u5165\u5E93\u65F6\u95F4\uFF1A");
		label_2.setBounds(308, 81, 100, 25);
		getContentPane().add(label_2);

		txtWName = new JTextField();
		txtWName.setBounds(117, 81, 143, 24);
		getContentPane().add(txtWName);
		txtWName.setColumns(10);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormatter df = new DateFormatter(format);

		txtJoinTime = new JFormattedTextField(df);
		txtJoinTime.setColumns(10);
		txtJoinTime.setBounds(411, 81, 166, 24);
		txtJoinTime.setValue(new Date());
		// txtJoinTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new
		// Date()));
		getContentPane().add(txtJoinTime);

		JLabel label_3 = new JLabel("\u91CD\u91CF\uFF1A");
		label_3.setBounds(14, 119, 100, 25);
		getContentPane().add(label_3);

		txtWeight = new JTextField();
		txtWeight.setColumns(10);
		txtWeight.setBounds(117, 119, 143, 24);
		getContentPane().add(txtWeight);

		JLabel lblNewLabel_1 = new JLabel("\u5343\u514B");
		lblNewLabel_1.setBounds(285, 122, 72, 18);
		getContentPane().add(lblNewLabel_1);

		JLabel label_4 = new JLabel("*");
		label_4.setBounds(267, 40, 27, 18);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("*");
		label_5.setBounds(267, 84, 27, 18);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("*");
		label_6.setBounds(267, 119, 27, 18);
		getContentPane().add(label_6);

		JLabel label_7 = new JLabel("*");
		label_7.setBounds(580, 40, 22, 18);
		getContentPane().add(label_7);

		JLabel label_8 = new JLabel("*");
		label_8.setBounds(580, 81, 27, 18);
		getContentPane().add(label_8);

		JLabel label_9 = new JLabel("\u5907\u6CE8\uFF1A");
		label_9.setBounds(14, 157, 100, 25);
		getContentPane().add(label_9);

		JTextArea txtRemark = new JTextArea();
		txtRemark.setBounds(117, 158, 437, 110);
		getContentPane().add(txtRemark);

		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oid = (String) cbOid.getSelectedItem();
				if(oid==null) oid="";
				Warehouse w = (Warehouse)cbDid.getSelectedItem();
				Integer did = null;
				if(w!=null) {
					did = w.getId();
				}
				String wName = txtWName.getText().trim();
				String joinTime = txtJoinTime.getText().trim();
				String strWeight = txtWeight.getText().trim();
				String remark = txtRemark.getText().trim();
				if("".equals(oid) || "".equals(did) || "".equals(wName) || "".equals(joinTime) || "".equals(strWeight)) {
					JOptionPane.showMessageDialog(null, "请将带星号(*)的信息填写完整！", "提示", JOptionPane.WARNING_MESSAGE);
					return ;
				}				
				try {
					InWarehouse t = new InWarehouse();
					t.setWeight(Float.parseFloat(strWeight));
					t.setOid(oid);
					t.setDid(did);
					t.setWareName(wName);
					t.setJoinTime(joinTime);
					t.setRemark(remark);
					Client.inWarehouseDao.insert(t, Session.getToken());
				}catch(RemoteException re) {
					JOptionPane.showMessageDialog(null, re.getLocalizedMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "货物重量填写错误","提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null, "数据添加成功！","提示",JOptionPane.ERROR_MESSAGE);	
				observable.setChanged();
				observable.notifyObservers();
			}
		});
		btnAdd.setBounds(147, 295, 113, 27);
		getContentPane().add(btnAdd);

		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(308, 295, 113, 27);
		getContentPane().add(btnExit);

	}
}
