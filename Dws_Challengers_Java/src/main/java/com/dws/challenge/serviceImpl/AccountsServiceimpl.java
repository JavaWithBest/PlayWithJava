package com.dws.challenge.serviceImpl;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transaction;
import com.dws.challenge.globleExceptionHandler.InvalidTransactionException;
import com.dws.challenge.repo.AccountRepo;
import com.dws.challenge.repositoryImpl.AccountsRepoImpl;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountsServiceimpl {

	@Getter
	private final AccountRepo accountsRepository;

	@Autowired
	public AccountsServiceimpl(AccountsRepoImpl accountsRepository) {
		this.accountsRepository = accountsRepository;
	}

	public void createAccount(Account account) {
		this.accountsRepository.createAccount(account);
	}

	public Account getAccount(String accountId) {
		return this.accountsRepository.getAccount(accountId);
	}
	
	
	/* 
	 * 
	 * preventing dirty read and deadlock scenario
	 * synchronized and adding layer of @Transactional boundary 
	 *  
	 *  */

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public synchronized List<Account> transaction(Transaction transaction) {
		Account accountFrom = accountsRepository.getAccount(transaction.getFromAccountId());
		Account accountTo = accountsRepository.getAccount(transaction.getToAccountId());

		boolean isValid = validatedAccountDetails(transaction, accountFrom, accountTo);

		if (isValid == true)
	    accountFrom.setBalance(accountFrom.getBalance().subtract(transaction.getAmount()));
		accountTo.setBalance(accountTo.getBalance().add(transaction.getAmount()));
		accountsRepository.updateAccount(accountFrom);
		accountsRepository.updateAccount(accountTo);
		return new ArrayList<>(Arrays.asList(accountFrom, accountTo));

	}

	
	/*
	 *  
	 *  @Doc Go throug READMeCodeExplation
	 *  
	 *  APPENDIX 2: EXPLANATION OF CODE THE CODE, BEST PRACTICES IN DETAILS
	 *    
	 */
	private boolean validatedAccountDetails(Transaction transaction, Account accountFrom, Account accountTo) {
		boolean flag = true;
		if (accountFrom == null || accountTo == null) {
			flag = false;
			throw new InvalidTransactionException("Either From or To Account(s) dosen't exist!");
		}
		if (accountFrom.getBalance().compareTo(transaction.getAmount()) == -1) {
			flag = false;
			throw new InvalidTransactionException(
					"Account id " + transaction.getFromAccountId() + " doesnot have enough amount!");
		}
		return flag;
	}

}
