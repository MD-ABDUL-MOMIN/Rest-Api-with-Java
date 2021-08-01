package com.example.bookmanagement;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.user_package.User;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bookTitle;
	private String bookAuthorName;
	private int numberOfCopies;
	
	private int numberOfAvailableBook;



	public long getId() {
		return id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthorName() {
		return bookAuthorName;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookAuthorName(String bookAuthorName) {
		this.bookAuthorName = bookAuthorName;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public int getNumberOfAvailableBook() {
		return numberOfAvailableBook;
	}

	public void setNumberOfAvailableBook(int numberOfAvailableBook) {
		this.numberOfAvailableBook = numberOfAvailableBook;
	}
	
	

}
