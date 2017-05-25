package cn.leizhang.service;

import java.sql.SQLException;

import cn.leizhang.dao.UserDao;
import cn.leizhang.domain.User;

public class UserService {

	public User check(String username, String password) throws SQLException {
		
		return UserDao.userlogin(username,password);
	}

	public boolean isUserExist(String username) throws SQLException {
		
		return UserDao.isUserExist(username);
	}

	public void saveUser(String username, String password) throws SQLException {
		
		UserDao.savUser(username,password);
		
	}

	public void updateUser(String id, String truename, String email,
			String address, String phone) throws SQLException {
		UserDao.updateUser(id,truename,email,address,phone);
		
	}
	public User getUserById(String id) throws SQLException
	{
		
		return UserDao.getUserById(id);
		
	}
		
}
