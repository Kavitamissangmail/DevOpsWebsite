/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.AppUser;

/**
 * @author dbkumar
 *
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	boolean existsByUserId(Long userId);

	AppUser findByUserId(Long userId);

	void deleteByUserId(Long userId);

}
