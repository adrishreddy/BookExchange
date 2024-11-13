package com.fsd.asg1.BookExchange.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.asg1.BookExchange.dto.Book;
import com.fsd.asg1.BookExchange.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<Book> addBook( @RequestBody Book book) {
		return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}

	@GetMapping("user/{user}/id/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String user, @PathVariable UUID id) {
		return bookService.getBookById(id).map(book -> new ResponseEntity<>(book, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/user/{user}")
	public ResponseEntity<List<Book>> getBookByUser(@PathVariable String user) {
		List<Book> books = bookService.getBookByUser(user);
		if (books.size() != 0)
			return new ResponseEntity<>(books, HttpStatus.OK);
		else
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return null;
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable UUID id, @Valid @RequestBody Book updatedBook) {
		return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.OK);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Boolean availabile, @RequestParam(required = false) String genre,
			@RequestParam(required = false) String location, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		Boolean availabilityStatus = availabile;
		return bookService.searchBooks(keyword, availabilityStatus, genre, location, page, size);
	}
}
