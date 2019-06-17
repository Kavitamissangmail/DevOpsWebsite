/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.User;

/**
 * @author dbkumar
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUserId(Integer userId);

	User findByUserId(Integer userId);

	void deleteByUserId(Integer userId);

	User findByUsername(String username);

	User findByUsermailid(String email);
	

}
