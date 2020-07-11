package com;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.common.util.TokenManager;
import com.dao.BasicMessageDao;
import com.dao.ContactDao;
import com.dao.DepartmentDao;
import com.dao.DutyDao;
import com.dao.GoodsDao;
import com.dao.InWarehouseDao;
import com.dao.OutWarehouseDao;
import com.dao.PurchaseDao;
import com.dao.SellerDao;
import com.dao.WarehouseDao;
import com.daoimpl.BasicMessageDaoImpl;
import com.daoimpl.ContactDaoImpl;
import com.daoimpl.DepartmentDaoImpl;
import com.daoimpl.DutyDaoImpl;
import com.daoimpl.GoodsDaoImpl;
import com.daoimpl.InWarehouseDaoImpl;
import com.daoimpl.OutWarehouseDaoImpl;
import com.daoimpl.PurchaseDaoImpl;
import com.daoimpl.SellerDaoImpl;
import com.daoimpl.WarehouseDaoImpl;
import com.supplier.dao.SupplierDao;
import com.supplier.dao.SupplierDaoImpl;
import com.user.bean.User;
import com.user.dao.UserDao;
import com.user.dao.UserDaoImpl;

public class Server extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private int width=450,height=300;
	
	private JPanel contentPane;
	private JTable table;
	public static JLabel lblNotice;
	public static LocalTableModel model = new LocalTableModel();      //方便直接添加相应内容（public）
	private static List<String> clientList = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server(); //创建窗体界面
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//监听的常见参数
		Properties prop = new Properties();//属性文件
		InputStream in = Class.class.getResourceAsStream("/com/server.properties");//把文件作为输入流
		try {
			prop.load(in);//从输入流装载属性
		} catch (IOException e) {
			e.printStackTrace();
		}
		//根据属性名调用属性值
		int authServerPort = Integer.parseInt(prop.getProperty("authServerPort"));
		String serverAddress = prop.getProperty("serverAddress");
		int appServerPort = Integer.parseInt(prop.getProperty("appServerPort"));
		
		
		//RMI注册代码
		Registry registry = null;		
		try {
			registry = LocateRegistry.createRegistry(appServerPort);
			
			BasicMessageDao basicMessageDao = new BasicMessageDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/basicMessageDao", basicMessageDao);
			ContactDao contactDao = new ContactDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/contactDao", contactDao);
			DepartmentDao departmentDao = new DepartmentDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/departmentDao", departmentDao);
			DutyDao dutyDao = new DutyDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/dutyDao", dutyDao);
			GoodsDao goodsDao = new GoodsDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/goodsDao", goodsDao);
			InWarehouseDao inWarehouseDao = new InWarehouseDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/inWarehouseDao", inWarehouseDao);
			OutWarehouseDao outWarehouseDao = new OutWarehouseDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/outWarehouseDao", outWarehouseDao);
			PurchaseDao purchaseDao = new PurchaseDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/purchaseDao", purchaseDao);
			SellerDao sellerDao = new SellerDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/sellerDao", sellerDao);
			SupplierDao supplierDao = new SupplierDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/supplierDao", supplierDao);
			WarehouseDao warehouseDao = new WarehouseDaoImpl();
			Naming.rebind("//"+serverAddress+":"+appServerPort+"/warehouseDao", warehouseDao);
		
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
		ServerSocket server;
		UserDao userDao = new UserDaoImpl();//实现类与接口之间的关系
		
		try {
			server = new ServerSocket(authServerPort);
			System.out.println("服务器启动：" + server.getInetAddress().getHostAddress() + ":" + server.getLocalPort());
			while (true) {
				Socket socket = server.accept();//等待客户端连接
				ObjectInputStream ois;
				PrintWriter pw;
				try {
					ois = new ObjectInputStream(socket.getInputStream());
					pw = new PrintWriter(socket.getOutputStream());
					User tempUser = (User) ois.readObject();//客户端传递的认证对象

					User user = userDao.getUser(tempUser.getUserName(), tempUser.getPassWord());
					if (user != null) {
						String token = TokenManager.instance.getToken(user);//令牌
						clientList.add(token);
						System.out.println("发给 " + token);
						pw.println(token);
						pw.flush();
						System.out.println(user.getUserName() + "连接：" + socket.getInetAddress().getHostAddress() + ":"
								+ socket.getPort());
					} else {
						pw.println("failed");
						pw.flush();//刷新
					}
					ois.close();
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("服务器监控");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel fixPanel = new JPanel();
		contentPane.add(fixPanel, BorderLayout.NORTH);
		fixPanel.setLayout(new BorderLayout(0, 0));
		fixPanel.setSize(800, 30);
		
		lblNotice = new JLabel("当前在线人数：0；当前内存占用："+Runtime.getRuntime().totalMemory()/(8*1024*1024)+"MB");
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);
		fixPanel.add(lblNotice, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(244);
		scrollPane.setViewportView(table);
	}
	
	//定义内部类（table需要修改，所以model必须是动态的）
	public static class LocalTableModel  extends DefaultTableModel{
		Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
				java.lang.Object.class,java.lang.Object.class };
		boolean []  canEdit = new boolean[] {false,false,false,false,false};
		public LocalTableModel() {
			super(new Object[][] {}, new String[] {"序号","用户","令牌","持续时间","剩余时间"});   //调用父类构造方法，把字符串标题引入
		}
		public Class getColumnClass(int columnIndex) {  //获取每一列数据类型
			return types[columnIndex];
		}
		public boolean isCellEditable(int rowIndex, int columnIndex) {   //每个单元格是否可以编辑行号和列号
			return canEdit[columnIndex];
		}
	}

}
