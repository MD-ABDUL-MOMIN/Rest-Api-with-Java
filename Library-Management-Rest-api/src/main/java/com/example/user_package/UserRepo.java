package com.example.user_package;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{

	
	User findById(long id);
	List<User> findAll();
	
	

}
