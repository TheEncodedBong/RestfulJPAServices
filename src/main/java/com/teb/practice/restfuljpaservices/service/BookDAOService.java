package com.teb.practice.restfuljpaservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.teb.practice.restfuljpaservices.entity.Book;

@Component
public class BookDAOService implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BookDAOService.class);

	@Autowired
	private BookDAO bookDAO;

	@Override
	public void run(String... args) throws Exception {
		Book newBook = new Book("The Basics of Computing", "Sripada Sharath");
		bookDAO.addBook(newBook);
		log.info("New user created using DAO: {}", newBook);
	}

}
