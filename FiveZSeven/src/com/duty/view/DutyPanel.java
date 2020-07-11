package com.duty.view;

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
import com.bean.Duty;
import com.common.MyTableModel;

public class DutyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtHeadshipName;
	private MyTableModel<Duty, Integer> model;
	
	public DutyPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setSize(600,150);
		fixPanel.setPreferredSize(new Dimension(600, 50));
		fixPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u804C\u52A1\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(14, 19, 99, 18);
		fixPanel.add(lblNewLabel);
		
		txtHeadshipName = new JTextField();
		txtHeadshipName.setBounds(127, 16, 314, 24);
		fixPanel.add(txtHeadshipName);
		txtHeadshipName.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Duty duty = new Duty();
				duty.setHeadshipName(txtHeadshipName.getText().trim());
				model.fillSearch(duty);
				txtHeadshipName.setText("");
			}
		});
		btnSearch.setBounds(463, 15, 80, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDutyFrame addDutyFrame = new AddDutyFrame();
				addDutyFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						model.fillList();
					}					
				});
				addDutyFrame.setVisible(true);
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
				int id = (int) model.getValueAt(row, 0);
				UpdateDutyFrame updateFrame = new UpdateDutyFrame();
				updateFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						model.fillList();
					}
				});
				updateFrame.setId(id);
				updateFrame.setVisible(true);
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
				model.remove(row, 0);
				JOptionPane.showMessageDialog(getParent(), "数据删除成功！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnDelete.setBounds(745, 15, 80, 27);
		fixPanel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		model = new MyTableModel<Duty, Integer>(new String[] {"编号","职务名"});
		model.setCanEdit(new boolean[] {false,false});
		model.setTypes(new Class[] {Integer.class, String.class});
		model.setDao(Client.dutyDao);
		model.fillList();
		table = new JTable();
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		table.setModel(model);	
	}

}
