package com.example.bookmanagement;

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
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("books")
	public List<Book> getBooks() {
		return bookService.getAllBooks();
	}

	@PostMapping("books")
	public Book addBook(@Validated @RequestBody Book book) {

		System.out.println(book);

		return bookService.addBook(book);

	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
		Book book = bookService.findBookBy(id);

		if (book == null) {
			return (ResponseEntity<Book>) ResponseEntity.notFound();
		} else {
			return ResponseEntity.ok().body(book);
		}

	}

	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @Validated @RequestBody Book updatedBook)
			throws NotFoundException {

		return ResponseEntity.ok().body(bookService.updateBookById(id, updatedBook));
	}

	@DeleteMapping("books/{id}")
	public Map<String, Boolean> removeBook(@PathVariable("id") long id) throws NotFoundException {

		return bookService.removeBookById(id);
	}

}
