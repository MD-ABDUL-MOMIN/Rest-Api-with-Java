package com.example.user_package;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
public class UserController {
	
	@Autowired
	UserOperationService userService;
	
	@GetMapping("users")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("users")
	public User addUser(@Validated @RequestBody User user) {
		
		System.out.println(user);
		
		return userService.addUser(user);
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User>  getUser(@PathVariable("id") long id) throws NotFoundException {
	 return  ResponseEntity.ok().body(userService.findUserBy(id));	
		
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<User>  updateUser(@PathVariable("id") long id, @Validated @RequestBody User updatedUser) throws NotFoundException {
		                                                        
		
		return userService.updateUserById(id,updatedUser);
	}                                                                                       
	
	@DeleteMapping("users/{id}")
	public Map<String, Boolean> removeUser(@PathVariable("id") long id) throws NotFoundException{
		
		return userService.removeUserById(id);
	}
	
	


}
