package com.example.user_package;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class UserOperationService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public List<User> getAllUsers(){
		return (List<User>) userRepo.findAll();
	}

	public User addUser(User user) {
		return userRepo.save(user);
		
	}



	public User findUserBy(long  id) {
		
	 return  userRepo.findById( id).get();
	 
	 
	}

	public ResponseEntity<User> updateUserById(long id, User updatedUser) throws NotFoundException {
		 User user = userRepo.findById(id).orElseThrow(() ->
		 new NotFoundException("User not found on:: "+id));
		 
		user.setName(updatedUser.getName());
		user.setEmail(updatedUser.getEmail());
		
		User updateOperation = userRepo.save(user);
		
		return ResponseEntity.ok().body(updateOperation);
		
		
	}

	public Map<String, Boolean> removeUserById(long id) throws NotFoundException {
		
		 User user = userRepo.findById(id).orElseThrow(() ->
		 new NotFoundException("User not found on:: "+id));
		 
	      userRepo.delete(user);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
		return response;
				
	}
	

	

}
