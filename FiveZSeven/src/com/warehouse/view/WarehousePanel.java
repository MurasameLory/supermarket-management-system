package com.warehouse.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.Client;
import com.bean.Warehouse;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class WarehousePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtId;
	private JTextField txtName;
	private MyTableModel<Warehouse, Integer> model;
	
	public WarehousePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setSize(600,150);
		fixPanel.setPreferredSize(new Dimension(600, 50));
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4ED3\u5E93\u7F16\u53F7\uFF1A");
		lblNewLabel.setBounds(14, 19, 99, 18);
		fixPanel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(127, 16, 131, 24);
		fixPanel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5E93\u7BA1\uFF1A");
		lblNewLabel_1.setBounds(272, 19, 51, 18);
		fixPanel.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(337, 16, 112, 24);
		fixPanel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Warehouse obj = new Warehouse();
					obj.setName(txtName.getText().trim());
					String strId = txtId.getText().trim();
					if(!"".equals(strId)) {
						obj.setId(Integer.parseInt(strId));
					}
					List<Warehouse> list = Client.warehouseDao.search(obj, Session.getToken());
					fillTable(list);
				}catch(RemoteException re) {
					re.printStackTrace();
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(getParent(), "请输入整数编号！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				txtId.setText("");
				txtName.setText("");
			}
		});
		btnSearch.setBounds(463, 15, 80, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddWarehouseFrame addWarehouseFrame = new AddWarehouseFrame();
				addWarehouseFrame.setVisible(true);
			}
		});
		btnAdd.setBounds(557, 15, 80, 27);
		fixPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
				String sid = model.getValueAt(row, 0).toString();
				UpdateWarehouseFrame update = new UpdateWarehouseFrame();
				try {
					update.setId(Integer.parseInt(sid));
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				update.setVisible(true);
			}
		});
		btnUpdate.setBounds(651, 15, 80, 27);
		fixPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要删除的数据！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
				String sid = model.getValueAt(row, 0).toString();
				if(JOptionPane.showConfirmDialog(getParent(),  "确定要删除编号为"+sid+"的销售商的记录吗？", "信息确认框", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
					return;
				}				
				try {
					Client.warehouseDao.delete(Integer.parseInt(sid), Session.getToken());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
				JOptionPane.showMessageDialog(getParent(), "数据删除成功！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
				model.removeRow(row);
				repaint();
			}
		});
		btnDelete.setBounds(745, 15, 80, 27);
		fixPanel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		

		model = new MyTableModel<Warehouse, Integer>(new String[] {"编号","库管","描述"});
		model.setCanEdit(new boolean[] {false,false,false});
		model.setTypes(new Class[] {Object.class, String.class, String.class});
		
		table = new JTable();
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		try {
			List<Warehouse> list = new ArrayList<Warehouse>();
			list = Client.warehouseDao.list(Session.getToken());
			fillTable(list);
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
	}
	
	private void fillTable(List<Warehouse> list) {
		model.setRowCount(0);
		Warehouse obj;
		for(int i=0; i<list.size(); i++) {
			obj = list.get(i);
			model.addRow(new Object[] { obj.getId(),obj.getName(),obj.getRemark()});
		}
		repaint();
	}

}
