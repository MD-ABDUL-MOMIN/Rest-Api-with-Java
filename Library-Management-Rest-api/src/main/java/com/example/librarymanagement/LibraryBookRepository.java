package com.example.librarymanagement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmanagement.Book;
import com.example.user_package.User;

@Repository
interface LibraryBookRepository extends CrudRepository<LibraryManagementModel, Long>{

	List<LibraryManagementModel> findByBookAndUserAndDeposit(Book book,User user,boolean deposit);
	
	

}
