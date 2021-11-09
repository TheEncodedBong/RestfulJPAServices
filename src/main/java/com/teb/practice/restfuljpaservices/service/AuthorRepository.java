package com.teb.practice.restfuljpaservices.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teb.practice.restfuljpaservices.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

}
