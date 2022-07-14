package com.tweetapp.DButility;

import java.sql.*;
import java.util.ResourceBundle;
public class DBUtility {
	
	public static Connection getConnection() 
	{
		ResourceBundle rb=ResourceBundle.getBundle("db");
		String driver=rb.getString("driver");
		String url=rb.getString("url");
		String username=rb.getString("username");
		String password=rb.getString("password");
		Connection con=null;
		try
		{
		Class.forName(driver);
		 con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
		
	}

}