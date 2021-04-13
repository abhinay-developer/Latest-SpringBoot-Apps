package com.abhi.user.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.user.management.exception.EmailAlreadyExistsException;
import com.abhi.user.management.model.User;
import com.abhi.user.management.repository.UserRepository;
import com.abhi.user.management.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	/* POST USER */
	@PostMapping(path = "/create")
	public ResponseEntity<?> postUser(@RequestBody User user) {
		try {

			User email = userRepository.findByEmail(user.getEmail());
			if (email != null) {
				throw new EmailAlreadyExistsException("Email Already Exists");
			} else {
				User theUser = userService.createUser(user);
				return new ResponseEntity<>(theUser, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/* LOGIN USER */
	@PostMapping(path = "/login/{email}/{password}")
	public ResponseEntity<?> loginUser(@PathVariable String email, @PathVariable String password) {
		try {
			User theUser = userService.loginUser(email, password);

			return new ResponseEntity<>(theUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/* GET ALL USERS */
	@GetMapping(path = "/getAllUsers")
	public ResponseEntity<?> retrieveAllUsers() {
		try {
			List<User> theUser = userService.getAllUsers();

			return new ResponseEntity<>(theUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/* GET USER BY USERID */
	@GetMapping(path = "/getUser/{userId}")
	public ResponseEntity<?> retrieveUser(@PathVariable int userId) {
		try {
			Optional<User> theUser = userService.getUser(userId);

			return new ResponseEntity<>(theUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/* DELETE USER BY USERID */
	@DeleteMapping(path = "/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		try {
			return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/** UPDATE USER ***/

	@PutMapping(path  = "/update")
	public ResponseEntity<?> modifyUser(@RequestBody User user) {

		try {
			User theUser = userService.updateUser(user);
			return new ResponseEntity<>(theUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
