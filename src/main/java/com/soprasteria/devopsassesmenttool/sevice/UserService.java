/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.AppUser;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	public AppUser createUser(AppUser appUser) {

		if (userRepository.existsByUserId(appUser.getUserId())) {
			throw new ResourceNotFoundException("User with id " + appUser.getUserId() + " alreday exist ");
		}
		return userRepository.save(appUser);
	}

	public AppUser getUserByUserId(Long userId) {

		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}
		return userRepository.findByUserId(userId);
	}

	public AppUser updateUserByUserId(Long userId, AppUser userRequest) {

		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}
		AppUser appUser = userRepository.findByUserId(userId);

		if (appUser == null) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}

		appUser.setUserId(userRequest.getUserId());
		appUser.setEmailId(userRequest.getEmailId());
		appUser.setName(userRequest.getName());
		appUser.setEnabled(userRequest.isEnabled());

		return userRepository.save(appUser);
	}

	public ResponseEntity<Object> deleteByUserId(Long userId) {
		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}

		userRepository.deleteByUserId(userId);

		return ResponseEntity.ok().build();
	}

}
