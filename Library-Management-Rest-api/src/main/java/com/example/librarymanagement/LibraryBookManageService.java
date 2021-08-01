package com.example.librarymanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.Book;
import com.example.bookmanagement.BookService;
import com.example.user_package.User;
import com.example.user_package.UserOperationService;

@Service
public class LibraryBookManageService {

	@Autowired
	LibraryBookRepository libraryBookRepository;
	@Autowired
	BookService bookService;
	@Autowired
	UserOperationService userOperationService;

	
	public List<Long> saveRecord(long userId, List<Long> requestedBookList) {
		// TODO Auto-generated method stub
		List<Long> issuedList = new ArrayList<Long>();
		LibraryManagementModel libraryManagementModel;
		for (long bookId : requestedBookList) {

			try {

				Book requestedBook = bookService.findBookBy(bookId);

				if (requestedBook != null && requestedBook.getNumberOfAvailableBook() > 0) {

					requestedBook.setNumberOfAvailableBook(requestedBook.getNumberOfAvailableBook() - 1);

					Book operation = bookService.updateBookById(bookId, requestedBook);
					libraryManagementModel = new LibraryManagementModel();
					libraryManagementModel.setBook(requestedBook);
					libraryManagementModel.setDeposit(false);
					libraryManagementModel.setUser(userOperationService.findUserBy(userId));

					libraryBookRepository.save(libraryManagementModel);
					issuedList.add(bookId);
					System.out.println("done: " + userId + " " + bookId);

				}
			} catch (Exception e) {
				continue;
			}
		}

		return issuedList;

	}
	

	public List<Long> submitBook(long userId, List<Long> submittedBookList) {
		List<LibraryManagementModel> bookIssuelist = new ArrayList<LibraryManagementModel>();
		List<Long> submissionComplete = new ArrayList<Long>();
		User requestedUser = userOperationService.findUserBy(userId);

		for (long bookId : submittedBookList) {
			try {

				Book issuedBook = bookService.findBookBy(bookId);
				if(issuedBook!=null) {

				bookIssuelist.addAll(libraryBookRepository.findByBookAndUserAndDeposit(issuedBook, requestedUser,false));

				submissionComplete.add(bookId);
				}
			} catch (Exception e) {
				continue;
			}
		}
		

		for (LibraryManagementModel model : bookIssuelist) {
			try {
			model.setDeposit(true);
		  model.getBook().setNumberOfAvailableBook(model.getBook().getNumberOfAvailableBook()+1);
			
		

			
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		libraryBookRepository.saveAll(bookIssuelist);

		return submissionComplete;
	}

}