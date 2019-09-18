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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.model.UserToken;
import com.soprasteria.devopsassesmenttool.repository.AccountRepository;
import com.soprasteria.devopsassesmenttool.repository.AnswerRepository;
import com.soprasteria.devopsassesmenttool.repository.LoginDto;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
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
	AccountRepository accountRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	UserTokenRepository userTokenRepository;
	
	@Autowired
    QuestionRepository questionRepository;

	public List<User> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		
		users.forEach(user ->{
			user.setAgileStatus(getUserStatus(user.getUserId(),"AGILE"));
			user.setDevopsStatus(getUserStatus(user.getUserId(),"DEVOPS"));
		});
		
		return users;
	}

	public User createUser(User user) {

		if (userRepository.existsByUserId(user.getUserId())) {
			throw new ResourceNotFoundException("User with id " + user.getUserId() + " alreday exist ");
		}
		return userRepository.save(user);
	}

	public User getUserByUserId(Long userId) {

		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}
		User user = userRepository.findByUserId(userId);
		user.setAgileStatus(getUserStatus(user.getUserId(),"AGILE"));
		user.setDevopsStatus(getUserStatus(user.getUserId(),"DEVOPS"));
		return user;
		
	}

	public User updateUserByUserId(Long userId, User userRequest) {

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
		user.setLoginName(userRequest.getLoginName());
		user.setAccountName(userRequest.getAccountName());

		return userRepository.save(user);
	}

	public ResponseEntity<Object> deleteByUserId(Long userId) {
		if (!userRepository.existsByUserId(userId)) {
			throw new ResourceNotFoundException("User with id " + userId + " not found");
		}

		userRepository.deleteByUserId(userId);

		return ResponseEntity.ok().build();
	}

	public ApiResponse login(LoginDto loginDto) {
		User user = userRepository.findByUsername(loginDto.getUsername());
		if (user == null) {
			return new ApiResponse(200L, loginDto.getUsername(), "", "", "", 0L, "success", "");

		}
		if (!user.getPassword().equals(loginDto.getPassword())) {
			throw new RuntimeException("Password mismatch.");
		}

		UserToken ut = new UserToken();
		ut.setIsvalid(true);
		ut.setUsername(user.getUsername());
		ut.setUserId(user.getUserId());
		ut.setToken(GenearteToken(user.getUsername()));
		ut.setRole(user.getRole());

		userTokenRepository.save(ut);
		return new ApiResponse(200L, ut.getUsername(),  user.getLoginName(),user.getAccountName(),ut.getToken(), ut.getUserId(), "success", ut.getRole());

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
	public ApiResponse logout(String user) {

		User user1 = userRepository.findByUsername(user);

		long userId = user1.getUserId();
		String uname = user1.getUsername();
		String lname = user1.getLoginName();
		String aname = user1.getAccountName();
		String urole = user1.getRole();

		userTokenRepository.deleteByusername(user);

		return new ApiResponse(200L, uname, lname, aname, "", userId, "User has been logged out",urole );

	}

	public String getUserStatus(Long userId,String type) {

		Long count = answerRepository.countByUserUserIdAndAssessmentType(userId,type);

		if (count == 0) {
			return "Not Started";
		}

		else if (count ==  questionRepository.countByAssessmentType(type)) {
			return "Completed ";
		} else {
			return "In Progress";
		}

	}

}
