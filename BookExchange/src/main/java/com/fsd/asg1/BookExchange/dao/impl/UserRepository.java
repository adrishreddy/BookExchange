package com.fsd.asg1.BookExchange.dao.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fsd.asg1.BookExchange.dto.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ?", userRowMapper, email).stream().findFirst();
    }

    public Optional<User> findById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRowMapper, id).stream().findFirst();
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (id, email, password) VALUES (?, ?, ?)",
                user.getId(), user.getEmail(), user.getPassword());
    }
}