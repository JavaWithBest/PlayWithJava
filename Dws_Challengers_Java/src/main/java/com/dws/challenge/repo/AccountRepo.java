package com.dws.challenge.repo;

import com.dws.challenge.domain.Account;
import com.dws.challenge.globleExceptionHandler.DuplicateAccountIdException;

public interface AccountRepo {
	
	
	 void createAccount(Account account) throws DuplicateAccountIdException;

	  Account getAccount(String accountId);

	  void clearAccounts();
	  
	  Account updateAccount(Account account);
}
