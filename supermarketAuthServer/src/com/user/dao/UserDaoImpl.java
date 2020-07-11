package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.util.DataConnection;
import com.user.bean.User;
//用来存放各种实现接口
public class UserDaoImpl implements UserDao {
	DataConnection connection = new DataConnection();
	Connection conn = null; 
	//将return null修改为实现登录操作的代码
	@Override
	public User getUser(String userName, String passWord) {
		User user =null;
		conn =connection.getConnection();
		try {
			String sql = "select *from tb_users where username=? and password=?";    //*表示拿出用户的所有字段 ,后面是查找指定用户名指定口令的记录
			PreparedStatement pstm =conn.prepareStatement(sql);
			pstm.setString(1,userName);
			pstm.setString(2,passWord);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassWord(rs.getString(3));
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
