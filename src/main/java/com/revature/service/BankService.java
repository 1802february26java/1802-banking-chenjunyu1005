package com.revature.service;

import java.util.Set;

import com.revature.exception.IlleaglTransactionException;
import com.revature.model.Customer;
import com.revature.repository.BankCustomerDao;

public class BankService {

	BankCustomerDao bankCustomerDao =new BankCustomerDao();
	public double viewBalance(String firstName,String lastName) {
		// TODO Auto-generated method stub
		return bankCustomerDao.viewBalance(firstName, lastName);
	}


	public boolean withdrawMoney(String firstName,String lastName,double withdrawAmount) {
		// TODO Auto-generated method stub
		return bankCustomerDao.withdrawMoney(firstName,lastName,withdrawAmount);
	}


	public boolean depositMoney(String firstName,String lastName,double depositAmount) throws IlleaglTransactionException{
		// TODO Auto-generated method stub
		if(depositAmount<0){
			throw new IlleaglTransactionException("Must Be A Positive Amount");
		}
		
		return bankCustomerDao.depositMoney(firstName,lastName,depositAmount);
	}


	public boolean registerNewUser(Customer customer) {
		// TODO Auto-generated method stub
	
		return bankCustomerDao.registerNewUser(customer);
	}

/*
	public Set<Customer> viewAllTranscation() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
