package com.goods.view;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.Client;
import com.bean.Goods;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class UpdateGoodsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtWareName;
	private JTextField txtWareBewrite;
	private JTextField txtSpec;
	private JTextField txtStockPrice;
	private JTextField txtRetallPrice;
	private JTextField txtAssocalatorPrice;
	
	private Goods obj;

	public void setId(int id) {
		try {
			obj = Client.goodsDao.get(id, Session.getToken());
		}catch(IOException e) {
			e.printStackTrace();			
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}		
		txtWareName.setText(obj.getWareName());
		txtWareBewrite.setText(obj.getWareBewrite());
		txtSpec.setText(obj.getSpec());
		txtStockPrice.setText(String.valueOf(obj.getStockPrice()));
		txtRetallPrice.setText(String.valueOf(obj.getRetallPrice()));
		txtAssocalatorPrice.setText(String.valueOf(obj.getAssoclatorPrice()));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateGoodsFrame frame = new UpdateGoodsFrame();
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
	public UpdateGoodsFrame() {
		setTitle("\u4FEE\u6539\u8D27\u54C1");

		setSize(647,249);
		setLocationRelativeTo(null);		
		
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
		
		txtWareBewrite = new JTextField();
		txtWareBewrite.setColumns(10);
		txtWareBewrite.setBounds(439, 27, 136, 24);
		contentPane.add(txtWareBewrite);
		
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
		
		txtAssocalatorPrice = new JTextField();
		txtAssocalatorPrice.setColumns(10);
		txtAssocalatorPrice.setBounds(439, 98, 136, 24);
		contentPane.add(txtAssocalatorPrice);
		
		JLabel label_15 = new JLabel("*");
		label_15.setBounds(581, 101, 20, 18);
		contentPane.add(label_15);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Supplier sp = new Supplier();
				String wareName=txtWareName.getText();
				String wareBewrite=txtWareBewrite.getText();
				String spec=txtSpec.getText();
				String stockPrice=txtStockPrice.getText();
				String assoclatorPrice=txtAssocalatorPrice.getText();
				String retallPrice=txtRetallPrice.getText();

				if(wareName.equals("") || wareBewrite.equals("") || wareBewrite.equals("") ||(spec.equals(""))||(retallPrice.equals(""))
						||(stockPrice.equals(""))||(assoclatorPrice.equals(""))) {					
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					obj.setWareName(wareName);
				    obj.setWareBewrite(wareBewrite);
					obj.setSpec(spec);
					obj.setStockPrice(Float.parseFloat(stockPrice));
					obj.setRetallPrice(Float.parseFloat(retallPrice));
					obj.setAssoclatorPrice(Float.parseFloat(assoclatorPrice));
					Client.goodsDao.update(obj, Session.getToken());
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
		btnUpdate.setBounds(189, 151, 113, 27);
		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(344, 151, 113, 27);
		contentPane.add(btnExit);
	}
}
