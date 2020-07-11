package com.main.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.duty.view.DutyPanel;
import com.goods.view.GoodsPanel;
import com.seller.view.SellerPanel;
import com.supplier.view.SupplierPanel;
import com.warehouse.view.WarehousePanel;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class BasePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JPanel tempPanel;

	public BasePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane menuPanel = new JScrollPane();
		add(menuPanel, BorderLayout.WEST);		
		JTree tree = new JTree();

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath selectionPaths = tree.getSelectionPath();
				DefaultMutableTreeNode node =  (DefaultMutableTreeNode) selectionPaths.getLastPathComponent();
				String userObject = (String) node.getUserObject();
				tempPanel.removeAll();
				if("供货商管理".equals(userObject)) {
					SupplierPanel supplierPanel = new SupplierPanel();
					tempPanel.add(supplierPanel, BorderLayout.CENTER);
				}else if("销售商管理".equals(userObject)) {
					SellerPanel sellerPanel = new SellerPanel();
					tempPanel.add(sellerPanel, BorderLayout.CENTER);
				}else if("货品档案管理".equals(userObject)) {
					GoodsPanel goodsPanel = new GoodsPanel();
					tempPanel.add(goodsPanel, BorderLayout.CENTER);
				}else if("仓库管理".equals(userObject)) {
					WarehousePanel warehousePanel = new WarehousePanel();
					tempPanel.add(warehousePanel,BorderLayout.CENTER);
				}else if("职务管理".equals(userObject)) {
					DutyPanel dutyPanel = new DutyPanel();
					tempPanel.add(dutyPanel,BorderLayout.CENTER);
				}
				updateUI();
			}
		});
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("\u57FA\u672C\u6863\u6848\u7BA1\u7406") {
				private static final long serialVersionUID = 1L;
				{
					add(new DefaultMutableTreeNode("\u4F9B\u8D27\u5546\u7BA1\u7406"));
					add(new DefaultMutableTreeNode("\u9500\u552E\u5546\u7BA1\u7406"));
					add(new DefaultMutableTreeNode("\u8D27\u54C1\u6863\u6848\u7BA1\u7406"));
					add(new DefaultMutableTreeNode("\u4ED3\u5E93\u7BA1\u7406"));
					add(new DefaultMutableTreeNode("\u804C\u52A1\u7BA1\u7406"));
				}
			}
		));
		menuPanel.setViewportView(tree);
		
		tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout());
		add(tempPanel, BorderLayout.CENTER);
		
		SupplierPanel supplierPanel = new SupplierPanel();
		tempPanel.add(supplierPanel, BorderLayout.CENTER);
	}

}
