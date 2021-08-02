package com.example.user_package;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exceptionsHandlers.UserNotFoundException;

import javassist.NotFoundException;

@Service
public class UserOperationService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public List<User> getAllUsers(){
		return  userRepo.findAll();
	}
	

	public User addUser(User user) {
		return userRepo.save(user);
		
	}



	public User findUserBy(long  id) throws UserNotFoundException {
		
		User user = userRepo.findById(id);
		
		if(user==null)
			throw new UserNotFoundException("user not found");
		
		return user;
	 
	 
	}

	public ResponseEntity<User> updateUserById(long id, User updatedUser) throws NotFoundException {
		 User user = userRepo.findById(id);
		 if(user == null)
			 throw new  NotFoundException("not found");
		 
	 
		user.setName(updatedUser.getName());
		user.setEmail(updatedUser.getEmail());
		
		User updateOperation = userRepo.save(user);
		
		return ResponseEntity.ok().body(updateOperation);
		
		
	}

	public Map<String, Boolean> removeUserById(long id) throws NotFoundException{
		
		 User user = userRepo.findById(id);
		 if(user == null)
			 throw new  NotFoundException("not found");
		 
	      userRepo.delete(user);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
		return response;
				
	}
	

	

}
