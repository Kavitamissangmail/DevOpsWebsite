/**
 * 
 */
package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.sevice.AnswerService;

import com.soprasteria.devopsassesmenttool.sevice.UserService;

/**
 * @author dbkumar
 *
 */

@RestController
@RequestMapping("/devops")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user) {
		return userService.updateUserByUserId(user.getUserId(), user);
	}

	@Transactional
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUserByUserId(@PathVariable(value = "userId") Integer userId) {
		return userService.deleteByUserId(userId);
	}

}
