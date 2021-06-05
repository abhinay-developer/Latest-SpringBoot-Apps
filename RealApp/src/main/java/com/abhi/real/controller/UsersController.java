package com.abhi.real.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.real.model.Users;
import com.abhi.real.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping(path = "/create/user", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> saveUserDetails(@RequestBody Users users) {
		try {
			Users user = usersService.saveUser(users);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/users",  produces  = "application/json")
	public ResponseEntity<?> getUserDetails() {
		try {
			return new ResponseEntity<>(usersService.retriveUsers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/user/{id}",  produces  = "application/json")
	public ResponseEntity<?> getUserDetailsById(@PathVariable int id) {
		try {
			Optional<Users> userData=usersService.retriveUserById(id);
			if (userData.isPresent()) {
				return new ResponseEntity<>(userData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/delete/user/{id}",  produces  = "application/json")
	public ResponseEntity<?> removeUser(@PathVariable int id) {
		try {
		String message=usersService.deleteUser(id);
		if (message!="") {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path="/update/user", consumes =  "application/json",produces =  "application/json")
	public ResponseEntity<?> updateUserDetails(@RequestBody Users user) {
		try {
			System.out.println("userData.isPresent()");
			Users userData=usersService.updateUser(user);
			return new ResponseEntity<>(userData, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("userData.isPresent()");
			return  new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
