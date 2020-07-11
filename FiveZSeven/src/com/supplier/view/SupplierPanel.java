package com.supplier.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.Client;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;
import com.supplier.bean.Supplier;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;

public class SupplierPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	//private LocalTableModel model = new LocalTableModel();
	private MyTableModel<Supplier, Integer> model;
	private JTextField txtCName;
	private JTextField txtAddress;
	
	public SupplierPanel() {
		setLayout(new BorderLayout(0, 0));
		
		model = new MyTableModel<Supplier, Integer>(new String[] {"编号", "客户名称", "地址", "联系人", "联系电话", "传真", "邮编", "银行账号", "网址", "EMail", "备注"});
		model.setTypes(new Class[] { java.lang.Object.class, java.lang.String.class, 
			java.lang.String.class,java.lang.String.class,java.lang.String.class,
			java.lang.String.class,java.lang.String.class,java.lang.String.class,
			java.lang.String.class,java.lang.String.class,java.lang.String.class });
		model.setCanEdit(new boolean[] {false,false,false,false,false,false,false,false,
			false,false,false});
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		fixPanel.setPreferredSize(new Dimension(800, 50));
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5BA2\u6237\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(9, 19, 87, 18);
		fixPanel.add(lblNewLabel);
		
		txtCName = new JTextField();
		txtCName.setBounds(105, 16, 133, 24);
		fixPanel.add(txtCName);
		txtCName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5730\u5740\uFF1A");
		lblNewLabel_1.setBounds(247, 19, 50, 18);
		fixPanel.add(lblNewLabel_1);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(306, 16, 133, 24);
		fixPanel.add(txtAddress);
		txtAddress.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cname = txtCName.getText().trim();
				String address = txtAddress.getText().trim();
				List<Supplier> list  = new ArrayList<Supplier>();
				try {
					if(!"".equals(cname) && "".equals(address)){
						list = Client.supplierDao.selectSupplierByName(cname, Session.getToken());
					}else if("".equals(cname) && !"".equals(address)) {
						list = Client.supplierDao.selectSupplierByAddress(address, Session.getToken());
					}else if(!"".equals(cname) && !"".equals(address)){
						list = Client.supplierDao.selectSupplierByNameAddress(address, cname, Session.getToken());
					}else {
						return;
					}
				}catch(RemoteException re) {
					re.printStackTrace();
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}
				txtCName.setText("");
				txtAddress.setText("");
				fillTable(list);				
			}
		});
		btnSearch.setBounds(448, 15, 75, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSupplierFrame addSupplierFrame = new AddSupplierFrame();
				addSupplierFrame.setVisible(true);
			}
		});
		btnAdd.setBounds(532, 15, 75, 27);
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
				File file = new File("file.txt");
				try {
					file.createNewFile();
					FileOutputStream out = new FileOutputStream(file);
					out.write(Integer.parseInt(sid));
					out.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				UpdateSupplierFrame update = new UpdateSupplierFrame();
				update.setVisible(true);
			}
		});
		btnUpdate.setBounds(616, 15, 75, 27);
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
				if(JOptionPane.showConfirmDialog(getParent(),  "确定要删除编号为"+sid+"的供应商的记录吗？", "信息确认框", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
					return;
				}				
				try {
					Client.supplierDao.deleteSupplier(Integer.parseInt(sid), Session.getToken());
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
		btnDelete.setBounds(700, 15, 75, 27);
		fixPanel.add(btnDelete);
		
		JButton btnRefresh = new JButton("\u5237\u65B0");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Supplier> list = getSuppliers();
				fillTable(list);
			}
		});
		
		btnRefresh.setBounds(786, 15, 75, 27);
		fixPanel.add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(25);
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			list = Client.supplierDao.selectSupplier(Session.getToken());
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
//		model.setRowCount(0);
//		for(int i=0; i<list.size(); i++) {
//			Supplier supplier = (Supplier)list.get(i);
//			model.addRow(new Object[] { supplier.getId(),supplier.getcName(),
//					supplier.getAddress(), supplier.getLinkman(), supplier.getLinkPhone(),
//					supplier.getFaxes(),supplier.getPostNum(),supplier.getBankNum(),
//					supplier.getNetAddress(),supplier.getEmailAddress(),supplier.getRemark()				
//			});
//		}
		fillTable(list);
		table.setModel(model);		
		scrollPane.setViewportView(table);			
	}
	
	private List<Supplier> getSuppliers(){
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			list = Client.supplierDao.selectSupplier(Session.getToken());
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
		}
		return list;
	}
	
	private void fillTable(List<Supplier> list) {
		model.setRowCount(0);
		for(int i=0; i<list.size(); i++) {
			Supplier supplier = (Supplier)list.get(i);
			model.addRow(new Object[] { supplier.getId(),supplier.getcName(),
					supplier.getAddress(), supplier.getLinkman(), supplier.getLinkPhone(),
					supplier.getFaxes(),supplier.getPostNum(),supplier.getBankNum(),
					supplier.getNetAddress(),supplier.getEmailAddress(),supplier.getRemark()				
			});
		}
		repaint();
	}
}
