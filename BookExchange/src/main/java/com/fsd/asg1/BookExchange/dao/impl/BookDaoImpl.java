package com.fsd.asg1.BookExchange.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fsd.asg1.BookExchange.dao.BookDao;
import com.fsd.asg1.BookExchange.dto.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// RowMapper to convert ResultSet rows to Book objects
	private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> {
		Book book = new Book();
		book.setId(UUID.fromString(rs.getString("id")));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setGenre(rs.getString("genre"));
		book.setCondition(rs.getString("bookCondition"));
		book.setAvailable(rs.getBoolean("available"));
		book.setUser(rs.getString("user"));
		return book;
	};

	@Override
	public Book addBook(Book book) {
		String sql = "INSERT INTO books (id, title, author, genre, bookCondition, available, user) VALUES (?, ?, ?, ?, ?, ?, ?)";
		UUID bookId = UUID.randomUUID();
		jdbcTemplate.update(sql, bookId.toString(), book.getTitle(), book.getAuthor(), book.getGenre(),
				book.getCondition(), book.getAvailable(), book.getUser());
		book.setId(bookId);
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT * FROM books";
		return jdbcTemplate.query(sql, BOOK_ROW_MAPPER);
	}

	@Override
	public Optional<Book> getBookById(UUID id) {
		String sql = "SELECT * FROM books WHERE id = ?";
		List<Book> books = jdbcTemplate.query(sql, BOOK_ROW_MAPPER, id.toString());
		return books.stream().findFirst();
	}

	@Override
	public Optional<Book> getBookByUser(String user) {
		String sql = "SELECT * FROM books WHERE user = ?";
		List<Book> books = jdbcTemplate.query(sql, BOOK_ROW_MAPPER, user.toString());
		return books.stream().findFirst();
	}

	@Override
	public Book updateBook(UUID id, Book updatedBook) {
		String sql = "UPDATE books SET title = ?, author = ?, genre = ?, bookCondition = ?, available = ? WHERE id = ?";
		jdbcTemplate.update(sql, updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getGenre(),
				updatedBook.getCondition(), updatedBook.getAvailable(), id.toString());
		updatedBook.setId(id);
		return updatedBook;
	}

	@Override
	public void deleteBook(UUID id) {
		String sql = "DELETE FROM books WHERE id = ?";
		jdbcTemplate.update(sql, id.toString());
	}

	public List<Book> searchBooks(String keyword, Boolean availabilityStatus, String genre, String location, int offset, int limit) {
        StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");

        // Search by keyword across title, author, and genre
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (title LIKE ? OR author LIKE ? OR genre LIKE ?)");
        }

        // Filter by genre if specified
        if (genre != null && !genre.isEmpty()) {
            sql.append(" AND genre = ?");
        }

        // Filter by location if specified
        if (location != null && !location.isEmpty()) {
            sql.append(" AND location = ?");
        }

        // Filter by availability status if specified
        if (availabilityStatus != null) {
            sql.append(" AND availability_status = ?");
        }

        // Limit and offset for pagination
        sql.append(" LIMIT ? OFFSET ?");

        return jdbcTemplate.query(
            sql.toString(),
            ps -> {
                int index = 1;
                if (keyword != null && !keyword.isEmpty()) {
                    ps.setString(index++, "%" + keyword + "%");
                    ps.setString(index++, "%" + keyword + "%");
                    ps.setString(index++, "%" + keyword + "%");
                }
                if (genre != null && !genre.isEmpty()) {
                    ps.setString(index++, genre);
                }
                if (location != null && !location.isEmpty()) {
                    ps.setString(index++, location);
                }
                if (availabilityStatus != null) {
                    ps.setBoolean(index++, availabilityStatus);
                }
                ps.setInt(index++, limit);
                ps.setInt(index, offset);
            },
            BOOK_ROW_MAPPER
        );
    }

}