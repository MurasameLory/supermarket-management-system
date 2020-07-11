package com.person.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Client;
import com.bean.BasicMessage;
import com.bean.Contact;
import com.bean.Department;
import com.bean.Duty;
import com.common.MyObservable;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class UpdatePersonFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtContact;
	private JTextField txtOfficePhone;
	private JTextField txtEmail;
	private JTextField txtFax;
	private JTextField txtFaddress;
	private JRadioButton rbMan;
	private JRadioButton rbWoman;
	
	DefaultComboBoxModel<Department> deptModel = new DefaultComboBoxModel<Department>();
	DefaultComboBoxModel<Duty> dutyModel = new DefaultComboBoxModel<Duty>();
	private Map<Integer, Department> depts = new HashMap<Integer, Department>();
	private Map<Integer, Duty> headships = new HashMap<Integer, Duty>();
	
	private BasicMessage bm = new BasicMessage();
	private Contact c = new Contact();
	
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
					UpdatePersonFrame frame = new UpdatePersonFrame();
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
	public UpdatePersonFrame() {
		setTitle("修改员工");		
		setSize(647,439);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel basicPanel = new JPanel();
		tabbedPane.addTab("基本信息", null, basicPanel, null);
		basicPanel.setLayout(null);
		
		JLabel label = new JLabel("姓名");
		label.setBounds(51, 36, 72, 18);
		basicPanel.add(label);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(128, 33, 143, 24);
		basicPanel.add(txtName);
		
		JLabel label_1 = new JLabel("年龄");
		label_1.setBounds(315, 36, 72, 18);
		basicPanel.add(label_1);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(392, 33, 143, 24);
		basicPanel.add(txtAge);
		
		JLabel label_2 = new JLabel("性别");
		label_2.setBounds(51, 76, 72, 18);
		basicPanel.add(label_2);
		
		rbMan = new JRadioButton("男");
		rbMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bm.setSex("男");
			}
		});
		rbMan.setSelected(true);
		bm.setSex("男");
		rbMan.setBounds(128, 72, 55, 27);
		basicPanel.add(rbMan);
		
		rbWoman = new JRadioButton("女");
		rbWoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bm.setSex("女");
			}
		});
		rbWoman.setBounds(199, 72, 72, 27);
		basicPanel.add(rbWoman);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbMan);
		bg.add(rbWoman);
		
		JLabel label_3 = new JLabel("\u804C\u52A1\uFF1A");
		label_3.setBounds(315, 76, 72, 18);
		basicPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u90E8\u95E8\uFF1A");
		label_4.setBounds(51, 111, 72, 18);
		basicPanel.add(label_4);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setBounds(275, 36, 72, 18);
		basicPanel.add(lblNewLabel);
		
		JLabel label_10 = new JLabel("*");
		label_10.setBounds(275, 76, 72, 18);
		basicPanel.add(label_10);
		
		JLabel label_11 = new JLabel("*");
		label_11.setBounds(542, 36, 72, 18);
		basicPanel.add(label_11);
		
		JLabel label_12 = new JLabel("*");
		label_12.setBounds(275, 114, 72, 18);
		basicPanel.add(label_12);

		try {
			List<Department> departments = Client.departmentDao.list(Session.getToken());
			Department t;
			for(int i=0;i<departments.size();i++) {
				t=departments.get(i);
				deptModel.addElement(t);
				depts.put(t.getId(), t);
			}
			deptModel.setSelectedItem(null);			
			List<Duty> dutys = Client.dutyDao.list(Session.getToken());
			Duty d;
			for(int i=0;i<dutys.size();i++) {
				d=dutys.get(i);
				dutyModel.addElement(d);
				headships.put(d.getId(), d);
			}
			dutyModel.setSelectedItem(null);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (TokenUnvalidException e2) {
			e2.printStackTrace();
		}
		
		JComboBox<Department> cbDept = new JComboBox<Department>();
		cbDept.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					Department dept = (Department)e.getItem();
					bm.setDept(dept.getId());
				}
			}
		});
		cbDept.setBackground(Color.WHITE);
		cbDept.setBounds(128, 108, 143, 24);
		cbDept.setModel(deptModel);
		basicPanel.add(cbDept);
		
		JComboBox<Duty> cbHeadship = new JComboBox<Duty>();
		cbHeadship.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					Duty duty = (Duty)e.getItem();
					bm.setHeadship(duty.getId());
				}
			}
		});
		cbHeadship.setBackground(new Color(255, 255, 255));
		cbHeadship.setBounds(392, 70, 143, 24);
		cbHeadship.setModel(dutyModel);		
		basicPanel.add(cbHeadship);
		
		JButton btnUpdateBasicMessage = new JButton("\u4FEE\u6539");
		btnUpdateBasicMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText().trim();
				String age = txtAge.getText().trim();
				if(name.equals("") || age.equals("") || bm.getDept()==0) {
					JOptionPane.showMessageDialog(getParent(), "将带星号的信息填写完整！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					bm.setAge(Integer.parseInt(age));
					bm.setName(name);
					Client.basicMessageDao.update(bm, Session.getToken());
					
					observable.setChanged();
					observable.notifyObservers(bm);
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(getParent(), "年龄应填写数字","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
			}
		});
		btnUpdateBasicMessage.setBounds(158, 200, 113, 27);
		basicPanel.add(btnUpdateBasicMessage);
		
		JButton btnCloseBasic = new JButton("\u5173\u95ED");
		btnCloseBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCloseBasic.setBounds(315, 200, 113, 27);
		basicPanel.add(btnCloseBasic);
		
		JPanel contactPanel = new JPanel();
		tabbedPane.addTab("\u8054\u7CFB\u8D44\u6599", null, contactPanel, null);
		contactPanel.setLayout(null);
		
		JLabel label_5 = new JLabel("\u624B\u673A\uFF1A");
		label_5.setBounds(52, 40, 72, 18);
		contactPanel.add(label_5);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(129, 37, 143, 24);
		contactPanel.add(txtContact);
		
		JLabel label_6 = new JLabel("\u529E\u516C\u7535\u8BDD\uFF1A");
		label_6.setBounds(286, 40, 102, 18);
		contactPanel.add(label_6);
		
		txtOfficePhone = new JTextField();
		txtOfficePhone.setColumns(10);
		txtOfficePhone.setBounds(393, 37, 143, 24);
		contactPanel.add(txtOfficePhone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(393, 82, 143, 24);
		contactPanel.add(txtEmail);
		
		JLabel label_7 = new JLabel("\u90AE\u7BB1\uFF1A");
		label_7.setBounds(316, 85, 72, 18);
		contactPanel.add(label_7);
		
		txtFax = new JTextField();
		txtFax.setColumns(10);
		txtFax.setBounds(129, 82, 143, 24);
		contactPanel.add(txtFax);
		
		JLabel label_8 = new JLabel("\u4F20\u771F\uFF1A");
		label_8.setBounds(52, 85, 72, 18);
		contactPanel.add(label_8);
		
		JLabel label_9 = new JLabel("\u4F4F\u5740\uFF1A");
		label_9.setBounds(52, 133, 72, 18);
		contactPanel.add(label_9);
		
		txtFaddress = new JTextField();
		txtFaddress.setColumns(10);
		txtFaddress.setBounds(129, 130, 143, 24);
		contactPanel.add(txtFaddress);
		
		JButton btnUpdateContact = new JButton("\u4FEE\u6539");
		btnUpdateContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contact = txtContact.getText().trim();
				String officePhone = txtOfficePhone.getText().trim();
				String fax = txtFax.getText().trim();
				String email = txtEmail.getText().trim();
				if((!"".equals(contact) && !MyToolKit.isTelephone(contact)) 
						|| (!"".equals(officePhone) && !MyToolKit.isTelephone(officePhone)) 
						|| (!"".equals(email) && !MyToolKit.isMail(email))
						|| (!"".equals(fax) && !MyToolKit.isTelephone(fax))) {
					JOptionPane.showMessageDialog(getParent(), "电话或电邮格式不正确！","信息提示框",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					c.setContact(contact);
					c.setOfficePhone(officePhone);
					c.setFax(txtFax.getText().trim());
					c.setEmail(txtEmail.getText().trim());
					c.setFaddress(txtFaddress.getText().trim());
					if(c.getId()==0) {
						BigInteger cid = (BigInteger)Client.contactDao.insert(c, Session.getToken());
						c.setId(cid.intValue());
					}else {
						Client.contactDao.update(c, Session.getToken());
					}
				}catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}				
			}
		});
		btnUpdateContact.setBounds(159, 183, 113, 27);
		contactPanel.add(btnUpdateContact);
		
		JButton btnCloseContact = new JButton("\u5173\u95ED");
		btnCloseContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCloseContact.setBounds(316, 183, 113, 27);
		contactPanel.add(btnCloseContact);
	}

	public void setId(int id) {
		try {
			bm = Client.basicMessageDao.get(id, Session.getToken());
			txtName.setText(bm.getName());
			if("男".equals(bm.getSex())){
				rbMan.setSelected(true);
			}else if("女".equals(bm.getSex())){
				rbWoman.setSelected(true);
			}
			txtAge.setText(String.valueOf(bm.getAge()));
			deptModel.setSelectedItem(depts.get(bm.getDept()));
			dutyModel.setSelectedItem(headships.get(bm.getHeadship()));
			c.setHid(id);
			List<Contact> list = Client.contactDao.search(c, Session.getToken());
			if(! list.isEmpty()){
				c=list.get(0);
			}
			txtContact.setText(c.getContact());
			txtOfficePhone.setText(c.getOfficePhone());
			txtFax.setText(c.getFax());
			txtEmail.setText(c.getEmail());
			txtFaddress.setText(c.getFaddress());
		}catch(IOException e) {
			e.printStackTrace();			
		} catch (TokenUnvalidException e1) {
			MyToolKit.login();
			return;
		}
	}
}
