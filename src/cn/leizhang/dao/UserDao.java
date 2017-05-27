package cn.leizhang.dao;



import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;



import cn.leizhang.utils.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.leizhang.domain.User;

public class UserDao {

	public static User userlogin(String username, String password) throws SQLException {
			
		String sql = "SELECT * FROM users where username=? and password_=?";
		QueryRunner runner = new QueryRunner(DataRourceUtils.getDatasource());

		
		User user=(User)runner.query(sql, new BeanHandler(User.class),username,password);
		
		return user;

				
	}

	public static boolean isUserExist(String username) throws SQLException {
		
		String sql = "SELECT username FROM users where username=?";
		QueryRunner runner = new QueryRunner(DataRourceUtils.getDatasource());

		
		List<User> list=(List<User>)runner.query(sql,new BeanListHandler(User.class),username);
		for(User list1: list)
		{
			if(list1.getUsername().equals(username))
			{
			return true;
			}
		}
		return false;
		
		
		
		
		
	}

	public static void savUser(String username, String password) throws SQLException {
		String sql="insert into users values(null,?,?,null,null,null,null,null)";
		QueryRunner runner = new QueryRunner(DataRourceUtils.getDatasource());
		runner.update(sql,username,password);
	}

	public static void updateUser(String id, String truename, String email,
			String address, String phone) throws SQLException {
		String sql="update users set truename=?,email=?,address_=?,phone=? where userid=?  ";
		QueryRunner runner = new QueryRunner(DataRourceUtils.getDatasource());
		runner.update(sql,truename,email,address,phone,id);
		
	}

	public static User getUserById(String id) throws SQLException {
		String sql="select * from users where userid=?";
		QueryRunner runner=new QueryRunner(DataRourceUtils.getDatasource());
		    User us=(User)runner.query(sql,new BeanHandler(User.class), id);
		return us;
	}

	

}
