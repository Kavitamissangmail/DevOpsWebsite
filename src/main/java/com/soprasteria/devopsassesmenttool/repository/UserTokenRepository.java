/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soprasteria.devopsassesmenttool.model.UserToken;

/**
 * @author dbkumar
 *
 */

@Repository
public interface UserTokenRepository extends JpaRepository <UserToken, String> {
	
	
	void deleteByusername(String username);
	
	UserToken findByToken(String token);

}
