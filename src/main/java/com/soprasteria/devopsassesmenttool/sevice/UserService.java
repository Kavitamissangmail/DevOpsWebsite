/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Category;
import com.soprasteria.devopsassesmenttool.model.User;
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

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		
		if (userRepository.existsByUserId(user.getUserId())) {
			throw new ResourceNotFoundException("User with id " + user.getUserId()+ " alreday exist ");
		}
		return userRepository.save(user);
	}

	public User getUserByUserId(Integer userId) {

		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}
		return userRepository.findByUserId(userId);
	}

	public User updateUserByUserId(Integer userId, User userRequest) {

		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}
		User user = userRepository.findByUserId(userId);

		if (user == null) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}

		user.setUserId(userRequest.getUserId());
		user.setUsermailid(userRequest.getUsermailid());
		user.setUsername(userRequest.getUsername());

		return userRepository.save(user);
	}

	public ResponseEntity<Object> deleteByUserId(Integer userId) {
		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}

		userRepository.deleteByUserId(userId);

		return ResponseEntity.ok().build();
	}



}
