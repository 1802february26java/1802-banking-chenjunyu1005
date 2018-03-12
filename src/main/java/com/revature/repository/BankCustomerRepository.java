package com.revature.repository;

import java.util.Set;

import com.revature.model.Customer;

public interface BankCustomerRepository {
	public double viewBalance(String firstName,String lastName);
	
	public boolean withdrawMoney(String firstName,String lastName,double withdrawAmount);
	
	public boolean depositMoney(String firstName,String lastName,double depositeAmount);
	
	public boolean registerNewUser(Customer customer);
	
	

}
