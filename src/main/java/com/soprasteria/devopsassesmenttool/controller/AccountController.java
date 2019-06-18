package com.soprasteria.devopsassesmenttool.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.sevice.AccountService;
import com.soprasteria.devopsassesmenttool.sevice.UserService;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

@RestController
@RequestMapping("/devops")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	public AccountController() {
	}

	@RequestMapping(value = "accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts() {
		return accountService.findAll();
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getAccount(@PathVariable Long id) {
		return accountService.findOne(id);
	}

	@RequestMapping(value = "/user/{userId}/account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createAccount(@PathVariable Long userId, @RequestBody Account account) throws URISyntaxException {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User with user id " + userId + " is does not exist.");
		}
		return accountService.save(account);
	}

	@RequestMapping(value = "account", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(@RequestBody Account account) throws URISyntaxException {
		if (account.getId() == null) {
			throw new ResourceNotFoundException("Unable to upate. Account with id " + account.getId() + " not found.");
		}
		return accountService.update(account);
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAccount(@PathVariable Long id) {

		Account account = accountService.findOne(id);

		if (account == null) {
			throw new ResourceNotFoundException("Unable to delete. User with id " + id + " not found.");
		}
		accountService.delete(id);
	}

	@RequestMapping(value = "/user/{userId}/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getAccountByUser(@PathVariable Long userId) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User with user id " + userId + " is does not exist.");
		}
		return accountService.findByUser(userId);
	}

}
