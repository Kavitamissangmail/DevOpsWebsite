/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.model.UserToken;
import com.soprasteria.devopsassesmenttool.repository.LoginDto;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.repository.UserTokenRepository;
import com.soprasteria.devopsassesmenttool.util.ApiResponse;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTokenRepository userTokenRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {

		if (userRepository.existsByUserId(user.getUserId())) {
			throw new ResourceNotFoundException("User with id " + user.getUserId() + " alreday exist ");
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
		user.setRole(userRequest.getRole());
		user.setPassword(userRequest.getPassword());

		return userRepository.save(user);
	}

	public ResponseEntity<Object> deleteByUserId(Integer userId) {
		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}

		userRepository.deleteByUserId(userId);

		return ResponseEntity.ok().build();
	}

	public ApiResponse login(LoginDto loginDto) {
		User user = userRepository.findByUsername(loginDto.getUsername());
		if (user == null) {
			throw new RuntimeException("User does not exist.");
		}
		if (!user.getPassword().equals(loginDto.getPassword())) {
			throw new RuntimeException("Password mismatch.");
		}

		UserToken ut = new UserToken();
		ut.setIsvalid(true);
		ut.setUsername(user.getUsername());
		ut.setToken(GenearteToken(user.getUsername()));

		userTokenRepository.save(ut);
		return new ApiResponse(200, ut.getUsername(), ut.getToken());

	}

	private String GenearteToken(String username) {

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = username + dateFormat.format(date);

		// Encode into Base64 format
		String BasicBase64format = Base64.getEncoder().encodeToString(strDate.getBytes());

		return BasicBase64format;

	}

	public String createToken(String username) {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User does not exist.");
		}

		UserToken ut = new UserToken();
		ut.setIsvalid(true);
		ut.setUsername(username);
		ut.setToken(GenearteToken(username));

		userTokenRepository.save(ut);
		return ut.getToken();

	}

	@Transactional
	public ApiResponse logout(String UserName) {

		userTokenRepository.deleteByusername(UserName);
		return new ApiResponse(200, "LogOut successfull", "Token Deavtivated");

	}

}
