package com.kafka.UserService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.UserService.dto.UserDTO;
import com.kafka.UserService.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    private ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) throws JsonProcessingException {
        return ResponseEntity.ok(userService.signup(userDTO));
    }
}
