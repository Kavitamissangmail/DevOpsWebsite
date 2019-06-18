package com.soprasteria.devopsassesmenttool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.model.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	List<User> findByUserUserId(Long userId);	
	

	
}