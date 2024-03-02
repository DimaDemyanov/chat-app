package com.dm.api.controller;

import com.dm.api.service.MessageService;
import com.dm.common.model.Message;
import com.dm.common.utils.LoggerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping
    public void sendMessage(@RequestBody Message message) {
        logger.info("Received request to send message: {}", LoggerHelper.toJson(message));
        messageService.sendMessage(message);
        logger.info("Message sent successfully");
    }

    @GetMapping("/received/{userId}")
    public List<Message> getReceivedMessages(@PathVariable Long userId) {
        logger.info("Received request to fetch messages received by user: {}", userId);
        List<Message> messages = messageService.getReceivedMessages(userId);
        logger.info("Fetched {} messages received by user {}", messages.size(), userId);
        return messages;
    }

    @GetMapping("/sent/{userId}")
    public List<Message> getSentMessages(@PathVariable Long userId) {
        logger.info("Received request to fetch messages sent by user: {}", userId);
        List<Message> messages = messageService.getSentMessages(userId);
        logger.info("Fetched {} messages sent by user {}", messages.size(), userId);
        return messages;
    }

    @GetMapping("/{userId}/from/{senderId}")
    public List<Message> getMessagesFromUser(@PathVariable Long userId, @PathVariable Long senderId) {
        logger.info("Received request to fetch messages from user {} to user {}", senderId, userId);
        List<Message> messages = messageService.getMessagesFromUser(userId, senderId);
        logger.info("Fetched {} messages from user {} to user {}", messages.size(), senderId, userId);
        return messages;
    }
}