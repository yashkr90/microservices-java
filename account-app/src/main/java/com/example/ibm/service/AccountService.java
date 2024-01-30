package com.example.ibm.service;

import java.util.List;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.exception.MinimumBalanceException;

//import javax.security.auth.login.AccountNotFoundException;

import com.example.ibm.model.Account;

public interface AccountService {

	
	Account createAccount(Account account);
	
	List<Account> getAllAccounts();

	Account getAccountByaccountNumber(String accountNumber)throws AccountNotFoundException;
	
	Account updateAccountByAccountNumber(String accountNumber, Account account)
			throws AccountNotFoundException;
	
	Account depositAmount(String accountNumber, Double amount) throws AccountNotFoundException;
	
	Account withdrawAmount(String accountNumber, Double amount) throws AccountNotFoundException,MinimumBalanceException;
	
	void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;
}

