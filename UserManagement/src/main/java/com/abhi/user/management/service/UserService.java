package com.abhi.user.management.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhi.user.management.model.User;
import com.abhi.user.management.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/** CREATE USER **/
	public User createUser(User user) {

		user.setCreatedDate(new Date());
		return userRepository.save(user);
	}

	/** LOGIN USER **/
	public User loginUser(String email, String password) {

		return userRepository.findByEmailAndPassword(email, password);
	}

	/** GET ALL USERS **/
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/** GET ONE USER **/
	public Optional<User> getUser(int userId) {
		return userRepository.findById(userId);
	}

	/** GET USERS COUNT **/
	public Long usersCount(long count) {
		return userRepository.count();
	}

	/** DELETE ONE USER **/
	public ResponseEntity<?> deleteUser(int userId) {
		String message = "";
		try {
			message = "deleted successfully";
			userRepository.deleteById(userId);
			return new ResponseEntity<>(message + ":" + userId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/** UPDATE USER **/
	public User updateUser(User user) throws Exception {

		try {
			Optional<User> userData = userRepository.findById(user.getUserId());
			if (userData.isPresent()) {
				User theUser = userData.get();
				theUser.setUsername(user.getUsername());
				theUser.setPassword(user.getPassword());
				theUser.setCurrentAddress(user.getCurrentAddress());
				theUser.setPermanentAddress(user.getPermanentAddress());
				theUser.setUpdatedDate(new Date());

				return userRepository.save(theUser);
			} else {
				throw new Exception("USER NOT FOUND");
			}
		} catch (Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}

}
