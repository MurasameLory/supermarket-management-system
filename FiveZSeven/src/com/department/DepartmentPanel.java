package com.department;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.Client;
import com.bean.Department;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DepartmentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtSearch;
	private MyTableModel<Department, Integer> model;
	
	public DepartmentPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setSize(600,150);
		fixPanel.setPreferredSize(new Dimension(600, 50));
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u6761\u4EF6\uFF1A");
		lblNewLabel.setBounds(14, 19, 99, 18);
		fixPanel.add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(272, 16, 177, 24);
		fixPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"\u90E8\u95E8\u540D\u79F0", "\u8D1F\u8D23\u4EBA"}));
		comboBox.setBounds(127, 16, 131, 24);
		fixPanel.add(comboBox);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department obj = new Department();
				int select = comboBox.getSelectedIndex(); 
				if(select==0) {
					obj.setdName(txtSearch.getText().trim());					
				}else if(select==1) {
					obj.setPrincipal(txtSearch.getText().trim());
				}
				try {
					List<Department> list = Client.departmentDao.search(obj, Session.getToken());
					fillTable(list);
				}catch(RemoteException re) {
					re.printStackTrace();
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}
				txtSearch.setText("");
			}
		});
		btnSearch.setBounds(463, 15, 80, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDepartmentFrame addDepartmentFrame = new AddDepartmentFrame();
				addDepartmentFrame.setVisible(true);
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
				UpdateDepartmentFrame update = new UpdateDepartmentFrame();
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
					Client.departmentDao.delete(Integer.parseInt(sid), Session.getToken());
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
		model = new MyTableModel<Department, Integer>(new String[] {"编号","部门名称","负责人","描述"});
		model.setCanEdit(new boolean[] {false,false,false,false});
		model.setTypes(new Class[] {Object.class, String.class, String.class, String.class});
		table.setModel(model);
		scrollPane.setViewportView(table);

		try {
			List<Department> list = new ArrayList<Department>();
			list = Client.departmentDao.list(Session.getToken());
			fillTable(list);
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
	}
	
	private void fillTable(List<Department> list) {
		model.setRowCount(0);
		Department obj;
		for(int i=0; i<list.size(); i++) {
			obj = list.get(i);
			model.addRow(new Object[] { obj.getId(),obj.getdName(),
					obj.getPrincipal(),obj.getBewrite()				
			});
		}
		repaint();
	}
}
