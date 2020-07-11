package com.warehouse.view;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Client;
import com.bean.Warehouse;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;


public class AddWarehouseFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWarehouseFrame frame = new AddWarehouseFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddWarehouseFrame() {
		setTitle("\u6DFB\u52A0\u4ED3\u5E93");		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(647,264);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 647, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5E93    \u7BA1\uFF1A");
		lblNewLabel.setBounds(37, 30, 99, 18);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(136, 27, 136, 24);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(278, 30, 20, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_18 = new JLabel("\u5907    \u6CE8\uFF1A");
		label_18.setBounds(37, 87, 99, 18);
		contentPane.add(label_18);
		
		JTextArea txtRemark = new JTextArea();
		txtRemark.setBounds(136, 61, 439, 89);
		contentPane.add(txtRemark);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtName.getText();
				if(name.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Warehouse obj = new Warehouse();
				obj.setName(name);
				obj.setRemark(txtRemark.getText());
				
				try {
					Client.warehouseDao.add(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
				txtName.setText("");
				txtRemark.setText("");
				JOptionPane.showMessageDialog(getParent(), "数据添加成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}		

		});
		btnAdd.setBounds(178, 178, 113, 27);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(333, 178, 113, 27);
		contentPane.add(btnExit);
	}
}
