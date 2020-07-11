package com.goods.view;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.Client;
import com.bean.Goods;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;


public class AddGoodsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtWareName;
	private JTextField txtWareRewrite;
	private JTextField txtSpec;
	private JTextField txtStockPrice;
	private JTextField txtRetallPrice;
	private JTextField txtAssoclatorPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoodsFrame frame = new AddGoodsFrame();
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
	public AddGoodsFrame() {
		setTitle("\u6DFB\u52A0\u8D27\u54C1");		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(647,255);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 647, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D27\u54C1\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(37, 30, 99, 18);
		contentPane.add(lblNewLabel);
		
		txtWareName = new JTextField();
		txtWareName.setBounds(136, 27, 136, 24);
		contentPane.add(txtWareName);
		txtWareName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(278, 30, 20, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u8D27\u54C1\u63CF\u8FF0\uFF1A");
		label.setBounds(340, 30, 99, 18);
		contentPane.add(label);
		
		txtWareRewrite = new JTextField();
		txtWareRewrite.setColumns(10);
		txtWareRewrite.setBounds(439, 27, 136, 24);
		contentPane.add(txtWareRewrite);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(581, 30, 20, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5355  \u4F4D\uFF1A");
		label_2.setBounds(37, 67, 99, 18);
		contentPane.add(label_2);
		
		txtSpec = new JTextField();
		txtSpec.setColumns(10);
		txtSpec.setBounds(136, 64, 136, 24);
		contentPane.add(txtSpec);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(278, 67, 20, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u8FDB \u8D27 \u4EF7\uFF1A");
		label_4.setBounds(340, 67, 99, 18);
		contentPane.add(label_4);
		
		txtStockPrice = new JTextField();
		txtStockPrice.setColumns(10);
		txtStockPrice.setBounds(439, 64, 136, 24);
		contentPane.add(txtStockPrice);
		
		JLabel label_5 = new JLabel("*");
		label_5.setBounds(581, 67, 20, 18);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u96F6 \u552E \u4EF7\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setBounds(37, 101, 99, 18);
		contentPane.add(label_6);
		
		txtRetallPrice = new JTextField();
		txtRetallPrice.setColumns(10);
		txtRetallPrice.setBounds(136, 98, 136, 24);
		contentPane.add(txtRetallPrice);
		
		JLabel label_7 = new JLabel("*");
		label_7.setBounds(278, 101, 20, 18);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("\u4F1A \u5458 \u4EF7\uFF1A");
		label_8.setBounds(340, 101, 99, 18);
		contentPane.add(label_8);
		
		txtAssoclatorPrice = new JTextField();
		txtAssoclatorPrice.setColumns(10);
		txtAssoclatorPrice.setBounds(439, 98, 136, 24);
		contentPane.add(txtAssoclatorPrice);
		
		JLabel label_11 = new JLabel("*");
		label_11.setBounds(581, 101, 20, 18);
		contentPane.add(label_11);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String wareName=txtWareName.getText();
				String wareBewrite=txtWareRewrite.getText();
				String spec=txtSpec.getText();
				String stockPrice=txtStockPrice.getText();
				String assoclatorPrice=txtAssoclatorPrice.getText();
				String retallPrice=txtRetallPrice.getText();
				if(wareName.equals("") || wareBewrite.equals("") || wareBewrite.equals("") ||(spec.equals(""))||(retallPrice.equals(""))
						||(stockPrice.equals(""))||(assoclatorPrice.equals(""))) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					Goods obj = new Goods();
					obj.setWareName(wareName);
				    obj.setWareBewrite(wareBewrite);
					obj.setSpec(spec);
					obj.setStockPrice(Float.parseFloat(stockPrice));
					obj.setRetallPrice(Float.parseFloat(retallPrice));
					obj.setAssoclatorPrice(Float.parseFloat(assoclatorPrice));
					Client.goodsDao.add(obj, Session.getToken());
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(getParent(), "价格应填写数字！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				txtWareName.setText("");
				txtWareRewrite.setText("");
				txtSpec.setText("");
				txtStockPrice.setText("");
				txtAssoclatorPrice.setText("");
				txtRetallPrice.setText("");
				JOptionPane.showMessageDialog(getParent(), "数据添加成功！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}		

		});
		btnAdd.setBounds(189, 161, 113, 27);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(344, 161, 113, 27);
		contentPane.add(btnExit);
	}
}
