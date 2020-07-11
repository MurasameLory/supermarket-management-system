package com.purchase.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import com.Client;
import com.bean.InWarehouse;
import com.bean.Purchase;
import com.common.MyTableModel;
import com.common.util.MyToolKit;
import com.common.util.Session;
import com.common.util.TokenUnvalidException;

public class StockPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtCond;
	private JTable table;
	private MyTableModel<Purchase, Integer> model;
	public StockPanel() {

		setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(900,500));
		
		JPanel fixPanel = new JPanel();
		fixPanel.setBackground(Color.WHITE);
		fixPanel.setPreferredSize(new Dimension(900,80));
		add(fixPanel, BorderLayout.NORTH);
		fixPanel.setLayout(null);
		
		JLabel lblSearchCondition = new JLabel("\u67E5\u8BE2\u6761\u4EF6\uFF1A");
		lblSearchCondition.setBounds(14, 30, 93, 18);
		fixPanel.add(lblSearchCondition);
		
		JComboBox<String> cbType = new JComboBox<String>();
		cbType.setBackground(new Color(255, 255, 255));
		cbType.setModel(new DefaultComboBoxModel<String>(new String[] {"\u5BA2\u6237", "\u8BA2\u5355\u53F7", "\u4EA4\u8D27\u65E5\u671F", "\u5546\u54C1\u540D"}));
		cbType.setBounds(115, 27, 110, 24);
		fixPanel.add(cbType);
		
		txtCond = new JTextField();
		txtCond.setBounds(233, 27, 173, 24);
		fixPanel.add(txtCond);
		txtCond.setColumns(10);
		
		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = (String) cbType.getSelectedItem();
				String value = txtCond.getText().trim();
				Purchase p = new Purchase();
				if("客户".equals(type)) {
					p.setsName(value);					
				}else if("订单号".equals(type)) {
					p.setOid(value);
				}else if("交货日期".equals(type)) {
					p.setConsignmentDate(value);
				}else if("商品名".equals(type)) {
					p.setBaleName(value);
				}
				try {
					List<Purchase> list = Client.purchaseDao.search(p, Session.getToken());
					fillTable(list);
				}catch(RemoteException re) {
					MyToolKit.showError(re);
					return;
				}catch(TokenUnvalidException te) {
					MyToolKit.login();
					return;
				}				
			}
		});
		btnSearch.setBounds(414, 26, 113, 27);
		fixPanel.add(btnSearch);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStockFrame addFrame = new AddStockFrame();
				addFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						fillTable();
					}					
				});
				addFrame.setVisible(true);
			}
		});
		btnAdd.setBounds(535, 26, 113, 27);
		fixPanel.add(btnAdd);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedRow();
				if(i<0) {
					MyToolKit.showWarning("请先选择更新的记录");
					return;
				}
				int pid = (int) table.getValueAt(i, 1);
				UpdateStockFrame updateFrame = new UpdateStockFrame();
				updateFrame.getObservable().addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						fillTable();						
					}					
				});
				updateFrame.setId(pid);
				updateFrame.setVisible(true);
			}
		});
		btnUpdate.setBounds(656, 26, 113, 27);
		fixPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedRow();
				if(i<0) {
					MyToolKit.showWarning("请先选择要删除的记录");
					return;
				}
				if(MyToolKit.showConfirm("你确定要删除订单号为"+table.getValueAt(i, 3)+"的采购订单吗？")==JOptionPane.NO_OPTION) {
					return;
				}
				int pid = (int) table.getValueAt(i, 1);
				try {
					Client.purchaseDao.delete(pid, Session.getToken());
					MyToolKit.showMessage("删除数据成功！");
					model.removeRow(i);
				} catch (RemoteException re) {
					MyToolKit.showError(re);
					return;
				} catch (TokenUnvalidException e1) {
					MyToolKit.login();
					return;
				}
			}
		});
		btnDelete.setBounds(777, 26, 113, 27);
		fixPanel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		model = new MyTableModel<Purchase, Integer>(new String[] {"是否入库","编号", "客户", "订单号", "交货日期", "商品名", "金额", "数量"});
		model.setCanEdit(new boolean[] {true,false,false,false,false,false,false,false});
		model.setTypes(new Class[] {Boolean.class,Object.class, String.class,String.class,String.class,String.class,Float.class,String.class});
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.getColumn("是否入库").setCellRenderer(new InWarehouseCellRenderer());
		table.getColumn("是否入库").setCellEditor(new InWarehouseCellEditor());
		table.setRowHeight(25);
		fillTable();
	}
	
	private void fillTable(List<Purchase> list) {
		model.setRowCount(0);
		try {
			Purchase p;
			InWarehouse iwh = new InWarehouse();
			List<InWarehouse> l = new ArrayList<InWarehouse>();
			for(int i=0; i<list.size(); i++) {
				p  = list.get(i);
				iwh.setOid(p.getOid());
				l = Client.inWarehouseDao.search(iwh, Session.getToken());
				if(l.isEmpty()) {
					model.addRow(new Object[] { false, p.getId(),p.getsName(),p.getOid(),p.getConsignmentDate(),p.getBaleName(),p.getMoney(),p.getMount()				
						});
				}else {
					model.addRow(new Object[] { true, p.getId(),p.getsName(),p.getOid(),p.getConsignmentDate(),p.getBaleName(),p.getMoney(),p.getMount()				
					});
				}
			}
		}catch(RemoteException re) {
			MyToolKit.showError(re);
			return;
		}catch(TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}
		repaint();
	}
	
	private void fillTable() {
		model.setRowCount(0);
		try {
			List<Purchase> list = Client.purchaseDao.list(Session.getToken());
			fillTable(list);
		}catch(RemoteException re) {
			MyToolKit.showError(re);
			return;
		}catch(TokenUnvalidException te) {
			MyToolKit.login();
			return;
		}
		repaint();
	}
	
	class InWarehouseCellRenderer extends JCheckBox implements TableCellRenderer{
		private static final long serialVersionUID = 1L;
		private final Border noFocusBorder = new EmptyBorder(1,1,1,1);
		
		public InWarehouseCellRenderer(){
			super();
			setHorizontalAlignment(JLabel.CENTER);
			setBorderPainted(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Object v = table.getModel().getValueAt(row, 0);
			if(v==null){
				return new JLabel();
			} 
			
			if(isSelected){
				setForeground(Color.WHITE);
				setBackground(table.getSelectionBackground());
			}else{
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			
			setSelected(((Boolean)value).booleanValue());
			if(hasFocus){
				setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			}else{
				setBorder(noFocusBorder);
			}
			return this;
		}
		
	}
	
	class InWarehouseCellEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;

		public InWarehouseCellEditor(){
			super(new JCheckBox());
		}
		
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
			if(!isSelected) return null;
			JCheckBox checkBox =(JCheckBox)getComponent();
			checkBox.setHorizontalAlignment(JLabel.CENTER);
			checkBox.setSelected((boolean)value);
			if(checkBox.isSelected()) {
				checkBox.setEnabled(false);
			}else {
				checkBox.setEnabled(true);
				ActionListener[] l = checkBox.getActionListeners();
				for(int i=0;i<l.length;i++)
					checkBox.removeActionListener(l[i]);
				checkBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						checkBox.setSelected(false);
						try {
							AddInWarehouseDialog dialog = new AddInWarehouseDialog();
							int pid =(int)table.getValueAt(row, 1);
							Purchase p = Client.purchaseDao.get(pid, Session.getToken());
							dialog.setPurchase(p);
							dialog.setVisible(true);
							if(dialog.isSuccess()) {
								checkBox.setSelected(true);
								InWarehouseCellEditor.this.stopCellEditing();
							}
						}catch(TokenUnvalidException te) {
							MyToolKit.login();
							return;
						}catch(Exception ex) {
							MyToolKit.showError(ex);
							return;
						}
					}
				});
			}
			return checkBox;
		}		
	}
}
