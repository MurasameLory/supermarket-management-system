package com.user.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.user.bean.User;
import com.common.util.Session;
import com.main.view.MainFrame;

public class LoginFrame extends JFrame {
	
	private BackgroundPanel backgroundPanel;
	private int width = 535;
	private int height = 410;
	
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblNotice;
	
	private int x,y;
	private boolean isDraging = false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/icon/bg.png")));
		
		setResizable(false);
		setUndecorated(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setBounds((screenSize.width-width)/2,(screenSize.height-height)/2,535,439);

		backgroundPanel = getLoginPanel();
		setContentPane(backgroundPanel);
	}
	
	private BackgroundPanel getLoginPanel() {
		BackgroundPanel backgroundPanel = new BackgroundPanel();
		backgroundPanel.setImage(getToolkit().getImage(getClass().getResource("/com/icon/1.png")));
		//backgroundPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("用户名");
		lblUsername.setFont(new Font("微软雅黑",0 ,20));
		lblUsername.setForeground(new Color(252, 108, 3));
		lblUsername.setBounds(93,240,73,35);
		backgroundPanel.add(lblUsername);
		txtUsername = new JTextField();
		txtUsername.setBounds(166, 242, 243, 34);
		backgroundPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("密码");
		lblPassword.setFont(new Font("微软雅黑", 0 ,20));
		lblPassword.setForeground(new Color(252, 108, 3));
		lblPassword.setBounds(93,274,73,35);
		backgroundPanel.add(lblPassword);
		txtPassword = new JPasswordField();
		txtPassword.setBounds(166, 277, 243, 34);
		backgroundPanel.add(txtPassword);
		
		lblNotice = new JLabel("请输入账号和密码");
		lblNotice.setFont(new Font("微软雅黑", 0 ,20));
		lblNotice.setForeground(new Color(252, 108, 3));
		lblNotice.setBounds(166, 310, 243, 34);
		backgroundPanel.add(lblNotice);	
		
		JButton btnClose = new JButton("");
		ImageIcon closeIcon = new ImageIcon(getClass().getResource("/com/icon/close.gif"));
		btnClose.setIcon(closeIcon);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorder(null);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}			
		});
		btnClose.setBounds(512,11,closeIcon.getIconWidth(),closeIcon.getIconHeight());
		backgroundPanel.add(btnClose);
		
		JButton btnMinimize = new JButton("");
		ImageIcon minimizeIcon = new ImageIcon(getClass().getResource("/com/icon/min.gif"));
		btnMinimize.setIcon(minimizeIcon);
		btnMinimize.setContentAreaFilled(false);
		btnMinimize.setBorder(null);
		btnMinimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setExtendedState(JFrame.ICONIFIED);				
			}			
		});
		btnMinimize.setBounds(485,11,minimizeIcon.getIconWidth(),minimizeIcon.getIconHeight());
		backgroundPanel.add(btnMinimize);
		
		JButton btnLogin = new JButton();
		ImageIcon loginIcon = new ImageIcon(getClass().getResource("/com/icon/login.png"));
		btnLogin.setIcon(loginIcon);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorder(null);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User user = new User();
				user.setUserName(txtUsername.getText());
				user.setPassWord(String.valueOf(txtPassword.getPassword()));
				System.out.println(user.getUserName()+" "+user.getPassWord());
				try {
					Socket socket = new Socket("127.0.0.1", 12345);
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//输出流构建输出流对象
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					oos.writeObject(user);//把user对象写入网络输出流上
					String message = br.readLine();//来自网络上的输入流
					if("failed".equals(message)) {
						lblNotice.setText("用户不存在或者密码不正确");
						return;//返回主界面
					}else {
						Session.setToken(message);
						//保存令牌信息，转到主界面
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
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dispose();//将窗体弃用
			}			
		});
//				if (username.equals("liumengyu")&&password.equals("123456")){
//					System.out.println("登陆成功");	
//					JOptionPane.showMessageDialog(null, "登录成功", "友情提示", JOptionPane.INFORMATION_MESSAGE);
//					setVisible(false);
//					MainFrame m=new MainFrame();
//					m.setVisible(true);
//					
//				}else if(username.equals("liumengyu")){
//					System.out.println("登陆失败，密码错误！！！");
//					JOptionPane.showMessageDialog(null, "登陆失败，密码错误！！！", "友情提示", JOptionPane.ERROR_MESSAGE);
//					
//				}else{
//					System.out.println("用户名错误！！！");
//					 JOptionPane.showMessageDialog(null, "用户名错误！！！", "友情提示", JOptionPane.ERROR_MESSAGE);  
//				}
//			}
//		});
		btnLogin.setBounds(225,358,loginIcon.getIconWidth(),loginIcon.getIconHeight());
		backgroundPanel.add(btnLogin);
		
		
		backgroundPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				x = e.getX(); 
				y = e.getY();
			}
			
			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}
		});
		
		backgroundPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if(isDraging) {
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left+e.getX()-x,top+e.getY()-y);
				}
			}
		});
		
		return backgroundPanel;
	}

	class BackgroundPanel extends JPanel {
		private Image image;
		public BackgroundPanel() {
			setOpaque(false); //设置控件不透明
			setLayout(null); //使用绝对定位布局控件
		}
		
		//设置背景图片对象的方法
		public void setImage(Image image) {
			this.image = image;
		}
		
		//画出背景
		protected void paintComponent(Graphics g) {
			if(image!=null){
				g.drawImage(image , 0 , 0, this);
			}
			super.paintComponent(g);
		}
	}

}
