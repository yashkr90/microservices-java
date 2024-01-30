package com.example.ibm.repo;

import java.util.List;


//import javax.security.auth.login.AccountNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.exception.MinimumBalanceException;
import com.example.ibm.model.Account;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
//import jakarta.websocket.Session;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component(value="accountRepository")
@EnableTransactionManagement

public class AccountRepoImpl implements AccountRepo {
	
	private final SessionFactory sessionFactory;

//	public  AccountRepoImpl(SessionFactory sessionFactory) {
//		super();
//		this.sessionFactory = sessionFactory;
//	}
	
	@Transactional
	@Override
	public Account createAccount(Account account) { // TODO
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.persist(account);
		session.getTransaction().commit();
		return account;
	}

	@Override
	public List<Account> getAllAccounts() { // TODO Auto-generated
		Session session = sessionFactory.openSession();
		TypedQuery<Account> query = session.createQuery("FROM Account A", Account.class);
		return query.getResultList();
	}
	
	@Override
	public Account getAccountByaccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Account account=session.get(Account.class, accountNumber);
		if(account==null)
		{
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		//session.close();
		return account;
	}

	@Override
	public Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Account tempAccount= getAccountByaccountNumber(accountNumber);
		
		if(tempAccount==null)
		{
			throw new AccountNotFoundException("account with " + accountNumber + " not found");
		}
		
		Session session = sessionFactory.openSession();
		
		tempAccount.setAccountHolderAddress(account.getAccountHolderAddress());
		tempAccount.setAccountHolderName(account.getAccountHolderName());
		tempAccount.setEmail(account.getEmail());
		
		session.beginTransaction();
		session.merge(tempAccount);
		session.getTransaction().commit();
		return tempAccount;
	}
	@Override
	@Transactional
	public void deleteAccountByAccountNumber(String accountNumber)
			throws AccountNotFoundException {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Account tempAccount = getAccountByaccountNumber(accountNumber);
		if (tempAccount == null) {
			throw new AccountNotFoundException(
					"account with " + accountNumber + " not found");
		}

		session.remove(session.merge(tempAccount));
		session.getTransaction().commit();

	}

	@Override
	public Account depositAmount(String accountNumber, Double amount) throws AccountNotFoundException{
		// TODO Auto-generated method stub
		Session session= sessionFactory.openSession();
		Account tempAccount= getAccountByaccountNumber(accountNumber);
		System.out.println(tempAccount);
		if (tempAccount==null) {
			throw new AccountNotFoundException(
					"account with " + accountNumber + " not found");
		}
		Double finalAmount= amount+ tempAccount.getAmount();
		tempAccount.setAmount(finalAmount);
		session.merge(tempAccount);
		session.beginTransaction().commit();
		session.close();
		
		return tempAccount;
	}

	@Override
	public Account withdrawAmount(String accountNumber, Double amount)
			throws AccountNotFoundException, MinimumBalanceException {
		
		Session session = sessionFactory.openSession();
		
		Account tempAccount= getAccountByaccountNumber(accountNumber);
		
		if (tempAccount==null)
		{
			throw new AccountNotFoundException(
					"account with " + accountNumber + " not found");
		}
		Double finalAmount = tempAccount.getAmount()-amount;
		if(finalAmount <500.00)
		{
			throw new MinimumBalanceException("balance less than 500 not allowed in account with" + accountNumber + "");
		}
		
		tempAccount.setAmount(tempAccount.getAmount()-amount);
		session.merge(tempAccount);
		session.beginTransaction().commit();
		session.close();
		
		return tempAccount;
	
	}

	

}
