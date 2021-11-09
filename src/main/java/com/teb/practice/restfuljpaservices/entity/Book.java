package com.teb.practice.restfuljpaservices.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String bookId;

	@NotNull
	@Size(min = 10, max = 40, message = "Book name should be between 10 to 40 characters.")
	private String bookName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Author bookAuthor;

	protected Book() {
		super();
	}

	public Book(String bookName, String bookAuthor) {
		super();
		this.bookName = bookName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Author getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(Author bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	@Override
	public String toString() {
		return "Book [bookId: " + bookId + ", bookName: " + bookName + "]";
	}

}
