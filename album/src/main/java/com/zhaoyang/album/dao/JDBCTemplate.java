package com.zhaoyang.album.dao;

import java.sql.Connection;
import java.sql.DriverManager;

abstract public class JDBCTemplate<T> {
	private String driverClass="com.mysql.jdbc.Driver";
	private String jdbcURL="jdbc:mysql://127.0.0.1:3306/webalbum";
	private String user="root";
	private String pwd="xbacd";
	abstract public T execute() throws Exception;
	protected Connection getConnection()throws Exception {
			Class.forName(driverClass);
			Connection conn=DriverManager.getConnection(jdbcURL, user, pwd);
			return conn;		
	}
}
