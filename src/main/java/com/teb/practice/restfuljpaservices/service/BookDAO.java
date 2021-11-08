package com.teb.practice.restfuljpaservices.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.teb.practice.restfuljpaservices.entity.Book;

@Repository
@Transactional
public class BookDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public String addBook(Book book) {
		entityManager.persist(book);
		return book.getBookId();
	}
}
