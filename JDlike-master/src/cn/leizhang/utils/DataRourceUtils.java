package cn.leizhang.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataRourceUtils {
	public static ComboPooledDataSource cpds=new ComboPooledDataSource();
	public static Connection getConnection() throws SQLException
	{
		
		return cpds.getConnection();
		
	}
	public static DataSource getDatasource()
	{
		return cpds;
	}

}
