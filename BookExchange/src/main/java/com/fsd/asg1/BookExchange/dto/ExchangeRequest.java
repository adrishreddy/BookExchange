package com.fsd.asg1.BookExchange.dto;

import java.util.UUID;

public class ExchangeRequest {
	private UUID id;
	private String senderId;
	private String recipientId;
	private UUID bookId;
	private String deliveryMethod;
	private String bookName;
	private String exchangeDuration;
	private String lastModified;
	private String status; // pending, accepted, rejected, modified

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getExchangeDuration() {
		return exchangeDuration;
	}

	public void setExchangeDuration(String exchangeDuration) {
		this.exchangeDuration = exchangeDuration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}