package com.soprasteria.devopsassesmenttool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.model.Category;


@Repository
public interface AccountLabelRespository extends JpaRepository<AccountLabel, Long> {
	
	AccountLabel findById(Long id);

	boolean existsById(Long id);


	void deleteById(Long id);
     AccountLabel findByacccolname(String acccolName);

	


}




