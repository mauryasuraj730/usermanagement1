package com.example.UserManagement.usermanagement.user.controller;

import com.example.UserManagement.usermanagement.admin.entity.AdvisorEntity;
import com.example.UserManagement.usermanagement.user.modal.BookedAdvisorResponse;
import com.example.UserManagement.usermanagement.user.modal.BookingPayload;
import com.example.UserManagement.usermanagement.user.modal.SignInPayload;
import com.example.UserManagement.usermanagement.user.modal.SignUpPayload;
import com.example.UserManagement.usermanagement.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> signUpUser(@Valid @RequestBody SignUpPayload signUpPayload) {
        log.info("user payload " + signUpPayload);
        return userService.createUser(signUpPayload);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> signInUser(@Valid @RequestBody SignInPayload signInPayload) {
        log.info("user payload " + signInPayload);
        return userService.signIn(signInPayload);
    }

    @GetMapping("/{userId}/advisor")
    public List<AdvisorEntity> getAllAdvisor() {
        return userService.getAllAdvisor();

    }

    @GetMapping("/{userId}/advisor/booking")
    public List<BookedAdvisorResponse> getAllBookedAdvisor(@PathVariable String userId) {
        return userService.getAllBookedAdvisor(userId);
    }

    @PostMapping("/{userId}/advisor/{advisorId}")
    public ResponseEntity bookAdvisor(@Valid @RequestBody BookingPayload bookingPayload, @PathVariable String userId, @PathVariable String advisorId) {
        userService.bookAdvisor(userId, advisorId, bookingPayload);
        return new ResponseEntity(HttpStatus.OK);
    }


}
