package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.repository.AccountRepository;

@Service
public class AccountService {
	private AccountRepository accountRepository;

	private static List<Account> accounts;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account save(Account account) {
		if (account.getId() != null && accountRepository.exists(account.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return accountRepository.save(account);
	}

	public Account update(Account account) {
		if (account.getId() != null && !accountRepository.exists(account.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return accountRepository.save(account);
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account findOne(Integer id) {
		return accountRepository.findOne(id);
	}

	public void delete(Integer id) {
		accountRepository.delete(id);
	}

	public boolean isUserExist(Account account) {
		return findByName(account.getName()) != null;
	}

	public Account findByName(String name) {
		accounts = findAll();
		if (accounts != null) {
			for (Account account : accounts) {
				if (account.getName().equalsIgnoreCase(name)) {
					return account;
				}
			}
		}

		return null;

	}
}
