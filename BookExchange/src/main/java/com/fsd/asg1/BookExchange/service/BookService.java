package com.fsd.asg1.BookExchange.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.asg1.BookExchange.dao.impl.BookDaoImpl;
import com.fsd.asg1.BookExchange.dto.Book;

@Service
public class BookService {

    @Autowired
    private BookDaoImpl bookDao;

    public Book addBook(Book book) {
        return bookDao.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookDao.getBookById(id);
    }
    
    public Optional<Book> getBookByUser(String user) {
    	return bookDao.getBookByUser(user);
    }

    public Book updateBook(UUID id, Book updatedBook) {
        return bookDao.updateBook(id, updatedBook);
    }

    public void deleteBook(UUID id) {
        bookDao.deleteBook(id);
    }
    public List<Book> searchBooks(String keyword, Boolean availabilityStatus, String genre, String location, int page, int size) {
        int offset = (page - 1) * size;
        return bookDao.searchBooks(keyword, availabilityStatus, genre, location, offset, size);
    }
}
