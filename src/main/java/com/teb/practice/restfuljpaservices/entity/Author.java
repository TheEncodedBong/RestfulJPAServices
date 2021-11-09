package com.teb.practice.restfuljpaservices.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String authorId;

	@NotNull
	@Size(min = 10, max = 20, message = "Author name should be between 10 to 20 characters.")
	private String authorName;

	@OneToMany(mappedBy = "bookAuthor", orphanRemoval = true)
	private Set<Book> bookSet;

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Set<Book> getBookSet() {
		return bookSet;
	}

	public void setBookSet(Set<Book> bookSet) {
		this.bookSet = bookSet;
	}

	@Override
	public String toString() {
		return "Author [authorId: " + authorId + ", authorName: " + authorName + "]";
	}

}
