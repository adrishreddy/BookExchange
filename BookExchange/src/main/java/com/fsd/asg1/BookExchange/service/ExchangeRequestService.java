package com.fsd.asg1.BookExchange.service;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.asg1.BookExchange.dao.impl.ExchangeRequestRepository;
import com.fsd.asg1.BookExchange.dto.ExchangeRequest;

@Service
public class ExchangeRequestService {

    @Autowired
    private ExchangeRequestRepository exchangeRequestRepository;

    public void sendExchangeRequest(String bookName, String senderId, String recipientId, UUID bookId, String deliveryMethod, String exchangeDuration) {
        ExchangeRequest request = new ExchangeRequest();
        request.setBookName(bookName);
        request.setId(UUID.randomUUID());
        request.setSenderId(senderId);
        request.setRecipientId(recipientId);
        request.setBookId(bookId);
        request.setDeliveryMethod(deliveryMethod);
        request.setExchangeDuration(exchangeDuration);
        request.setStatus("pending");
        exchangeRequestRepository.save(request);
    }

    public List<ExchangeRequest> getUserRequests(String userId) {
        return exchangeRequestRepository.findByUserId(userId);
    }

    public void acceptRequest(UUID requestId) {
        exchangeRequestRepository.updateStatus(requestId, "accepted");
        // Notify sender and recipient logic here
    }

    public void rejectRequest(UUID requestId) {
        exchangeRequestRepository.updateStatus(requestId, "rejected");
        // Notify sender and recipient logic here
    }

    public void modifyRequest(UUID requestId, String deliveryMethod, String exchangeDuration) {
        exchangeRequestRepository.updateExchangeRequest(requestId, deliveryMethod, exchangeDuration);
        // Notify recipient logic here
    }
}