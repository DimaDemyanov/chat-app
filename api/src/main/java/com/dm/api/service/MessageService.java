package com.dm.api.service;

import com.dm.common.model.Message;
import com.dm.common.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        // Logic to send the message and push to the queue
        return messageRepository.save(message);
    }

    public List<Message> getReceivedMessages(Long userId) {
        return messageRepository.findByReceiverId(userId);
    }

    public List<Message> getSentMessages(Long userId) {
        return messageRepository.findBySenderId(userId);
    }

    public List<Message> getMessagesFromUser(Long userId, Long senderId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, userId);
    }
}
