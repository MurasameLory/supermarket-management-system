package com.goods.view;

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
import com.bean.Goods;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class GoodsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtWareName;
	private MyTableModel<Goods, Integer> model;
	
	public GoodsPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setSize(600,150);
		fixPanel.setPreferredSize(new Dimension(600, 50));
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D27\u54C1\u540D\u79F0");
		lblNewLabel.setBounds(14, 19, 99, 18);
		fixPanel.add(lblNewLabel);
		
		txtWareName = new JTextField();
		txtWareName.setBounds(127, 16, 314, 24);
		fixPanel.add(txtWareName);
		txtWareName.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Goods goods = new Goods();
				goods.setWareName(txtWareName.getText().trim());
				try {
					List<Goods> list = Client.goodsDao.search(goods, Session.getToken());
					fillTable(list);
				}catch(RemoteException re) {
					re.printStackTrace();
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}
				txtWareName.setText("");
			}
		});
		btnSearch.setBounds(463, 15, 80, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoodsFrame addGoodsFrame = new AddGoodsFrame();
				addGoodsFrame.setVisible(true);
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
				UpdateGoodsFrame update = new UpdateGoodsFrame();
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
					Client.goodsDao.delete(Integer.parseInt(sid), Session.getToken());
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
		

		model = new MyTableModel<Goods, Integer>(new String[] {"编号","货品名称","货品描述","单位","进货价","零售价","会员价"});
		model.setCanEdit(new boolean[] {false,false,false,false,false,false,false});
		model.setTypes(new Class[] {Object.class, String.class, String.class, String.class, String.class, String.class, String.class});
		
		table = new JTable();
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		table.setModel(model);	

		List<Goods> list = new ArrayList<Goods>();
		try {
			list = Client.goodsDao.list(Session.getToken());
		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
		fillTable(list);
	}
	
	private void fillTable(List<Goods> list) {
		model.setRowCount(0);
		for(int i=0; i<list.size(); i++) {
			Goods goods = (Goods)list.get(i);
			model.addRow(new Object[] { goods.getId(),goods.getWareName(),
					goods.getWareBewrite(), goods.getSpec(), goods.getStockPrice(),
					goods.getRetallPrice(),goods.getAssoclatorPrice()				
			});
		}
		repaint();
	}

}
