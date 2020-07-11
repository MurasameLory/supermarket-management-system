package com.seller.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.Client;
import com.bean.Seller;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtSellName;
	private JTextField txtAddress;
	private MyTableModel<Seller, Integer> model;
	
	public SellerPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setSize(600,150);
		fixPanel.setPreferredSize(new Dimension(600, 50));
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u9500\u552E\u5546\u540D\u79F0");
		lblNewLabel.setBounds(14, 19, 99, 18);
		fixPanel.add(lblNewLabel);
		
		txtSellName = new JTextField();
		txtSellName.setBounds(127, 16, 131, 24);
		fixPanel.add(txtSellName);
		txtSellName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5730\u5740");
		lblNewLabel_1.setBounds(272, 19, 51, 18);
		fixPanel.add(lblNewLabel_1);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(337, 16, 112, 24);
		fixPanel.add(txtAddress);
		txtAddress.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seller obj = new Seller();
				obj.setAddress(txtAddress.getText().trim());
				obj.setSellName(txtSellName.getText().trim());
				try {
					List<Seller> list = Client.sellerDao.search(obj, Session.getToken());
					fillTable(list);
				}catch(RemoteException re) {
					re.printStackTrace();
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}
				txtSellName.setText("");
				txtAddress.setText("");
			}
		});
		btnSearch.setBounds(463, 15, 80, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSellerFrame addSellerFrame = new AddSellerFrame();
				addSellerFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						try {
							List<Seller> list = new ArrayList<Seller>();
							list = Client.sellerDao.list(Session.getToken());
							fillTable(list);
						}catch(RemoteException e) {
							e.printStackTrace();
						} catch (TokenUnvalidException e1) {
							MyToolKit.login();
							return;
						}
					}					
				});
				addSellerFrame.setVisible(true);
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
				UpdateSellerFrame update = new UpdateSellerFrame();
				update.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						try {
							List<Seller> list = new ArrayList<Seller>();
							list = Client.sellerDao.list(Session.getToken());
							fillTable(list);
						}catch(RemoteException e) {
							e.printStackTrace();
						} catch (TokenUnvalidException e1) {
							MyToolKit.login();
							return;
						}
					}					
				});
				try {
					update.setId(Integer.parseInt(sid));
					update.setVisible(true);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
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
					Client.sellerDao.delete(Integer.parseInt(sid), Session.getToken());
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
		
		table = new JTable();
		table.setRowHeight(25);
		model = new MyTableModel<Seller, Integer>(new String[] {"编号","客户名称","地址","联系人","联系电话","传真","邮编","银行账号","网址","Email地址","备注"});
		model.setCanEdit(new boolean[] {false,false,false,false,false,false,false,false,false,false,false});
		model.setTypes(new Class[] {Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class});
		table.setModel(model);
		scrollPane.setViewportView(table);

		try {
			List<Seller> list = new ArrayList<Seller>();
			list = Client.sellerDao.list(Session.getToken());
			fillTable(list);
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
	}
	
	private void fillTable(List<Seller> list) {
		model.setRowCount(0);
		Seller obj;
		for(int i=0; i<list.size(); i++) {
			obj = list.get(i);
			model.addRow(new Object[] { obj.getId(),obj.getSellName(),
					obj.getAddress(), obj.getLinkman(), obj.getLinkPhone(),
					obj.getFaxNum(),obj.getPostNum(),obj.getBankNum(),
					obj.getNetAddress(),obj.getEmailAddress(),obj.getRemark()				
			});
		}
		repaint();
	}

}
