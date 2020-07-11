package com.department;

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
import com.bean.Department;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class UpdateDepartmentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtDName;
	private JTextField txtPrincipal;
	private JTextArea txtBewrite;
	
	private Department obj;

	public void setId(int id) {
		try {
			obj = Client.departmentDao.get(id, Session.getToken());
		}catch(IOException e) {
			e.printStackTrace();			
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}		
		txtDName.setText(obj.getdName());
		txtPrincipal.setText(obj.getPrincipal());
		txtBewrite.setText(obj.getBewrite());		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDepartmentFrame frame = new UpdateDepartmentFrame();
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
	public UpdateDepartmentFrame() {
		setTitle("\u4FEE\u6539\u90E8\u95E8");

		setSize(623,262);
		setLocationRelativeTo(null);		
		
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
		
		JLabel label_18 = new JLabel("\u63CF    \u8FF0\uFF1A");
		label_18.setBounds(37, 87, 99, 18);
		contentPane.add(label_18);		
		
		txtBewrite = new JTextArea();
		txtBewrite.setBounds(136, 61, 439, 89);
		contentPane.add(txtBewrite);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dName=txtDName.getText().trim();
				String principal=txtPrincipal.getText().trim();
				if(dName.equals("") || principal.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					obj.setdName(dName);
					obj.setPrincipal(principal);
					obj.setBewrite(txtBewrite.getText().trim());
					Client.departmentDao.update(obj, Session.getToken());
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
		btnUpdate.setBounds(180, 180, 113, 27);
		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(335, 180, 113, 27);
		contentPane.add(btnExit);
	}
}
