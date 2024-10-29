package com.fsd.asg1.BookExchange.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fsd.asg1.BookExchange.dto.Book;

public interface BookDao {
	Book addBook(Book book);

	List<Book> getAllBooks();

	Optional<Book> getBookById(UUID id);

	Optional<Book> getBookByUser(String user);

	Book updateBook(UUID id, Book updatedBook);

	void deleteBook(UUID id);

	List<Book> searchBooks(String keyword, Boolean availabilityStatus, String genre, String location, int offset,
			int limit);
}