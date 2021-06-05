package com.abhi.real.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.real.model.Users;
import com.abhi.real.repository.UsersRepository;
@Service
public class UsersService {

	@Autowired
	private UsersRepository  usersRepo;
	
	public  Users   saveUser(Users users) {
		
		return   usersRepo.save(users);
	}
	
	public List<Users>  retriveUsers(){
		
		return usersRepo.findAll();
	}
	
	public   Optional<Users>  retriveUserById(int id){
		
		return usersRepo.findById(id);
	}
	public String deleteUser(int id) throws Exception {
		String message="";
		try {
			usersRepo.deleteById(id);	
			message = "Record Deleted Successfully";
		}
		catch (Exception e) {
			throw new Exception("user not found  "+id);
		}
		return message;
	
}
	public Users updateUser(Users users) throws Exception {
		System.out.println("users.isPresent()");
		try {
			Optional<Users> userData=usersRepo.findById(users.getUid());
			if(userData.isPresent()) {
				System.out.println("userData.isPresent()");
				Users user= userData.get();
				user.setPassword(users.getPassword());
				user.setEmail(users.getEmail());
				user.setMobileNo(user.getMobileNo());
			
				return usersRepo.save(user);
			}
			else {
				System.out.println("userData.isPresent()");
				throw new Exception("User not found "+users.getUid());
			}
		}
		catch (Exception e) {
			System.out.println("userData.isPresent()");
			throw new Exception(e.getLocalizedMessage());
		}
	} 
	
	
}
