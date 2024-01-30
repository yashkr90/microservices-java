package com.example.ibm.service;

import java.util.List;


//import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Component;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.exception.MinimumBalanceException;
import com.example.ibm.model.Account;
import com.example.ibm.repo.AccountRepo;

@Component(value = "accountService")
public class AccountServiceImpl implements AccountService{

	
	private AccountRepo accountRepository;

	public AccountServiceImpl(AccountRepo accountRepository) {
//		super();
		this.accountRepository = accountRepository;
	}
	
	
	
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.createAccount(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.getAllAccounts();
	}
	@Override
	public Account getAccountByaccountNumber(String accountNumber)throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.getAccountByaccountNumber(accountNumber);
	}
	@Override
	public Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.updateAccountByAccountNumber(accountNumber, account);
	}
	@Override
	public void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		accountRepository.deleteAccountByAccountNumber(accountNumber);
		
	}



	@Override
	public Account depositAmount(String accountNumber, Double amount) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.depositAmount(accountNumber, amount);
	}



	@Override
	public Account withdrawAmount(String accountNumber, Double amount)
			throws AccountNotFoundException, MinimumBalanceException {
		// TODO Auto-generated method stub
		return accountRepository.withdrawAmount(accountNumber, amount);
	}

	
	
	
}
