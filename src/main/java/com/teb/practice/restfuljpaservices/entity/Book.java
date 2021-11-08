package com.teb.practice.restfuljpaservices.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String bookId;

	@NotNull
	@Size(min = 10, max = 40, message = "Book name should be between 10 to 40 characters.")
	private String bookName;

	@NotNull
	@Size(min = 10, max = 20, message = "Author name should be between 10 to 20 characters.")
	private String bookAuthor;

	protected Book() {
		super();
	}

	/* For early versions of Spring-boot, add a manual no-argument constructor */
	public Book(String bookName, String bookAuthor) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}

	public String getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + "]";
	}

}
