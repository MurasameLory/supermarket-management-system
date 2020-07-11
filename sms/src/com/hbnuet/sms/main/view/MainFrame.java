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

	SmallScrollPanel moduleButtonGroup; // �������Ĵ��������

	private GlassButton baseButton;
	private GlassButton stockButton;
	private GlassButton inWarehouseButton;
	private GlassButton outWarehouseButton;
	private GlassButton departmentButton;
	private GlassButton personButton;

	private JPanel workspacePanel; // �����²���������

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
				JOptionPane.showMessageDialog(getContentPane(), "��������");
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
			moduleButtonGroup.getAlphaScrollPanel().setViewportView(getJPanel()); // ��ȡ�����ù������ϵİ�ť
		}
		return moduleButtonGroup;
	}

	private JPanel getJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null); // ���谴ť����
		int width = 80, height = 80, i = 0;
		getBaseButton(); // ��û�����������ť
		baseButton.setBounds(5 + i++ * 85, 0, width, height);
		panel.add(baseButton);
		getStockButton(); // ��òɹ�������ť
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
			departmentButton.setActionCommand("���Ź���");
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
			personButton.setActionCommand("��Ա����");
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
			outWarehouseButton.setActionCommand("�������");
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
			inWarehouseButton.setActionCommand("������");
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
			stockButton.setActionCommand("�ɹ���������");
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
			baseButton.setActionCommand("������������"); // ��ʶbutton��ÿһ����ťActionCommand��ͬ
			baseButton.setIcon(new ImageIcon(getClass().getResource("../icon/jbda.png")));// Ŀ��������ӦĿ¼���ҵ�ͼƬ��button����ͼ��
			ImageIcon icon = new ImageIcon(getClass().getResource("../icon/jbda.png"));
			baseButton.setRolloverIcon(icon); // û��ѡ��֮ǰ������ͼ����ʱ����ʾ
			baseButton.setSelectedIcon(icon); // ѡ��֮�����ʾ
			baseButton.setSelected(true);
			baseButton.addActionListener(new ToolsButtonActionAdapter()); // ��Ӽ�����
		}
		return baseButton;

	}

	// �ڲ���ķ�����������ⲿ���еĳ�Ա����ʵ�ֲ���
	// ����ʵ�ֶ���������
	class ToolsButtonActionAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			workspacePanel.removeAll(); // ���²��������������
			if(e.getSource()==baseButton) {
				workspacePanel.setBorder(createTitleBorder("������������"));
				BasePanel basePanel = new BasePanel();
				workspacePanel.add(basePanel,BorderLayout.CENTER);
			}else if(e.getSource() == stockButton) {
				workspacePanel.setBorder(createTitleBorder("�ɹ�����"));
				StockPanel stockPanel = new StockPanel();
				workspacePanel.add(stockPanel);
			}else if(e.getSource() == inWarehouseButton) {
				workspacePanel.setBorder(createTitleBorder("������"));
				InWarehousePanel inWarehousePanel = new InWarehousePanel();
				workspacePanel.add(inWarehousePanel);
			}else if(e.getSource() == outWarehouseButton) {
				workspacePanel.setBorder(createTitleBorder("�������"));
				OutWarehousePanel outWarehousePanel = new OutWarehousePanel();
				workspacePanel.add(outWarehousePanel);
			}else if(e.getSource() == personButton) {
				workspacePanel.setBorder(createTitleBorder("��Ա����"));
				PersonPanel personPanel = new PersonPanel();
				workspacePanel.add(personPanel);
			}else if(e.getSource() == departmentButton) {
				workspacePanel.setBorder(createTitleBorder("���Ź���"));
				DepartmentPanel departmentPanel = new DepartmentPanel();
				workspacePanel.add(departmentPanel);
			}
			workspacePanel.updateUI();//���������ʾ

		}
	}

	public Border createTitleBorder(String title) {
		return new TitledBorder(null,title,TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.TOP,new Font("΢���ź�",Font.BOLD,12),null);
	}

}
