package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.IlleaglAccountBalanceException;
import com.revature.exception.NotEnoughMoneyException;
import com.revature.model.Customer;
import com.revature.service.BankService;

public class Controller {

	private static Logger logger = Logger.getLogger(Controller.class);
	List<String> transactionList = new ArrayList<>();
	BankService service = new BankService();
	Scanner sc = new Scanner(System.in);

	public void loginIn() {
		logger.info("Enter you first name");
		String firstName = sc.next();
		logger.info("Enter you last name");
		String lastName = sc.next();
		logger.info("Login or LogOut");
		String loginOut = sc.next().toLowerCase();

		if ("login".equals(loginOut)) {
			logger.info("login succefully");
			optionMenu(firstName, lastName);
		}
	}

	public void optionMenu(String firstName, String lastName) {
		// TODO Auto-generated method stub
		logger.info(
				"\n\tWhat operation you want to perform:\n\tViewbalance\n\tDepositOrWithdraw\n\tRegister\n\tViewAllTransaction\n\tLogOut");
		while (true) {

			String line = sc.nextLine();
			line = line.toLowerCase();
			switch (line) {
			/*
			 * case "login": loginToDatabase(); break;
			 */
			case "viewbalance":
				viewCurrentBalance(firstName, lastName);
				break;
			case "depositorwithdraw":
				confirmTypeOfTransaction(firstName, lastName);
				break;

			case "register":
				registerNewAccount();
				break;

			case "viewall":
				viewAllTrancation();
				break;
			case "logout":
				logger.info("You are logging out ");
				System.exit(0);
				break;
			default:
				break;
			}

		}

	}

	private void confirmTypeOfTransaction(String firstName, String lastName) {
		logger.info("Enter what type of transcation you want to perform\n" + "Withdraw\tDeposit");
		String line = sc.nextLine().toLowerCase();
		switch (line) {
		case "withdraw":
			try {
				withDrawMoney(firstName, lastName);
			} catch (NotEnoughMoneyException e) {
				e.printStackTrace();
			}
			break;
		case "deposit":
			depositMoney(firstName, lastName);
			break;
		}

	}

	private void depositMoney(String firstName, String lastName) {
		// TODO Auto-generated method stub
		double depositeAmount = sc.nextDouble();
		if (service.depositMoney(firstName, lastName, depositeAmount)) {
			transactionList.add("Deposit  Amount :" + depositeAmount);
			logger.info("deposit successfully");
		} else {
			logger.info("transcation fails");
		}
	}

	private void withDrawMoney(String firstName, String lastName) throws NotEnoughMoneyException {
		
		double withdrawAmount = sc.nextDouble();
		if (withdrawAmount > service.viewBalance(firstName, lastName)) {
			throw new NotEnoughMoneyException("Not Enough Money In Your Account");
		}
		if (service.withdrawMoney(firstName, lastName, withdrawAmount)) {
			transactionList.add("Withdraw  Amount :" + withdrawAmount);
			logger.info("withdraw successfully");
		} else {
			logger.info("transcation fails");
		}

	}

	private void viewCurrentBalance(String firstName, String lastName) {
		logger.info("Your Current Balance is : " + service.viewBalance(firstName, lastName));
	}

	private void registerNewAccount() {
		/*logger.info("Enter Your ID");
		long id = sc.nextLong();*/
		logger.info("Enter Your firstName");
		String firstname = sc.next();
		logger.info("Enter Your LastName");
		String lastname = sc.next();
		logger.info("GENDER");
		String gender = sc.next().toUpperCase();
		logger.info("Date Of Birth");
		String birth = sc.next();
		logger.info("How much do you want to put in your account");
		double balanceNotCheck = sc.nextDouble();
		double balance = checkValidBalance(balanceNotCheck);
		Customer customer = new Customer(firstname, lastname, gender, birth, balance);
		service.registerNewUser(customer);
	}

	private static double checkValidBalance(double balanceNotCheck) {
		if (balanceNotCheck < 0) {
			try {
				throw new IlleaglAccountBalanceException("balance can't less than 0");
			} catch (IlleaglAccountBalanceException e) {
				
				e.printStackTrace();
			}
		}
		return balanceNotCheck;
	}

	private void viewAllTrancation() {
	
		for (String trans : transactionList) {
			logger.info(trans);
		}

	}

}
