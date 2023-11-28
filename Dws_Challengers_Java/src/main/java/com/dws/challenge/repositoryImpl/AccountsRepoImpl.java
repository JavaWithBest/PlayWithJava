package com.dws.challenge.repositoryImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.dws.challenge.domain.Account;
import com.dws.challenge.globleExceptionHandler.DuplicateAccountIdException;
import com.dws.challenge.repo.AccountRepo;

@Repository
public class AccountsRepoImpl implements AccountRepo {

	
	private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public void createAccount(Account account) throws DuplicateAccountIdException {
        Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
        if (previousAccount != null) {
            throw new DuplicateAccountIdException(
                    "Account id " + account.getAccountId() + " already exists!");
        }
    }

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public void clearAccounts() {
        accounts.clear();
    }

	@Override
	public Account updateAccount(Account account) {
		Account previousAccount = accounts.get(account.getAccountId());
        if (previousAccount == null) {
            throw new DuplicateAccountIdException(
                    "Account id " + account.getAccountId() + " doesnot exists!");
        }
        accounts.put(account.getAccountId(), account);
        return previousAccount;
	}



}
