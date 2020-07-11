package com.duty.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

public class UpdateDutyFrame extends MyFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtHeadshipName;
	
	private Duty obj;

	public void setId(int id) {
		try {
			obj = Client.dutyDao.get(id, Session.getToken());
		}catch(IOException e) {
			e.printStackTrace();			
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}		
		txtHeadshipName.setText(obj.getHeadshipName());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDutyFrame frame = new UpdateDutyFrame();
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
	public UpdateDutyFrame() {
		setTitle("\u4FEE\u6539\u804C\u52A1");

		setSize(442,164);
		setLocationRelativeTo(null);		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u804C\u52A1\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(37, 30, 99, 18);
		contentPane.add(lblNewLabel);
		
		txtHeadshipName = new JTextField();
		txtHeadshipName.setBounds(136, 27, 246, 24);
		contentPane.add(txtHeadshipName);
		txtHeadshipName.setColumns(10);

		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(388, 30, 20, 18);
		contentPane.add(lblNewLabel_1);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String headshipName=txtHeadshipName.getText();
				if(headshipName.equals("")) {					
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					obj.setHeadshipName(headshipName);
					Client.dutyDao.update(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				}catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(getParent(), "价格应填写数字！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(getParent(), "数据更新成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}		

		});
		btnUpdate.setBounds(74, 79, 113, 27);
		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(229, 79, 113, 27);
		contentPane.add(btnExit);
	}
}
