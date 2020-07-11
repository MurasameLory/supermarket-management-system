package com.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//���������ݿ������
public class DataConnection {
	private Connection conn;
	private PreparedStatement pstm;
	//��д����
	private String user = "root";
	private String password = "0323";
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/andios?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC" + 
			"";

	//��������
	public DataConnection() {
		try {
			Class.forName(driverClassName); 
		} catch (ClassNotFoundException e) {
			System.out.println("�������ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	//��������
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�����ʧ�ܣ�");
			conn = null;
			e.printStackTrace();
		}
		return conn;
	}

	public void doPstm(String sql, Object[] params) { //��װ�Ķ�������
		if (sql != null && !(sql.trim().equals(""))) {    //�ж��ַ����Ƿ�Ϊ�պ��ַ�����ֵ�Ƿ�Ϊ�մ�
			if (params == null) {
				params = new Object[0];
				getConnection();
				if (conn != null) {
					try {
						System.out.println(sql);
						pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,  //������������
								ResultSet.CONCUR_READ_ONLY);
						for (int i = 0; i < params.length; i++) {
							pstm.setObject(i + 1, params[i]);
						}
						pstm.execute();
					} catch (SQLException e) {
						System.out.println("doPstm()��������");
						e.printStackTrace();
					}
				}
			}
		}
	}

	public ResultSet getResultSet() throws SQLException {
		return pstm.getResultSet();		//����һ�������
	}

	public int getCount() throws SQLException {
		return pstm.getUpdateCount();
	}
	//�ȹ���䣬�������
	public void closed() {
		try {
			if (pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			System.out.println("�ر�pstm����ʧ�ܣ�");
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("�ر�conn����ʧ�ܣ�");
			e.printStackTrace();
		}
	}

}
