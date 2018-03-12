package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Utility class to obtain a connection object
 * 
 *
 */
public class ConnectionUtil {
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	//Whoever calls this method handle the Exception
	private static String url=null;
	private static String username=null;
	private static String password=null;
	private static  Connection con;
	
	static{
		try{
		 url="jdbc:oracle:thin:@myinstancedbs.cuii5qzoodlu.us-east-1.rds.amazonaws.com:1521:ORCL";
//		 username="CELEBRITY_DB";
//		 password="p4sswOrd";
		 username = "bankcustomer";
		 password = "pass";
		 con =DriverManager.getConnection(url, username, password);
		 logger.info("DataBase Connection successfully");
		}catch(SQLException s){
			logger.error("Something wrong"+s);

		}
	}
	public static Connection getConnection(){
	 //locally jdbc:oracle:thin:@localhost:1521:xe
	
	 return con;
	}
	
	
}
