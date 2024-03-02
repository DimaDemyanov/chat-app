package com.dm.api.controller;

import com.dm.api.service.UserService;
import com.dm.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String nickname) {
        logger.info("Received request to create user with nickname: {}", nickname);
        User user = userService.createUser(nickname);
        logger.info("Created user with ID: {}", user.getId());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("Received request to get user by ID: {}", id);
        User user = userService.getUserById(id);
        if (user == null) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Found user with ID: {}", id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable String nickname) {
        logger.info("Received request to get user by nickname: {}", nickname);
        User user = userService.getUserByNickname(nickname);
        if (user == null) {
            logger.warn("User not found with nickname: {}", nickname);
            return ResponseEntity.notFound().build();
        }
        logger.info("Found user with nickname: {}", nickname);
        return ResponseEntity.ok(user);
    }
}
