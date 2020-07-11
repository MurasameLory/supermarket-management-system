package com.warehouse.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

public class UpdateWarehouseFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtName;
	private JTextArea txtRemark;
	
	private Warehouse obj;

	public void setId(int id) {
		try {
			obj = Client.warehouseDao.get(id, Session.getToken());
		}catch(IOException e) {
			e.printStackTrace();			
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
		txtName.setText(obj.getName());
		txtRemark.setText(obj.getRemark());		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateWarehouseFrame frame = new UpdateWarehouseFrame();
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
	public UpdateWarehouseFrame() {
		setTitle("\u4FEE\u6539\u4ED3\u5E93");

		setSize(626,243);
		setLocationRelativeTo(null);		
		
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
		
		txtRemark = new JTextArea();
		txtRemark.setBounds(136, 61, 439, 89);
		contentPane.add(txtRemark);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtName.getText();
				if(name.equals("") ) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					obj.setName(name);
					obj.setRemark(txtRemark.getText());
					Client.warehouseDao.update(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
				JOptionPane.showMessageDialog(getParent(), "数据更新成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}		

		});
		btnUpdate.setBounds(188, 163, 113, 27);
		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(343, 163, 113, 27);
		contentPane.add(btnExit);
	}
}
