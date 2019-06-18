/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.User;

/**
 * @author dbkumar
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserId(Long userId);

	User findByUserId(Long userId);

	void deleteByUserId(Long userId);

	User findByUsername(String username);

	User findByUsermailid(String email);

}
