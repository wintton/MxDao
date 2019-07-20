package com.mx.mxdao;

import java.sql.Connection;

/**
 * 梦辛工作室  2019-05-31  1.0.0
 * @author 灵
 *
 */
public class MxDbInfo {
	// 驱动程序名
	protected static String driver = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名
	protected static String sqlurl = "jdbc:mysql://127.0.0.1:3306/mysql";
	// MySQL配置时的用户名
	protected static String user = "root";
	// MySQL配置时的密码
	protected static String password = "1234";
	protected static Connection conn = null;
	protected static String supportTypeStr = "java.lang.String,java.lang.Character,java.lang.Byte,java.lang.Double,java.lang.Integer,java.lang.Long,java.lang.Short,java.lang.Float,int,char,float,long";
	public static String getDriver() {
		return driver;
	}
	
	public static void setDriver(String driver) {
		MxDbInfo.driver = driver;
	}
	public static String getSqlurl() {
		return sqlurl;
	}
	public static void setSqlurl(String sqlurl) {
		MxDbInfo.sqlurl = sqlurl;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		MxDbInfo.user = user;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		MxDbInfo.password = password;
	}
	public static Connection getConn() {
		return conn;
	}
	public static void setConn(Connection conn) {
		MxDbInfo.conn = conn;
	}
	public static String getSupportTypeStr() {
		return supportTypeStr;
	}
	public static void setSupportTypeStr(String supportTypeStr) {
		MxDbInfo.supportTypeStr = supportTypeStr;
	}
 
	
}
