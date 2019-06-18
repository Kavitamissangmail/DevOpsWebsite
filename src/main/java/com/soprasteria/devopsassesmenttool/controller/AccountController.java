package com.soprasteria.devopsassesmenttool.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.sevice.AccountService;
import com.soprasteria.devopsassesmenttool.util.CustomErrorType;

@RestController
@RequestMapping("/devops")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts() {
		return accountService.findAll();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAccount(@PathVariable Long id) {

		Account account = accountService.findOne(id);

		if (account == null) {
			return new ResponseEntity(new CustomErrorType(" Account with id " + id + "  is not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> createAccount(@RequestBody Account account) throws URISyntaxException {

		if (accountService.isUserExist(account)) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create. Account with name " + account.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		try {
			Account result = accountService.save(account);
			return ResponseEntity.created(new URI("/devops/account/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create. Account with id  " + account.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "account", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) throws URISyntaxException {
		if (account.getId() == null) {

			return new ResponseEntity(
					new CustomErrorType("Unable to upate. Account with id " + account.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}

		try {
			Account result = accountService.update(account);

			return ResponseEntity.created(new URI("/devops/account/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity(
					new CustomErrorType("Unable to upate. Account with id " + account.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

		Account account = accountService.findOne(id);

		if (account == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		accountService.delete(id);

		return ResponseEntity.ok().build();
	}
}
