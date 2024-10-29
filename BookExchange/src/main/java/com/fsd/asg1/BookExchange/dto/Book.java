package com.fsd.asg1.BookExchange.dto;

import java.util.UUID;

public class Book {
    
    private UUID id;
    private String title;
    private String author;
    private String genre;
    private String condition;
    private String user;
    private boolean available;
    
    

    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	// Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

