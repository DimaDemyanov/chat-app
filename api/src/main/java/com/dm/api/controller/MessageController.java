package com.dm.api.controller;

import com.dm.common.model.Message;
import com.dm.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/received/{userId}")
    public List<Message> getReceivedMessages(@PathVariable Long userId) {
        return messageService.getReceivedMessages(userId);
    }

    @GetMapping("/sent/{userId}")
    public List<Message> getSentMessages(@PathVariable Long userId) {
        return messageService.getSentMessages(userId);
    }

    @GetMapping("/{userId}/from/{senderId}")
    public List<Message> getMessagesFromUser(@PathVariable Long userId, @PathVariable Long senderId) {
        return messageService.getMessagesFromUser(userId, senderId);
    }
}