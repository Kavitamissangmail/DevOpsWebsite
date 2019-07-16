/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.Category;
import com.soprasteria.devopsassesmenttool.repository.AccountLabelRespository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class AccountlabelService {
	

	private AccountLabelRespository accountlabelRepository;

	@Autowired
	public AccountlabelService(AccountLabelRespository accountlabelRepository) {
		this.accountlabelRepository = accountlabelRepository;
	}

	public List<AccountLabel> getAllAccountlabels() {
		return accountlabelRepository.findAll();
	}




	public AccountLabel createCategory(AccountLabel accountlabel) {
		return accountlabelRepository.save(accountlabel);

	}

	
	public AccountLabel getAccountlabelsById(Long id) {
		if (!accountlabelRepository.existsById(id)) {
			throw new ResourceNotFoundException("accountlabel with id " + id + " not found");
		}
		return accountlabelRepository.findById(id);

	}

	public AccountLabel updateAccountLabelById(AccountLabel accountLabelRequest) {
		if (!accountlabelRepository.existsById(accountLabelRequest.getId())) {
			throw new ResourceNotFoundException("AccountLabel with id " + accountLabelRequest.getId() + " not found");
		}
		AccountLabel accountLabel = accountlabelRepository.findById(accountLabelRequest.getId());

		accountLabel.setId(accountLabelRequest.getId());
		accountLabel.setAcccolname(accountLabelRequest.getAcccolname());
		accountLabel.setDropdown(accountLabelRequest.getDropdown());
		accountLabel.setQtype(accountLabelRequest.getQtype());
		accountLabel.setAccountlabel(accountLabelRequest.getAccountlabel());
	
		return accountlabelRepository.save(accountLabel);
	}
	
	
	public ResponseEntity<Object> deleteAccountLabelById(Long Id) {
		if (!accountlabelRepository.existsById(Id)) {
			throw new ResourceNotFoundException("AccountLabel with id " + Id + " not found");
		}

		accountlabelRepository. deleteById(Id);

		return ResponseEntity.ok().build();
	}
	
	




}
