package com.example.librarymanagement;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.bookmanagement.Book;
import com.example.user_package.User;

@Entity
public class LibraryManagementModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookIssuedId;
	
	private boolean deposit;
	
	@ManyToOne()
	@JoinColumn(name="fk_user_id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="fk_book_id")
	private Book book;

	public long getBookIssuedId() {
		return bookIssuedId;
	}

	public boolean isDeposit() {
		return deposit;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public void setBookIssuedId(long bookIssuedId) {
		this.bookIssuedId = bookIssuedId;
	}

	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	

}
