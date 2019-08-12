/**
 * 
 */
package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.sevice.AccountlabelService;

/**
 * @author dbkumar
 *
 */

@RestController
@RequestMapping("/devops")
public class AccountLabelController {
	
	@Autowired
	AccountlabelService accountLabelService;



	@RequestMapping(value = "/getAllAccountLabel", method = RequestMethod.GET)
	public List<AccountLabel> getAllAccountLabel() {
		return accountLabelService.getAllAccountlabels();
	}

	@RequestMapping(value = "/accountLabel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AccountLabel createAccountLabel(@RequestBody AccountLabel accountLabel) {
		return accountLabelService.createCategory(accountLabel);
	}
	
	@RequestMapping(value = "/AccountLabel/{id}", method = RequestMethod.GET)
	public AccountLabel AccountLabelById(@PathVariable(value = "id") Long id) {
		return accountLabelService.getAccountlabelsById(id);
	}
	
	@RequestMapping(value = "/accountLabel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AccountLabel updateAnswer(@RequestBody AccountLabel accountLabel) {
		return accountLabelService.updateAccountLabelById(accountLabel);
	}

	
	@Transactional
	@RequestMapping(value = "/accountLabel/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAccountLabelById(@PathVariable(value = "Id") Long Id) {
		return accountLabelService.deleteAccountLabelById(Id);
	}
}
