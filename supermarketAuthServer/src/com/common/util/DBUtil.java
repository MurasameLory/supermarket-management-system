package com.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//��д����
	public static String user = "root";
	public static String password = "0323";
	public static String driverClassName = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/andios?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
	
	static {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("�������ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

}
