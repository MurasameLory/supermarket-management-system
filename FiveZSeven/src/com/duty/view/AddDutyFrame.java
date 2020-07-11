package com.duty.view;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Client;
import com.bean.Duty;
import com.common.MyFrame;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;


public class AddDutyFrame extends MyFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtHeadshipName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDutyFrame frame = new AddDutyFrame();
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
	public AddDutyFrame() {
		setTitle("\u6DFB\u52A0\u804C\u52A1");		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(442,188);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 647, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u804C\u52A1\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(30, 44, 99, 18);
		contentPane.add(lblNewLabel);
		
		txtHeadshipName = new JTextField();
		txtHeadshipName.setBounds(129, 41, 247, 24);
		contentPane.add(txtHeadshipName);
		txtHeadshipName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(378, 44, 20, 18);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String headshipName=txtHeadshipName.getText();
				if(headshipName.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					Duty obj = new Duty();
					obj.setHeadshipName(headshipName);
						Client.dutyDao.add(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(getParent(), "价格应填写数字！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				txtHeadshipName.setText("");
				JOptionPane.showMessageDialog(getParent(), "数据添加成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}		

		});
		btnAdd.setBounds(93, 97, 113, 27);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(244, 97, 113, 27);
		contentPane.add(btnExit);
	}
}
