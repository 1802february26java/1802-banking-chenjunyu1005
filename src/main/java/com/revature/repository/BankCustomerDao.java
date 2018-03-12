package com.revature.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.controller.Controller;
import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class BankCustomerDao implements BankCustomerRepository {

	private static Connection conection = ConnectionUtil.getConnection();
	private static Logger logger = Logger.getLogger(BankCustomerDao.class);

	@Override
	public double viewBalance(String firstName,String lastName) {
		// TODO Auto-generated method stub
		try {

			String sql = "Select C_ACCOUNT_BALANCE from Customer where C_FIRST_NAME=? AND C_LAST_NAME=?";
			PreparedStatement statement = conection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			  ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getDouble("C_ACCOUNT_BALANCE");
			}
			
		} catch (SQLException e) {
			logger.error("Unable to check you AccountBalance At this Moment",e);

		}
		return 0;
	}

	@Override
	public boolean withdrawMoney(String firstName,String lastName,double withdrawAmount) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE CUSTOMER SET C_ACCOUNT_BALANCE=C_ACCOUNT_BALANCE-'" + withdrawAmount + "'"
					+ "where C_FIRST_NAME='"+firstName+"'AND C_LAST_NAME='"+lastName+"'";

			PreparedStatement statement = conection.prepareStatement(sql);
			if (statement.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("withdraw fails"+e);
		}
		return false;
	}

	@Override
	public boolean depositMoney(String firstName,String lastName,double depositeAmount) {
		try {
			String sql = "UPDATE CUSTOMER SET C_ACCOUNT_BALANCE=C_ACCOUNT_BALANCE+'" + depositeAmount + "'"
					+"where C_FIRST_NAME='"+firstName+"'AND C_LAST_NAME='"+lastName+"'";
			PreparedStatement statement = conection.prepareStatement(sql);
			if (statement.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {

			logger.error("deposit fails"+e);
		}

		return false;
	}

	@Override
	public boolean registerNewUser(Customer cutomer) {
		// TODO Auto-generated method stub
		
		try {
			SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format.
			java.util.Date myDate = format.parse( cutomer.getBirth());
			String sql = "INSERT INTO CUSTOMER VALUES(Null,?,?,?,?,?)";
			PreparedStatement statement = conection.prepareStatement(sql);
//			statement.setLong(1, cutomer.getId());
			statement.setString(1, cutomer.getFirstName());
			statement.setString(2, cutomer.getLastName());
			statement.setString(3, cutomer.getGender());
			java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() );
			statement.setDate(4, sqlDate);
			statement.setDouble(5, cutomer.getAccountBalance());
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				logger.info("New customer registered ");
				return true;
			}
		} catch (SQLException e) {
			logger.error("registration fails " + e);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public Set<Customer> viewAllTranscation() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
