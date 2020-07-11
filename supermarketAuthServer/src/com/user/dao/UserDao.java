package com.user.dao;

import com.user.bean.User;

public interface UserDao {
	public User getUser(String userName, String passWord);

}
