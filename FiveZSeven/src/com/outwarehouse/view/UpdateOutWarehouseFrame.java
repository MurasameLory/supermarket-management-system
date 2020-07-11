package com.outwarehouse.view;

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
import com.bean.Goods;
import com.bean.OutWarehouse;
import com.bean.Warehouse;
import com.common.MyFrame;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class UpdateOutWarehouseFrame extends MyFrame {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtOutDate;
	private JTextField txtWeight;
	DefaultComboBoxModel<String> wareModel = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<Warehouse> houseModel = new DefaultComboBoxModel<Warehouse>();
	private JComboBox<Warehouse> cbDid;
	private JComboBox<String> cbWareName;
	private JTextArea txtRemark;
	private OutWarehouse ow;

	public UpdateOutWarehouseFrame() {
		try {
			List<Goods> goods = Client.goodsDao.list(Session.getToken());
			Goods t;
			for (int i = 0; i < goods.size(); i++) {
				t = goods.get(i);
				wareModel.addElement(t.getWareName());
			}
			wareModel.setSelectedItem(null);

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
		setTitle("\u4FEE\u6539\u51FA\u5E93\u8BB0\u5F55");
		//setPreferredSize(new Dimension(800, 500));
		setSize(615, 390);
		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8D27\u54C1\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(303, 36, 100, 25);
		getContentPane().add(lblNewLabel);

		cbWareName = new JComboBox<String>();
		cbWareName.setEditable(true);
		cbWareName.setBounds(406, 36, 143, 24);
		cbWareName.setModel(wareModel);
		getContentPane().add(cbWareName);

		JLabel label = new JLabel("\u4ED3\u5E93\u7F16\u53F7\uFF1A");
		label.setBounds(14, 36, 100, 25);
		getContentPane().add(label);

		cbDid = new JComboBox<Warehouse>();
		cbDid.setBounds(117, 36, 166, 24);
		cbDid.setModel(houseModel);
		getContentPane().add(cbDid);

		JLabel lblChuChu = new JLabel("\u51FA\u5E93\u65F6\u95F4\uFF1A");
		lblChuChu.setBounds(14, 81, 100, 25);
		getContentPane().add(lblChuChu);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormatter df = new DateFormatter(format);

		txtOutDate = new JFormattedTextField(df);
		txtOutDate.setColumns(10);
		txtOutDate.setBounds(117, 81, 166, 24);
		txtOutDate.setValue(new Date());
		getContentPane().add(txtOutDate);

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
		label_4.setBounds(556, 39, 27, 18);
		getContentPane().add(label_4);

		JLabel label_6 = new JLabel("*");
		label_6.setBounds(267, 119, 27, 18);
		getContentPane().add(label_6);

		JLabel label_7 = new JLabel("*");
		label_7.setBounds(286, 39, 22, 18);
		getContentPane().add(label_7);

		JLabel label_8 = new JLabel("*");
		label_8.setBounds(286, 81, 27, 18);
		getContentPane().add(label_8);

		JLabel label_9 = new JLabel("\u5907\u6CE8\uFF1A");
		label_9.setBounds(14, 157, 100, 25);
		getContentPane().add(label_9);

		txtRemark = new JTextArea();
		txtRemark.setBounds(117, 158, 437, 110);
		getContentPane().add(txtRemark);

		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String wareName = (String) cbWareName.getSelectedItem();
				if(wareName==null) wareName="";
				Warehouse w = (Warehouse)cbDid.getSelectedItem();
				Integer did = 0;
				if(w!=null) {
					did = w.getId();
				}
				String outDate = txtOutDate.getText().trim();
				String strWeight = txtWeight.getText().trim();
				String remark = txtRemark.getText().trim();
				if("".equals(wareName) || did==0 || "".equals(outDate) || "".equals(strWeight)) {
					JOptionPane.showMessageDialog(null, "请将带星号(*)的信息填写完整！", "提示", JOptionPane.WARNING_MESSAGE);
					return ;
				}				
				try {
					ow.setWeight(Float.parseFloat(strWeight));
					ow.setDid(did);
					ow.setWareName(wareName);
					ow.setOutDate(outDate);
					ow.setRemark(remark);
					Client.outWarehouseDao.update(ow, Session.getToken());
				}catch(RemoteException re) {
					MyToolKit.showError(re);
					return;
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "货物重量填写错误","提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null, "数据更新成功！","提示",JOptionPane.ERROR_MESSAGE);	
				observable.setChanged();
				observable.notifyObservers();
			}
		});
		btnUpdate.setBounds(147, 295, 113, 27);
		getContentPane().add(btnUpdate);

		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(308, 295, 113, 27);
		getContentPane().add(btnExit);

	}

	public void setId(int id) {
		try {
			ow = Client.outWarehouseDao.get(id, Session.getToken());
			if(ow!=null) {
				cbDid.setSelectedItem(Client.warehouseDao.get(ow.getDid(),Session.getToken()));
			}
			cbWareName.setSelectedItem(ow.getWareName());
			txtOutDate.setText(ow.getOutDate());
			txtWeight.setText(String.valueOf(ow.getWeight()));
			txtRemark.setText(ow.getRemark());
		} catch (RemoteException re) {
			MyToolKit.showError(re);
			return;
		} catch (TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}
		
	}
}
