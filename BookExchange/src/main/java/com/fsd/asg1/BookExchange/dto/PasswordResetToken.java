package com.fsd.asg1.BookExchange.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordResetToken {
    
	private UUID id;
    private UUID userId;
    private String token;
    private LocalDateTime expiryDate;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
   
}

