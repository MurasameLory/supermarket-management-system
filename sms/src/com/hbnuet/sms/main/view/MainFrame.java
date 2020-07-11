package com.hbnuet.sms.main.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	SmallScrollPanel moduleButtonGroup; // 工具条的创建及添加

	private GlassButton baseButton;
	private GlassButton stockButton;
	private GlassButton inWarehouseButton;
	private GlassButton outWarehouseButton;
	private GlassButton departmentButton;
	private GlassButton personButton;

	private JPanel workspacePanel; // 创建下部工作区域

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("\u8D85\u5E02\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("\u6587\u4EF6");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("\u9000\u51FA");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("\u5E2E\u52A9");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("\u5173\u4E8E");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "关于我们");
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(BorderLayout.NORTH, getModuleButtonGroup());
		contentPane.add(BorderLayout.CENTER, getWorkspacePane());

	}

	private JPanel getWorkspacePane() {
		
		return new JPanel();
	}

	private SmallScrollPanel getModuleButtonGroup() {
		if (moduleButtonGroup == null) {
			moduleButtonGroup = new SmallScrollPanel();
			moduleButtonGroup.setBounds(250, 10, 434, 80);
			moduleButtonGroup.setOpaque(false);
			moduleButtonGroup.getAlphaScrollPanel().setViewportView(getJPanel()); // 获取并设置工具条上的按钮
		}
		return moduleButtonGroup;
	}

	private JPanel getJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null); // 赋予按钮布局
		int width = 80, height = 80, i = 0;
		getBaseButton(); // 获得基本档案管理按钮
		baseButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(baseButton);
		getStockButton(); // 获得采购订单按钮
		stockButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(stockButton);
		getInWarehouseButton();
		inWarehouseButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(inWarehouseButton);
		getOutWarehouseButton();
		outWarehouseButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(outWarehouseButton);
		getPersonButton();
		personButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(personButton);
		getDepartmentButton();
		departmentButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(departmentButton);
		panel.setPreferredSize(new Dimension(5 + i++ * 85, 80));
		return panel;
	}

	private GlassButton getDepartmentButton() {
		if (departmentButton == null) {
			departmentButton = new GlassButton();
			departmentButton.setActionCommand("部门管理");
			departmentButton.setIcon(new ImageIcon(getClass().getResource("../icon/bmgl.png")));
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/bmgl.png"));
			departmentButton.setRolloverIcon(icon);
			departmentButton.setSelectedIcon(icon);
			departmentButton.setSelected(true);
			departmentButton.addActionListener(new ToolsButtonActionAdapter());
		}
		return stockButton;

	}

	private GlassButton getPersonButton() {
		if (personButton == null) {
			personButton = new GlassButton();
			personButton.setActionCommand("人员管理");
			personButton.setIcon(new ImageIcon(getClass().getResource("../icon/rygl.png")));
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/rygl.png"));
			personButton.setRolloverIcon(icon);
			personButton.setSelectedIcon(icon);
			personButton.setSelected(true);
			personButton.addActionListener(new ToolsButtonActionAdapter());
		}
		return personButton;

	}

	private GlassButton getOutWarehouseButton() {
		if (outWarehouseButton == null) {
			outWarehouseButton = new GlassButton();
			outWarehouseButton.setActionCommand("出库管理");
			outWarehouseButton.setIcon(new ImageIcon(getClass().getResource("../icon/ckgl.png")));
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/ckgl.png"));
			outWarehouseButton.setRolloverIcon(icon);
			outWarehouseButton.setSelectedIcon(icon);
			outWarehouseButton.setSelected(true);
			outWarehouseButton.addActionListener(new ToolsButtonActionAdapter());
		}
		return stockButton;

	}

	private GlassButton getInWarehouseButton() {
		if (inWarehouseButton == null) {
			inWarehouseButton = new GlassButton();
			inWarehouseButton.setActionCommand("入库管理");
			inWarehouseButton.setIcon(new ImageIcon(getClass().getResource("../icon/rkgl.png")));
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/rkgl.png"));
			inWarehouseButton.setRolloverIcon(icon);
			inWarehouseButton.setSelectedIcon(icon);
			inWarehouseButton.setSelected(true);
			inWarehouseButton.addActionListener(new ToolsButtonActionAdapter());
		}
		return inWarehouseButton;

	}

	private GlassButton getStockButton() {
		if (stockButton == null) {
			stockButton = new GlassButton();
			stockButton.setActionCommand("采购订单管理");
			stockButton.setIcon(new ImageIcon(getClass().getResource("../icon/cgdd.png")));
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/cgdd.png"));
			stockButton.setRolloverIcon(icon);
			stockButton.setSelectedIcon(icon);
			stockButton.setSelected(true);
			stockButton.addActionListener(new ToolsButtonActionAdapter());
		}
		return stockButton;

	}

	private GlassButton getBaseButton() {
		if (baseButton == null) {
			baseButton = new GlassButton();
			baseButton.setActionCommand("基本档案管理"); // 标识button，每一个按钮ActionCommand不同
			baseButton.setIcon(new ImageIcon(getClass().getResource("../icon/jbda.png")));// 目的是在相应目录中找到图片给button设置图标
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/jbda.png"));
			baseButton.setRolloverIcon(icon); // 没有选中之前光标放在图标上时的显示
			baseButton.setSelectedIcon(icon); // 选中之后的显示
			baseButton.setSelected(true);
			baseButton.addActionListener(new ToolsButtonActionAdapter()); // 添加监听器
		}
		return baseButton;

	}

	// 内部类的方法方便调用外部类中的成员变量实现操作
	// 对象实现动作监听器
	class ToolsButtonActionAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			workspacePanel.removeAll(); // 将下部面板所有组件清空
			if(e.getSource()==baseButton) {
				workspacePanel.setBorder(createTitleBorder("基本档案管理"));
				BasePanel basePanel = new BasePanel();
				workspacePanel.add(basePanel,BorderLayout.CENTER);
			}else if(e.getSource() == stockButton) {
				workspacePanel.setBorder(createTitleBorder("采购管理"));
				StockPanel stockPanel = new StockPanel();
				workspacePanel.add(stockPanel);
			}else if(e.getSource() == inWarehouseButton) {
				workspacePanel.setBorder(createTitleBorder("入库管理"));
				InWarehousePanel inWarehousePanel = new InWarehousePanel();
				workspacePanel.add(inWarehousePanel);
			}else if(e.getSource() == outWarehouseButton) {
				workspacePanel.setBorder(createTitleBorder("出库管理"));
				OutWarehousePanel outWarehousePanel = new OutWarehousePanel();
				workspacePanel.add(outWarehousePanel);
			}else if(e.getSource() == personButton) {
				workspacePanel.setBorder(createTitleBorder("人员管理"));
				PersonPanel personPanel = new PersonPanel();
				workspacePanel.add(personPanel);
			}else if(e.getSource() == departmentButton) {
				workspacePanel.setBorder(createTitleBorder("部门管理"));
				DepartmentPanel departmentPanel = new DepartmentPanel();
				workspacePanel.add(departmentPanel);
			}
			workspacePanel.updateUI();//更新面板显示

		}
	}

	public Border createTitleBorder(String title) {
		return new TitledBorder(null,title,TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.TOP,new Font("微软雅黑",Font.BOLD,12),null);
	}

}
