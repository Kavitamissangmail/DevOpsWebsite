package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.repository.AccountRepository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;

@Service
public class AccountService {
	private AccountRepository accountRepository;

	private static List<Account> accounts;

	@Autowired
	UserRepository userRepository;

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

	public Account update(Account accountRequest) {
		if (accountRequest.getId() != null && !accountRepository.exists(accountRequest.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}
		Account account = accountRepository.getOne(accountRequest.getId());
		accountRequest.setUser(account.getUser());

		return accountRepository.save(accountRequest);
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account findOne(Long id) {
		return accountRepository.findOne(id);
	}

	public void delete(Long id) {
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

	public Account findByUser(Long userId) {
		return accountRepository.findByUserUserId(userId);
	}

}
