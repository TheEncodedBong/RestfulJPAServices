package com.teb.practice.restfuljpaservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.teb.practice.restfuljpaservices.entity.Author;
import com.teb.practice.restfuljpaservices.entity.Book;
import com.teb.practice.restfuljpaservices.exception.AuthorNotFoundException;
import com.teb.practice.restfuljpaservices.exception.BookNotFoundException;
import com.teb.practice.restfuljpaservices.service.AuthorRepository;
import com.teb.practice.restfuljpaservices.service.BookRepository;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@GetMapping("/")
	public String welcomeMessage() {
		return "Hello!";
	}

	@GetMapping("/authors")
	public List<Author> getAuthorList() {
		return authorRepository.findAll();
	}

	@GetMapping("/books")
	public List<Book> getBookList() {
		return bookRepository.findAll();
	}

	/* Get authors by authorId */
	@GetMapping("/authors/{authorId}")
	public EntityModel<Optional<Author>> getAuthorById(@PathVariable String authorId) {
		Optional<Author> authorToLookup = authorRepository.findById(authorId);
		if (!authorToLookup.isPresent()) {
			throw new AuthorNotFoundException("Author Id: ".concat(authorId.toUpperCase()));
		}

		EntityModel<Optional<Author>> authorResource = EntityModel.of(authorToLookup);
		WebMvcLinkBuilder newLink = linkTo(methodOn(this.getClass()).getAuthorList());
		authorResource.add(newLink.withRel("Check out all authors"));

		return authorResource;
	}

	/* Get books by bookId */
	@GetMapping("/books/{bookId}")
	public EntityModel<Optional<Book>> getBookById(@PathVariable String bookId) {
		Optional<Book> bookToLookup = bookRepository.findById(bookId);
		if (!bookToLookup.isPresent()) {
			throw new BookNotFoundException("Book Id: ".concat(bookId.toUpperCase()));
		}

		EntityModel<Optional<Book>> bookResource = EntityModel.of(bookToLookup);
		WebMvcLinkBuilder newLink = linkTo(methodOn(this.getClass()).getBookList());
		bookResource.add(newLink.withRel("Check out all books"));

		return bookResource;
	}

	/* Get books by authorId */
	@GetMapping("/authors/{authorId}/books")
	public Set<Book> getBookByAuthor(@PathVariable String authorId) {
		Optional<Author> authorToLookup = authorRepository.findById(authorId);
		if (!authorToLookup.isPresent()) {
			throw new AuthorNotFoundException("Author Id: ".concat(authorId.toUpperCase()));
		}

		return authorToLookup.get().getBookSet();
	}
	
	@PostMapping("/authors")
	public ResponseEntity<Object> addNewAuthor(@Valid @RequestBody Author author) {
		Author newAuthor = authorRepository.save(author);
		
		URI pathLocation = ServletUriComponentsBuilder.fromCurrentRequest() // fetches current path
				.path("/{authorId}") // appends required path
				.buildAndExpand(newAuthor.getAuthorId()).toUri(); // replace dynamic value and create new path

		/* Returns a HTTP code while adding a new record */
		return ResponseEntity.created(pathLocation).build();
	}

	/* Add book by authorId */
	@PostMapping("/authors/{authorId}/books")
	public ResponseEntity<Object> addNewBook(@PathVariable String authorId, @RequestBody Book book) {
		Optional<Author> authorToLookup = authorRepository.findById(authorId);
		if (!authorToLookup.isPresent()) {
			throw new AuthorNotFoundException("Author Id: ".concat(authorId.toUpperCase()));
		}
		
		book.setBookAuthor(authorToLookup.get());		
		Book newBook = bookRepository.save(book);

		URI pathLocation = ServletUriComponentsBuilder.fromCurrentRequest() // fetches current path
				.path("/{authorId}") // appends required path
				.buildAndExpand(newBook.getBookId()).toUri(); // replace dynamic value and create new path

		/* Returns a HTTP code while adding a new record */
		return ResponseEntity.created(pathLocation).build();
	}
	
	@PatchMapping("/authors")
	public void updateAuthor(@RequestBody Author author) {
		Optional<Author> authorToLookup = authorRepository.findById(author.getAuthorId());
		if (!authorToLookup.isPresent()) {
			throw new AuthorNotFoundException("Author Id: ".concat(author.getAuthorId().toUpperCase()));
		} else {
			Author authorToUpdate = authorToLookup.get();
			authorToUpdate.setAuthorName(author.getAuthorName());
			authorRepository.saveAndFlush(authorToUpdate);
		}
	}

	/* Update book by authorId */
	@PatchMapping("/authors/{authorId}/books")
	public void updateBook(@PathVariable String authorId, @RequestBody Book book) {
		Optional<Author> authorToLookup = authorRepository.findById(authorId);
		boolean bookToLookup = bookRepository.existsById(book.getBookId());
		if (!authorToLookup.isPresent()) {
			throw new AuthorNotFoundException("Author Id: ".concat(authorId.toUpperCase()));
		} else if (!bookToLookup) {
			throw new BookNotFoundException("Book Id: ".concat(book.getBookId().toUpperCase()));
		} else {
			book.setBookAuthor(authorToLookup.get());
			bookRepository.saveAndFlush(book);
		}
	}

	@DeleteMapping("/authors/{authorId}")
	public void deleteAuthor(@PathVariable String authorId) {
		boolean authorToLookup = authorRepository.existsById(authorId);
		if (!authorToLookup) {
			throw new AuthorNotFoundException("Author Id: ".concat(authorId.toUpperCase()));
		} else {
			authorRepository.deleteById(authorId);
		}
	}

	@DeleteMapping("/books/{bookId}")
	public void deleteBook(@PathVariable String bookId) {
		boolean bookToLookup = bookRepository.existsById(bookId);
		if (!bookToLookup) {
			throw new BookNotFoundException("Book Id: ".concat(bookId.toUpperCase()));
		} else {
			bookRepository.deleteById(bookId);
		}
	}

	@GetMapping("/authorcount")
	public Long getAuthorCount() {
		return authorRepository.count();
	}

	@GetMapping("/bookcount")
	public Long getBookCount() {
		return bookRepository.count();
	}
}
