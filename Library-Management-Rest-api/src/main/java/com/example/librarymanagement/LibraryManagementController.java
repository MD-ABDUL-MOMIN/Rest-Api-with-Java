package com.example.librarymanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmanagement.Book;

@RestController
public class LibraryManagementController {

	@Autowired
	LibraryBookManageService libraryBookManageService;

	@PostMapping("books/issue/{userId}")
	public List<Long> addIssueBook(@PathVariable("userId") long userId, @RequestBody List<Long> requestedBookList) {

		List<Long> issuedBook = new ArrayList<Long>();
		if (isIssueingConditionIsOK(requestedBookList)) {

			issuedBook =  libraryBookManageService.saveRecord(userId,requestedBookList);

		}
	
		return issuedBook;
		
	}
	
	
	@PostMapping("books/submit/{userId}")
	public List<Long> issuedBookSubmit(@PathVariable("userId") long userId, @RequestBody List<Long> submittedBookList) {

		List<Long> submitedBook = new ArrayList<Long>();
		if (isIssueingConditionIsOK(submittedBookList)) {

			submitedBook =  libraryBookManageService.submitBook(userId,submittedBookList);

		}
	
		return submitedBook;
		
	}
	
		
	


	private boolean isIssueingConditionIsOK(List<Long> requestedBookList) {
		// TODO Auto-generated method stub
		boolean valid = true, nonValid = false;
		if (requestedBookList.size() > 5)
			return nonValid;

		return valid;

	}

}
