package com.fsd.asg1.BookExchange.dao.impl;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fsd.asg1.BookExchange.dto.ExchangeRequest;

@Repository
public class ExchangeRequestRepository  {
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	    private final RowMapper<ExchangeRequest> exchangeRequestRowMapper = (rs, rowNum) -> {
	        ExchangeRequest request = new ExchangeRequest();
	        request.setId(UUID.fromString(rs.getString("id")));
	        request.setSenderId(rs.getString("sender_id"));
	        request.setRecipientId(rs.getString("recipient_id"));
	        request.setBookId(UUID.fromString(rs.getString("book_id")));
	        request.setDeliveryMethod(rs.getString("delivery_method"));
	        request.setExchangeDuration(rs.getString("exchange_duration"));
	        request.setStatus(rs.getString("status"));
	        request.setLastModified(rs.getString("last_modified"));
	        return request;
	    };

	    public void save(ExchangeRequest request) {
	        jdbcTemplate.update("INSERT INTO exchange_requests (id, sender_id, recipient_id, book_id, delivery_method, exchange_duration, status, last_modified) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)",
	                request.getId().toString(), request.getSenderId(), request.getRecipientId(), request.getBookId().toString(),
	                request.getDeliveryMethod(), request.getExchangeDuration(), request.getStatus());
	    }

	    public List<ExchangeRequest> findByUserId(String userId) {
	        return jdbcTemplate.query("SELECT * FROM exchange_requests WHERE sender_id = ? OR recipient_id = ?", 
	                exchangeRequestRowMapper, userId, userId);
	    }

	    public Optional<ExchangeRequest> findById(UUID id) {
	        return jdbcTemplate.query("SELECT * FROM exchange_requests WHERE id = ?", 
	                exchangeRequestRowMapper, id).stream().findFirst();
	    }

	    public void updateStatus(UUID requestId, String status) {
	        jdbcTemplate.update("UPDATE exchange_requests SET status = ?, last_modified  = CURRENT_TIMESTAMP WHERE id = ?", status, requestId.toString());
	    }

	    public void updateExchangeRequest(UUID requestId, String deliveryMethod, String exchangeDuration) {
	        jdbcTemplate.update("UPDATE exchange_requests SET delivery_method = ?, exchange_duration = ? , last_modified  = CURRENT_TIMESTAMP WHERE id = ?",
	                deliveryMethod, exchangeDuration, requestId.toString());
	    }

}