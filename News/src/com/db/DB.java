package com.db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private static String  driver="com.mysql.jdbc.Driver";//数据库驱动
	private static String url="jdbc:mysql://localhost:3306/newsdb?useUnicode=true&characterEncoding=UTF-8";//数据库连接的url地址
	private static String username="root";//数据库用户名
	private static String password="12345";//登陆密码
	
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
			
		}catch(Exception e)
		  {e.printStackTrace();}
		
		
		return conn;
	}
	
}
