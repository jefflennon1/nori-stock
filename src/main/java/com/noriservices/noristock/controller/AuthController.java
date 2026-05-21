package com.noriservices.noristock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {


    @GetMapping("/hello")
    public ResponseEntity hello(){
        return ResponseEntity.ok().body("Hello");
    }
}
