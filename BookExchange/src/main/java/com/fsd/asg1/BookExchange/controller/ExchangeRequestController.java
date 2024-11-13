package com.fsd.asg1.BookExchange.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.asg1.BookExchange.dto.ExchangeRequest;
import com.fsd.asg1.BookExchange.service.ExchangeRequestService;

@RestController
@RequestMapping("/exchange")
@CrossOrigin(origins = "*")
public class ExchangeRequestController {

    @Autowired
    private ExchangeRequestService exchangeRequestService;

    @PostMapping("/request")
    public ResponseEntity<String> sendExchangeRequest(@RequestBody ExchangeRequest request) {
        exchangeRequestService.sendExchangeRequest(request.getSenderId(),request.getRecipientId(),request.getBookId(), request.getDeliveryMethod(), request.getExchangeDuration());
        return ResponseEntity.ok("Exchange request sent!");
    }

    @GetMapping("/requests/{userId}")
    public ResponseEntity<List<ExchangeRequest>> getUserRequests(@PathVariable String userId) {
        List<ExchangeRequest> requests = exchangeRequestService.getUserRequests(userId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable UUID requestId) {
        exchangeRequestService.acceptRequest(requestId);
        return ResponseEntity.ok("Exchange request accepted!");
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable UUID requestId) {
        exchangeRequestService.rejectRequest(requestId);
        return ResponseEntity.ok("Exchange request rejected!");
    }

    @PostMapping("/modify/{requestId}")
    public ResponseEntity<String> modifyRequest(@PathVariable UUID requestId,
    		@RequestBody ExchangeRequest request) {
        exchangeRequestService.modifyRequest(requestId, request.getDeliveryMethod(), request.getExchangeDuration());
        return ResponseEntity.ok("Exchange request modified!");
    }
}