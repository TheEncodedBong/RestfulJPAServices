package com.teb.practice.restfuljpaservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.teb.practice.restfuljpaservices.entity.Book;

@Component
public class BookRepositoryService implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(BookRepositoryService.class);

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Book newBook = new Book("OOPS! An Introduction", "Megha Barik");
		log.info("New user created using Repository: {}", bookRepository.save(newBook));
		
		log.info("Search by Id: {}", bookRepository.findById("1"));
		
		log.info("List all books: {}", bookRepository.findAll());
		
		log.info("Book count: {}", bookRepository.count());
	}

}
