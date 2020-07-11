package com.inwarehouse.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.Client;
import com.bean.InWarehouse;
import com.common.MyTableModel;
import com.common.util.MyToolKit;

public class InWarehousePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField txtOid;
	private JTextField txtJoinTime;
	private MyTableModel<InWarehouse, Integer> model;
	private JTable table;

	public InWarehousePanel() {
		setPreferredSize(new Dimension(900,500));
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		fixPanel.setPreferredSize(new Dimension(900,80));
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		lblNewLabel.setBounds(14, 32, 72, 18);
		fixPanel.add(lblNewLabel);
		
		txtOid = new JTextField();
		txtOid.setBounds(92, 29, 134, 24);
		fixPanel.add(txtOid);
		txtOid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5165\u5E93\u65F6\u95F4\uFF1A");
		lblNewLabel_1.setBounds(240, 32, 86, 18);
		fixPanel.add(lblNewLabel_1);
		
		txtJoinTime = new JTextField();
		txtJoinTime.setBounds(340, 29, 121, 24);
		fixPanel.add(txtJoinTime);
		txtJoinTime.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oid = txtOid.getText().trim();
				String joinTime = txtJoinTime.getText().trim();
				InWarehouse iw = new InWarehouse();
				if(!"".equals(oid)) {
					iw.setOid(oid);
				}
				if(!"".equals(joinTime)) {
					iw.setJoinTime(joinTime);
				}
				model.fillSearch(iw);
				txtOid.setText("");
				txtJoinTime.setText("");
			}
		});
		btnSearch.setBounds(466, 28, 78, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddInWarehouseFrame addFrame = new AddInWarehouseFrame();
				addFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						model.fillList();
					}
				});
				addFrame.setVisible(true);
			}
		});
		btnAdd.setBounds(551, 28, 78, 27);
		fixPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row<0) {
					MyToolKit.showWarning("请先选择要更新的数据");
					return;
				}
				UpdateInWarehouseFrame updateFrame = new UpdateInWarehouseFrame();
				int id = (int) model.getValueAt(row, 0);
				updateFrame.setId(id);
				updateFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						model.fillList();
					}
				});
				updateFrame.setVisible(true);
			}
		});
		btnUpdate.setBounds(636, 28, 78, 27);
		fixPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row<0) {
					MyToolKit.showWarning("请先选择要删除的数据");
					return;
				}
				if(MyToolKit.showConfirm("你确定要删除选中的数据吗？")!=JOptionPane.YES_OPTION) {
					return;
				}
				model.remove(row, 0);
				MyToolKit.showMessage("数据删除成功！");
			}
		});
		btnDelete.setBounds(721, 28, 78, 27);
		fixPanel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(900,420));
		scrollPane.setSize(900, 420);
		add(scrollPane, BorderLayout.CENTER);

		table=new JTable();
		model = new MyTableModel<InWarehouse, Integer>(new String[] {"编号", "订单号", "仓库编号", "货品名称", "入库时间", "重量", "备注"});
		model.setCanEdit(new boolean[] {false,false,false,false,false,false,false});
		model.setTypes(new Class[] {Object.class, String.class, String.class, String.class, String.class, String.class, String.class});
		model.setDao(Client.inWarehouseDao);
		model.fillList();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumn("编号").setPreferredWidth(80);
		table.getColumn("订单号").setPreferredWidth(180);
		table.getColumn("仓库编号").setPreferredWidth(80);
		table.getColumn("货品名称").setPreferredWidth(280);
		table.getColumn("入库时间").setPreferredWidth(160);
		table.getColumn("重量").setPreferredWidth(120);
		table.getColumn("备注").setPreferredWidth(300);
		scrollPane.setViewportView(table);
	}

}
