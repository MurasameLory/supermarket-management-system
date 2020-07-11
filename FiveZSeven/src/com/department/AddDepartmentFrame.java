package com.department;


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
import com.bean.Department;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;


public class AddDepartmentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtDName;
	private JTextField txtPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDepartmentFrame frame = new AddDepartmentFrame();
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
	public AddDepartmentFrame() {
		setTitle("\u6DFB\u52A0\u90E8\u95E8");		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(647,263);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 647, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u90E8\u95E8\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(37, 30, 99, 18);
		contentPane.add(lblNewLabel);
		
		txtDName = new JTextField();
		txtDName.setBounds(136, 27, 136, 24);
		contentPane.add(txtDName);
		txtDName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(278, 30, 20, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u8D1F \u8D23 \u4EBA\uFF1A");
		label.setBounds(340, 30, 99, 18);
		contentPane.add(label);
		
		txtPrincipal = new JTextField();
		txtPrincipal.setColumns(10);
		txtPrincipal.setBounds(439, 27, 136, 24);
		contentPane.add(txtPrincipal);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(581, 30, 20, 18);
		contentPane.add(label_1);
		
		JLabel label_18 = new JLabel(" \u5907  \u6CE8\uFF1A");
		label_18.setBounds(37, 87, 99, 18);
		contentPane.add(label_18);
		
		JTextArea txtBewrite = new JTextArea();
		txtBewrite.setBounds(136, 61, 439, 89);
		contentPane.add(txtBewrite);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dName=txtDName.getText().trim();
				String principal=txtPrincipal.getText().trim();
				if(dName.equals("") || principal.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
				try {
					Department obj = new Department();
					obj.setdName(dName);
					obj.setPrincipal(principal);
					obj.setBewrite(txtBewrite.getText());
					Client.departmentDao.add(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
				txtDName.setText("");
				txtPrincipal.setText("");
				txtBewrite.setText("");
				JOptionPane.showMessageDialog(getParent(), "数据添加成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}
		});
		btnAdd.setBounds(171, 181, 113, 27);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(326, 181, 113, 27);
		contentPane.add(btnExit);
	}
}
