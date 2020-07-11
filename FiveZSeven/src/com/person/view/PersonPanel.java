package com.person.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.Client;
import com.bean.BasicMessage;
import com.bean.Contact;
import com.bean.Department;
import com.bean.Duty;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class PersonPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtHeadship;
	private JTextField txtDept;
	private JTextField txtContact;
	private JTextField txtOfficePhone;
	private JTextField txtFax;
	private JTextField txtEmail;
	private JTextField txtFaddress;
	private JRadioButton rdbtnSex_man;
	private JRadioButton rdbtnSex_women;
	private JComboBox<BasicMessage> comboBasicMessage;
	
	DefaultComboBoxModel<Department> deptModel = new DefaultComboBoxModel<Department>();
	DefaultComboBoxModel<BasicMessage> basicModel = new DefaultComboBoxModel<BasicMessage>();
	private Map<Integer, String> depts = new HashMap<Integer, String>();
	private Map<Integer, String> headships = new HashMap<Integer, String>();
	
	public PersonPanel() {
		setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(900,500));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setSize(600,150);
		fixPanel.setPreferredSize(new Dimension(600, 50));
		fixPanel.setLayout(null);
		
		JComboBox<Department> comboDepartment = new JComboBox<Department>();
		comboDepartment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					Department dept = (Department)e.getItem();
					if(dept==null) return;
					basicModel.removeAllElements();
					BasicMessage basic = new BasicMessage();
					basic.setDept(dept.getId());
					try {						
						List<BasicMessage> basics = Client.basicMessageDao.search(basic, Session.getToken());
						for(int i=0;i<basics.size();i++) {
							basicModel.addElement(basics.get(i));
						}
						basicModel.setSelectedItem(null);
						clearBasicMessage();
						clearContact();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (TokenUnvalidException e1) {
						MyToolKit.login();
						return;
					}
				}
			}
		});
		comboDepartment.setBounds(69, 16, 193, 24);
		try {
			List<Department> departments = Client.departmentDao.list(Session.getToken());
			Department t;
			for(int i=0;i<departments.size();i++) {
				t=departments.get(i);
				deptModel.addElement(t);
				depts.put(t.getId(), t.getdName());
			}
			deptModel.setSelectedItem(null);
			
			List<Duty> dutys = Client.dutyDao.list(Session.getToken());
			for(int i=0;i<dutys.size();i++) {
				headships.put(dutys.get(i).getId(), dutys.get(i).getHeadshipName());
			}
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (TokenUnvalidException e2) {
			e2.printStackTrace();
		}	
		comboDepartment.setModel(deptModel);
		fixPanel.add(comboDepartment);
		
		comboBasicMessage = new JComboBox<BasicMessage>();
		comboBasicMessage.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					clearBasicMessage();
					clearContact();
					BasicMessage basic = (BasicMessage)e.getItem();
					if(basic==null) {
						return;
					}
					txtName.setText(basic.getName());
					txtAge.setText(String.valueOf(basic.getAge()));
					txtDept.setText(depts.get(basic.getDept()));
					txtHeadship.setText(headships.get(basic.getHeadship()));
					if("男".equals(basic.getSex())) {
						rdbtnSex_man.setSelected(true);
					}else {
						rdbtnSex_women.setSelected(true);
					}
					try {
						Contact contact = new Contact();
						contact.setHid(basic.getId());
						List<Contact> contacts = Client.contactDao.search(contact, Session.getToken());
						if(contacts.isEmpty()) {
							clearContact();
						}else {
							contact = contacts.get(0);
							txtContact.setText(contact.getContact());
							txtOfficePhone.setText(contact.getOfficePhone());
							txtFax.setText(contact.getFax());
							txtEmail.setText(contact.getEmail());
							txtFaddress.setText(contact.getFaddress());
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (TokenUnvalidException e1) {
						MyToolKit.login();
						return;
					}
				}
			}
		});
		comboBasicMessage.setBounds(337, 16, 118, 24);
		comboBasicMessage.setModel(basicModel);
		fixPanel.add(comboBasicMessage);
		
		JLabel lblNewLabel = new JLabel("\u90E8\u95E8\uFF1A");
		lblNewLabel.setBounds(14, 19, 59, 18);
		fixPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_1.setBounds(276, 19, 59, 18);
		fixPanel.add(lblNewLabel_1);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddPersonFrame addPersonFrame = new AddPersonFrame();
				addPersonFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						basicModel.removeAllElements();
						deptModel.setSelectedItem(null);
					}					
				});
				addPersonFrame.setVisible(true);				
			}
			
		});
		btnAdd.setBounds(557, 15, 80, 27);
		fixPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicMessage basicMessage = (BasicMessage) comboBasicMessage.getSelectedItem();
				if (basicMessage==null) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					UpdatePersonFrame update = new UpdatePersonFrame();
					update.getObservable().addObserver(new Observer() {

						@Override
						public void update(Observable o, Object obj) {
							BasicMessage bm =(BasicMessage)obj;
							basicModel.removeElement(basicModel.getSelectedItem());
							basicModel.addElement(bm);
							basicModel.setSelectedItem(bm);							
						}
						
					});
					update.setId(basicMessage.getId());
					update.setVisible(true);
				}catch(Exception ex) {
					ex.printStackTrace();
				}				
			}
			
		});
		btnUpdate.setBounds(651, 15, 80, 27);
		fixPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BasicMessage basicMessage = (BasicMessage) comboBasicMessage.getSelectedItem();
				if (basicMessage==null) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要删除的数据！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
				if(JOptionPane.showConfirmDialog(getParent(),  "确定要删除员工"+basicMessage.getName()+"的记录吗？", "信息确认框", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
					return;
				}				
				try {
					Client.contactDao.delete(basicMessage.getId(), Session.getToken());
					Client.basicMessageDao.delete(basicMessage.getId(), Session.getToken());
					clearBasicMessage();
					clearContact();
					basicModel.removeElement(basicMessage);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
				JOptionPane.showMessageDialog(getParent(), "数据删除成功！", "信息提示框", JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}
		});
		btnDelete.setBounds(745, 15, 80, 27);
		fixPanel.add(btnDelete);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new CardLayout(0, 0));
		panel.add(cardPanel, BorderLayout.CENTER);
		
		JList<String> tabList = new JList<String>();
		tabList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int sel = tabList.getSelectedIndex();
				if(sel==0) {
					((CardLayout)cardPanel.getLayout()).show(cardPanel, "basic");
				}else if(sel==1) {
					((CardLayout)cardPanel.getLayout()).show(cardPanel, "contact");
				}				
			}
		});
		tabList.setSize(150, 500);
		tabList.setPreferredSize(new Dimension(150,500));
		tabList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"\u57FA\u672C\u4FE1\u606F", "\u8054\u7CFB\u65B9\u5F0F"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		tabList.setSelectedIndex(0);
		panel.add(tabList, BorderLayout.WEST);		
	
		
		JPanel basicPanel = new JPanel();
		basicPanel.setBorder(new TitledBorder(null, "\u57FA\u672C\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cardPanel.add(basicPanel, "basic");
		basicPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_2.setBounds(25, 36, 72, 18);
		basicPanel.add(lblNewLabel_2);
		
		txtName = new JTextField();
		txtName.setBounds(102, 33, 143, 24);
		basicPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel label = new JLabel("\u5E74\u9F84\uFF1A");
		label.setBounds(289, 36, 72, 18);
		basicPanel.add(label);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(366, 33, 143, 24);
		basicPanel.add(txtAge);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setBounds(25, 76, 72, 18);
		basicPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u804C\u52A1\uFF1A");
		label_2.setBounds(289, 76, 72, 18);
		basicPanel.add(label_2);
		
		txtHeadship = new JTextField();
		txtHeadship.setColumns(10);
		txtHeadship.setBounds(366, 73, 143, 24);
		basicPanel.add(txtHeadship);
		
		JLabel label_3 = new JLabel("\u90E8\u95E8\uFF1A");
		label_3.setBounds(25, 111, 72, 18);
		basicPanel.add(label_3);
		
		txtDept = new JTextField();
		txtDept.setColumns(10);
		txtDept.setBounds(102, 108, 143, 24);
		basicPanel.add(txtDept);
		
		rdbtnSex_man = new JRadioButton("\u7537");
		rdbtnSex_man.setBounds(102, 72, 55, 27);
		basicPanel.add(rdbtnSex_man);
		
		rdbtnSex_women = new JRadioButton("\u5973");
		rdbtnSex_women.setBounds(173, 72, 72, 27);
		basicPanel.add(rdbtnSex_women);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnSex_man);
		bg.add(rdbtnSex_women);
		
		JPanel contactPanel = new JPanel();
		contactPanel.setBorder(new TitledBorder(null, "\u8054\u7CFB\u65B9\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cardPanel.add(contactPanel, "contact");
		contactPanel.setLayout(null);
		
		JLabel label_4 = new JLabel("\u624B\u673A\uFF1A");
		label_4.setBounds(43, 42, 72, 18);
		contactPanel.add(label_4);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(120, 39, 143, 24);
		contactPanel.add(txtContact);
		
		JLabel label_5 = new JLabel("\u529E\u516C\u7535\u8BDD\uFF1A");
		label_5.setBounds(277, 42, 102, 18);
		contactPanel.add(label_5);
		
		txtOfficePhone = new JTextField();
		txtOfficePhone.setColumns(10);
		txtOfficePhone.setBounds(384, 39, 143, 24);
		contactPanel.add(txtOfficePhone);
		
		JLabel label_6 = new JLabel("\u4F20\u771F\uFF1A");
		label_6.setBounds(43, 87, 72, 18);
		contactPanel.add(label_6);
		
		txtFax = new JTextField();
		txtFax.setColumns(10);
		txtFax.setBounds(120, 84, 143, 24);
		contactPanel.add(txtFax);
		
		JLabel label_7 = new JLabel("\u90AE\u7BB1\uFF1A");
		label_7.setBounds(307, 87, 72, 18);
		contactPanel.add(label_7);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(384, 84, 143, 24);
		contactPanel.add(txtEmail);
		
		JLabel label_8 = new JLabel("\u4F4F\u5740\uFF1A");
		label_8.setBounds(43, 135, 72, 18);
		contactPanel.add(label_8);
		
		txtFaddress = new JTextField();
		txtFaddress.setColumns(10);
		txtFaddress.setBounds(120, 132, 143, 24);
		contactPanel.add(txtFaddress);
		
	}
	
	private void clearBasicMessage() {
		txtName.setText("");
		txtAge.setText("");
		txtDept.setText("");
		txtHeadship.setText("");
	}
	private void clearContact() {
		txtContact.setText("");
		txtOfficePhone.setText("");
		txtFax.setText("");
		txtEmail.setText("");
		txtFaddress.setText("");
	}

}
