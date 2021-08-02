package com.example.bookmanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.user_package.User;

import javassist.NotFoundException;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	

	public List getAllBooks() {

		return (List) bookRepository.findAll();
	}

	public Book addBook(Book book) {
		book.setNumberOfAvailableBook(book.getNumberOfCopies());
		return bookRepository.save(book);

	}

	public Book findBookBy(long id) {
		Book book = bookRepository.findById(id).get();

		return book;
	}

	public Book updateBookById(long id, Book updatedBook) {
		Book book = bookRepository.findById(id).get();

		if (updatedBook.getBookAuthorName() != null)
			book.setBookAuthorName(updatedBook.getBookAuthorName());
		if (updatedBook.getBookTitle() != null)
			book.setBookTitle(updatedBook.getBookTitle());

		if (updatedBook.getNumberOfCopies() != 0) {
			book.setNumberOfCopies(updatedBook.getNumberOfCopies() + book.getNumberOfCopies());
			book.setNumberOfAvailableBook(updatedBook.getNumberOfCopies() + book.getNumberOfAvailableBook());

		}

		return bookRepository.save(book);

	}

	public Map<String, Boolean> removeBookById(Long id) throws NotFoundException {

		Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found on:: " + id));

		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

}
