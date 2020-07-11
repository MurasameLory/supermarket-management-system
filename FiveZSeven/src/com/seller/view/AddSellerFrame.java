package com.seller.view;


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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.Client;
import com.bean.Seller;
import com.common.MyObservable;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;


public class AddSellerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtSellName;
	private JTextField txtAddress;
	private JTextField txtLinkman;
	private JTextField txtLinkPhone;
	private JTextField txtFaxNum;
	private JTextField txtPostNum;
	private JTextField txtBankNum;
	private JTextField txtNetAddress;
	private JTextField txtEmail;
	
	MyObservable observable = new MyObservable();
	
	public MyObservable getObservable() {
		return observable;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSellerFrame frame = new AddSellerFrame();
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
	public AddSellerFrame() {
		setTitle("\u6DFB\u52A0\u9500\u552E\u5546");		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(647,439);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 647, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u9500\u552E\u5546\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(37, 30, 99, 18);
		contentPane.add(lblNewLabel);
		
		txtSellName = new JTextField();
		txtSellName.setBounds(136, 27, 136, 24);
		contentPane.add(txtSellName);
		txtSellName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(278, 30, 20, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u9500\u552E\u5546\u5730\u5740\uFF1A");
		label.setBounds(340, 30, 99, 18);
		contentPane.add(label);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(439, 27, 136, 24);
		contentPane.add(txtAddress);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(581, 30, 20, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(" \u8054\u7CFB\u4EBA\uFF1A");
		label_2.setBounds(37, 67, 99, 18);
		contentPane.add(label_2);
		
		txtLinkman = new JTextField();
		txtLinkman.setColumns(10);
		txtLinkman.setBounds(136, 64, 136, 24);
		contentPane.add(txtLinkman);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(278, 67, 20, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_4.setBounds(340, 67, 99, 18);
		contentPane.add(label_4);
		
		txtLinkPhone = new JTextField();
		txtLinkPhone.setColumns(10);
		txtLinkPhone.setBounds(439, 64, 136, 24);
		contentPane.add(txtLinkPhone);
		
		JLabel label_5 = new JLabel("*");
		label_5.setBounds(581, 67, 20, 18);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel(" \u4F20  \u771F\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setBounds(37, 101, 99, 18);
		contentPane.add(label_6);
		
		txtFaxNum = new JTextField();
		txtFaxNum.setColumns(10);
		txtFaxNum.setBounds(136, 98, 136, 24);
		contentPane.add(txtFaxNum);
		
		JLabel label_7 = new JLabel("*");
		label_7.setBounds(278, 101, 20, 18);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel(" \u90AE  \u7F16\uFF1A");
		label_8.setBounds(340, 101, 99, 18);
		contentPane.add(label_8);
		
		txtPostNum = new JTextField();
		txtPostNum.setColumns(10);
		txtPostNum.setBounds(439, 98, 136, 24);
		contentPane.add(txtPostNum);
		
		JLabel label_10 = new JLabel("\u94F6\u884C\u8D26\u53F7\uFF1A");
		label_10.setBounds(37, 138, 99, 18);
		contentPane.add(label_10);
		
		txtBankNum = new JTextField();
		txtBankNum.setColumns(10);
		txtBankNum.setBounds(136, 135, 136, 24);
		contentPane.add(txtBankNum);
		
		JLabel label_11 = new JLabel("*");
		label_11.setBounds(278, 138, 20, 18);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel(" \u7F51  \u5740\uFF1A");
		label_12.setBounds(340, 138, 99, 18);
		contentPane.add(label_12);
		
		txtNetAddress = new JTextField();
		txtNetAddress.setColumns(10);
		txtNetAddress.setBounds(439, 135, 136, 24);
		contentPane.add(txtNetAddress);
		
		JLabel label_14 = new JLabel(" \u90AE  \u7BB1\uFF1A");
		label_14.setBounds(37, 175, 99, 18);
		contentPane.add(label_14);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(136, 172, 136, 24);
		contentPane.add(txtEmail);
		
		JLabel label_15 = new JLabel("*");
		label_15.setBounds(278, 175, 20, 18);
		contentPane.add(label_15);
		
		JLabel label_18 = new JLabel(" \u5907  \u6CE8\uFF1A");
		label_18.setBounds(37, 235, 99, 18);
		contentPane.add(label_18);
		
		JTextArea txtRemark = new JTextArea();
		txtRemark.setBounds(136, 209, 439, 89);
		contentPane.add(txtRemark);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sellName=txtSellName.getText();
				String address=txtAddress.getText();
				String bankNum=txtBankNum.getText();
				String linkman=txtLinkman.getText();
				String linkPhone=txtLinkPhone.getText();
				String postNum=txtPostNum.getText();
				String faxes=txtFaxNum.getText();
				String netAddress=txtNetAddress.getText();
				String emailAddress=txtEmail.getText();
				if(sellName.equals("") || address.equals("") || bankNum.equals("") ||!(linkPhone.matches("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{3}-\\d{7})$"))||(linkman.equals(""))||(faxes.equals(""))) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
				try {
					Seller obj = new Seller();
					obj.setSellName(sellName);
					obj.setAddress(address);
					obj.setLinkman(linkman);
					obj.setLinkPhone(linkPhone);
					obj.setFaxNum(faxes);
					obj.setBankNum(bankNum);
					obj.setPostNum(postNum);
					obj.setNetAddress(netAddress);
					obj.setEmailAddress(emailAddress);
					obj.setRemark(txtRemark.getText());
					Client.sellerDao.add(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
				txtSellName.setText("");
				txtAddress.setText("");
				txtBankNum.setText("");
				txtLinkman.setText("");
				txtLinkPhone.setText("");
				txtPostNum.setText("");
				txtFaxNum.setText("");
				txtNetAddress.setText("");
				txtEmail.setText("");
				txtRemark.setText("");
				JOptionPane.showMessageDialog(getParent(), "数据添加成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				observable.setChanged();
				observable.notifyObservers();
				repaint();
			}
		});
		btnAdd.setBounds(185, 328, 113, 27);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(340, 328, 113, 27);
		contentPane.add(btnExit);
	}
}
