package com.teb.practice.restfuljpaservices.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teb.practice.restfuljpaservices.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}
