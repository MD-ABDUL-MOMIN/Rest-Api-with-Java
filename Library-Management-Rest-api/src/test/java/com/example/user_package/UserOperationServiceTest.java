package com.example.user_package;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.exceptionsHandlers.UserNotFoundException;

class UserOperationServiceTest {

	@InjectMocks
	UserOperationService userOperationService;
	
	@Mock
	UserRepo userRepo;
	
     List<User> users;

	@BeforeEach
	void setUp() throws Exception {
		
		users  = new ArrayList<User>();
		User user1 = new User();
		user1.setAddress("faridpur");
		
		users.add(user1);
		User user2 = new User();
		user2.setAddress("dhaka");
		users.add(user2);
		User user3 = new User();
		user3.setAddress("kushtia");
		users.add(user3);
	   MockitoAnnotations.initMocks(this);
	   
	}
	

	@Test
	void testUserNotFoundException() {
		
		when(userRepo.findById(anyLong())).thenReturn(null);
		 assertThrows(UserNotFoundException.class,()->{
	    	 userOperationService.findUserBy(3);
	     });
	
	}
	
	
	@Test
	void testGetAllUsers() {
		
		when(userRepo.findAll()).thenReturn(users);
		
		assertEquals(3, userOperationService.getAllUsers().size());
		
		User testUser = userOperationService.getAllUsers().get(1);
		
		assertEquals("dhaka", testUser.getAddress());
		
		
	}
	
	@Test
	void TestAddUser() {
		
		User user = new User();
		user.setName("sojib");
		
	
		when(userRepo.save(user)).thenReturn(user);
		
		User addedUser = userOperationService.addUser(user);

		
	}
	
	
	
	
	
	

}
