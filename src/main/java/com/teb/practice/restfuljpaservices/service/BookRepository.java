package com.teb.practice.restfuljpaservices.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teb.practice.restfuljpaservices.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
