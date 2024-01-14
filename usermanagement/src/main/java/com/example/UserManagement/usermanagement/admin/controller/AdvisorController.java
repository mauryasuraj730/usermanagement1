package com.example.UserManagement.usermanagement.admin.controller;

import com.example.UserManagement.usermanagement.admin.modal.AdvisorPayload;
import com.example.UserManagement.usermanagement.admin.service.AdvisorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdvisorController {

    @Autowired
    AdvisorService advisorService;
    @PostMapping(value = "/advisor")
    public ResponseEntity addAdvisor(@Valid @RequestBody AdvisorPayload advisorPayload){
        log.info(" Advisor Payload ====>"+ advisorPayload);
        advisorService.saveAdvisor(advisorPayload);
        return  new ResponseEntity(HttpStatus.OK);
    }





}
