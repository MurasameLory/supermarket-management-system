package com.outwarehouse.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.Client;
import com.bean.OutWarehouse;
import com.bean.Warehouse;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;
import com.supplier.bean.Supplier;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OutWarehousePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtOutDate;
	private MyTableModel<OutWarehouse, Integer> model;
	private JComboBox<Warehouse> cbWarehouse;
	private DefaultComboBoxModel<Warehouse> warehouseModel;

	public OutWarehousePanel() {
		this.setPreferredSize(new Dimension(900, 450));
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		fixPanel.setPreferredSize(new Dimension(900,80));
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setLayout(null);
		
		JLabel label = new JLabel("\u4ED3\u5E93\uFF1A");
		label.setBounds(13, 30, 56, 18);
		fixPanel.add(label);
		
		JLabel lblChu = new JLabel("\u51FA\u5E93\u65F6\u95F4\uFF1A");
		lblChu.setBounds(234, 30, 86, 18);
		fixPanel.add(lblChu);
		
		txtOutDate = new JTextField();
		txtOutDate.setColumns(10);
		txtOutDate.setBounds(333, 27, 121, 24);
		fixPanel.add(txtOutDate);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Warehouse  w = (Warehouse) cbWarehouse.getSelectedItem();
				OutWarehouse ow = new OutWarehouse();
				if(w!=null) {
					ow.setDid(w.getId());
				}
				String outDate = txtOutDate.getText().trim();
				if(!"".equals(outDate)) {
					ow.setOutDate(outDate);
				}
				model.fillSearch(ow);
				txtOutDate.setText("");
				warehouseModel.setSelectedItem(null);
			}
		});
		btnSearch.setBounds(467, 26, 78, 27);
		fixPanel.add(btnSearch);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row < 0) {
					MyToolKit.showWarning("请先选择要更新的数据");
					return;
				}
				UpdateOutWarehouseFrame updateFrame = new UpdateOutWarehouseFrame();
				updateFrame.getObservable().addObserver(new Observer(){
					@Override
					public void update(Observable o, Object arg) {
						model.fillList();
					}
				});
				updateFrame.setId((int)model.getValueAt(row, 0));
				updateFrame.setVisible(true);
			}
		});
		btnUpdate.setBounds(649, 26, 78, 27);
		fixPanel.add(btnUpdate);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOutWarehouseFrame addFrame = new AddOutWarehouseFrame();
				addFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						model.fillList();
					}					
				});
				addFrame.setVisible(true);
			}
		});
		btnAdd.setBounds(558, 26, 78, 27);
		fixPanel.add(btnAdd);
		
		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row < 0) {
					MyToolKit.showWarning("请先选择要删除的数据");
					return;
				}
				if(MyToolKit.showConfirm("你确定要删除选择的数据吗?")!=JOptionPane.YES_OPTION) {
					return;
				}
				model.remove(row, 0);
				MyToolKit.showMessage("数据删除成功！");
			}
		});
		btnDelete.setBounds(740, 26, 78, 27);
		fixPanel.add(btnDelete);
		
		cbWarehouse = new JComboBox<Warehouse>();
		cbWarehouse.setBounds(82, 27, 139, 24);
		warehouseModel = new DefaultComboBoxModel<Warehouse>();
		try {
			List<Warehouse> list = Client.warehouseDao.list(Session.getToken());
			Warehouse w;
			for(int i=0;i<list.size();i++) {
				w = list.get(i);
				warehouseModel.addElement(w);
			}
			warehouseModel.setSelectedItem(null);
		}catch(RemoteException re){
			MyToolKit.showError(re);
			return;
		}catch(TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}
		cbWarehouse.setModel(warehouseModel);
		fixPanel.add(cbWarehouse);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		model = new MyTableModel<OutWarehouse, Integer>(new String[] {"编号","仓库编号","货品名称","出库时间","重量","备注"});
		model.setCanEdit(new boolean[] {false,false,false,false,false,false});
		model.setTypes(new Class[] {Integer.class,Integer.class,String.class,String.class,Float.class,String.class});
		model.setDao(Client.outWarehouseDao);
		model.fillList();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
	}
	private List<OutWarehouse> getOutWarehouse(){
		List<OutWarehouse> list = new ArrayList<OutWarehouse>();
		try {
			list = Client.outWarehouseDao.selectOutWarehouse(Session.getToken());
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
		}
		return list;
	}
	
}
