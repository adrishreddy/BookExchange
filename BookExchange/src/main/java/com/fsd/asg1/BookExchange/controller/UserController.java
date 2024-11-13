//package com.fsd.asg1.BookExchange.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/auth")
//public class UserController {
//
//    @Autowired
//    private PasswordResetService passwordResetService;
//
//    @PostMapping("/request-password-reset")
//    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
//        String token = passwordResetService.createPasswordResetToken(email);
//        if (token != null) {
//            return ResponseEntity.ok("Password reset token generated. Check your email for instructions.");
//        }
//        return ResponseEntity.badRequest().body("User not found with the provided email.");
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//        boolean result = passwordResetService.resetPassword(token, newPassword);
//        return result ? ResponseEntity.ok("Password reset successful!") :
//                        ResponseEntity.status(400).body("Invalid or expired token.");
//    }
//}