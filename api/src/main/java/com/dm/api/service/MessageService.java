package com.dm.api.service;

import com.dm.common.model.Message;
import com.dm.common.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class MessageService {

    private static final String TOPIC = "messages";

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {
        kafkaTemplate.send(TOPIC, message);
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
